package com.amc.web.controllers;

import java.security.NoSuchAlgorithmException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.portlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.amc.model.models.Product;
import com.amc.service.interfaces.IInvoicedetailService;
import com.amc.service.interfaces.IProductService;
import com.amc.web.auth.AuthPassport;
import com.amc.web.models.InvoicedetailEditModel;
import com.amc.web.models.InvoicedetailSearchModel;
import com.amc.web.models.ProductEditModel;
import com.amc.web.models.ProductSearchModel;
import com.amc.web.models.extension.InvoicedetailModelExtension;
import com.amc.web.models.extension.ProductModelExtension;
import com.infrastructure.project.common.exception.EntityOperateException;
import com.infrastructure.project.common.exception.ValidatException;
import com.infrastructure.project.common.utilities.PageListUtil;

@Controller
@RequestMapping(value = "/financial")
public class InvoicedetailController extends BaseController{
	@Autowired
    @Qualifier("InvoicedetailService")
	private IInvoicedetailService invoicedetailService;
	
	@AuthPassport
	@RequestMapping(value="/invoicedetail/{invoiceId}", method = {RequestMethod.GET})
    public String invoicedetail(HttpServletRequest request, Model model, InvoicedetailSearchModel searchModel,@PathVariable(value="invoiceId") String invoiceId){
    	model.addAttribute("requestUrl", request.getServletPath());
		model.addAttribute("requestQuery", request.getQueryString());
		//String invoiceId = request.getParameter("invoiceId");
		searchModel.setinvoiceId(invoiceId);
        model.addAttribute("searchModel", searchModel);
        int pageNo = ServletRequestUtils.getIntParameter(request, PageListUtil.PAGE_NO_NAME, PageListUtil.DEFAULT_PAGE_NO);
        int pageSize = ServletRequestUtils.getIntParameter(request, PageListUtil.PAGE_SIZE_NAME, PageListUtil.DEFAULT_PAGE_SIZE);      
        model.addAttribute("contentdetailModel", invoicedetailService.listPage(searchModel.getinvoiceId(), pageNo, pageSize));
        return "financial/invoicedetail";
    }
	
	@AuthPassport
	@RequestMapping(value = "/invoicedetailadd/{invoiceId}", method = RequestMethod.GET)
	public String invoicedetailadd(HttpServletRequest request, Model model,@PathVariable(value="invoiceId") String invoiceId/*@RequestParam("invoiceId") String invoiceId*/){
		
		if(!model.containsAttribute("contentModel")){
			InvoicedetailEditModel invoicedetailEditModel=new InvoicedetailEditModel();
			invoicedetailEditModel.setinvoiceId(invoiceId);
			 
	        String seconds = new SimpleDateFormat("HHmmss").format(new Date());
	        
			invoicedetailEditModel.setinvoicedetailId(invoiceId+"D"+seconds);
			invoicedetailEditModel.setinvoiceId(invoiceId);
			model.addAttribute("contentModel", invoicedetailEditModel);
		}
        return "financial/invoicedetailadd";	
	}
	@RequestMapping(value="/invoicedetailadd/{invoiceId}", method = {RequestMethod.POST})
	public String saveinvoicedetailadd(HttpServletRequest request, Model model, @Valid @ModelAttribute("contentModel") InvoicedetailEditModel invoicedetailEditModel, BindingResult result,@PathVariable(value="invoiceId") String invoiceId) throws ValidatException, EntityOperateException, NoSuchAlgorithmException{
		invoicedetailEditModel.setstatus("未完成");
		invoicedetailEditModel.setinvoicedetailId(request.getParameter("invoicedetailId"));
		invoicedetailEditModel.setproductId(request.getParameter("productId"));
		invoicedetailEditModel.setproductName(request.getParameter("productName"));
		invoicedetailEditModel.setamount(Integer.parseInt(request.getParameter("amount")));
		invoicedetailEditModel.setunitPrice(Double.parseDouble(request.getParameter("unitPrice")));
		invoicedetailEditModel.settotalPrice(Double.parseDouble(request.getParameter("totalPrice")));
		invoicedetailEditModel.setnote(request.getParameter("note"));
		
		invoicedetailService.saveInvoicedetail(InvoicedetailModelExtension.toInvoicedetail(invoicedetailEditModel));
        
		String returnUrl = ServletRequestUtils.getStringParameter(request, "returnUrl", null);
        if(returnUrl==null)
        returnUrl="/financial/invoicedetail/"+invoiceId;
    	return "redirect:"+returnUrl; 
	}
	

}
