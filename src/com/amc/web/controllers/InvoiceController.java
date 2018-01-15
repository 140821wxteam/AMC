package com.amc.web.controllers;

import java.security.NoSuchAlgorithmException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;

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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.amc.model.models.Invoicedetail;
import com.amc.model.models.Invoicedetail.InvoicedetailListForm;
import com.amc.service.interfaces.IInvoiceService;
import com.amc.service.interfaces.IInvoicedetailService;
import com.amc.service.services.InvoicedetailService;
import com.amc.web.auth.AuthPassport;
import com.amc.web.models.InvoiceEditModel;
import com.amc.web.models.InvoiceSearchModel;
import com.amc.web.models.InvoicedetailEditModel;
import com.amc.web.models.InvoicedetailSearchModel;
import com.amc.web.models.extension.InvoiceModelExtension;
import com.amc.web.models.extension.InvoicedetailModelExtension;
import com.infrastructure.project.common.exception.EntityOperateException;
import com.infrastructure.project.common.exception.ValidatException;
import com.infrastructure.project.common.utilities.PageListUtil;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

@Controller
@RequestMapping(value = "/financial")

public class InvoiceController extends BaseController{
	@Autowired
    @Qualifier("InvoiceService")
	
	
	private IInvoiceService invoiceService;
	//private IInvoicedetailService invoicedetailService;
	
	@AuthPassport
	@RequestMapping(value="/invoice", method = {RequestMethod.GET})
    public String invoice(HttpServletRequest request, Model model, InvoiceSearchModel searchModel){
    	model.addAttribute("requestUrl", request.getServletPath());
		model.addAttribute("requestQuery", request.getQueryString());

        model.addAttribute("searchModel", searchModel);
        int pageNo = ServletRequestUtils.getIntParameter(request, PageListUtil.PAGE_NO_NAME, PageListUtil.DEFAULT_PAGE_NO);
        int pageSize = ServletRequestUtils.getIntParameter(request, PageListUtil.PAGE_SIZE_NAME, PageListUtil.DEFAULT_PAGE_SIZE);      
        model.addAttribute("contentModel", invoiceService.listPage(searchModel.getinvoiceId(), searchModel.getfactoryId(), pageNo, pageSize));
        return "financial/invoice";
    }
	
	
/*	@AuthPassport
	@RequestMapping(value="/invoicedetail", method = {RequestMethod.GET})
    public String invoicedetail(HttpServletRequest request, Model model, InvoicedetailSearchModel searchModel){
    	model.addAttribute("requestUrl", request.getServletPath());
		model.addAttribute("requestQuery", request.getQueryString());

        model.addAttribute("searchModel", searchModel);
        int pageNo = ServletRequestUtils.getIntParameter(request, PageListUtil.PAGE_NO_NAME, PageListUtil.DEFAULT_PAGE_NO);
        int pageSize = ServletRequestUtils.getIntParameter(request, PageListUtil.PAGE_SIZE_NAME, PageListUtil.DEFAULT_PAGE_SIZE);      
        model.addAttribute("contentdetailModel", invoicedetailService.listPage(searchModel.getinvoicedetailId(), pageNo, pageSize));
        return "financial/invoicedetail";
    }
*/	
	
	@AuthPassport
	@RequestMapping(value = "/invoiceaddnew", method = RequestMethod.GET)
	public String invoiceadd(HttpServletRequest request, Model model) throws NoSuchAlgorithmException, EntityOperateException, ValidatException{	
		if(!model.containsAttribute("contentModel")){
			InvoiceEditModel invoiceEditModel = new InvoiceEditModel();
			//生成订单号
			String date = new SimpleDateFormat("yyyyMMdd").format(new Date());  
	        String seconds = new SimpleDateFormat("HHmmss").format(new Date());
	        
			invoiceEditModel.setinvoiceId("S"+date+seconds);
			//double tp=invoiceService.Invoicefigure("S"+date+seconds);
			//invoiceEditModel.settotalPrice(tp);
			model.addAttribute("contentModel", invoiceEditModel);
		}
		
        return "financial/invoiceaddnew";	
	}
	
	/*@AuthPassport
	@RequestMapping(value = "/invoicefigure/{invoiceId}", method = RequestMethod.GET)
	public String figure(HttpServletRequest request, Model model,@RequestParam("invoiceId") String invoiceId,@PathVariable(value="invoiceId") String invoiceId) throws NoSuchAlgorithmException, EntityOperateException, ValidatException{	
		if(!model.containsAttribute("contentModel")){
			InvoiceEditModel invoiceEditModel = new InvoiceEditModel();
			
			invoiceEditModel.setinvoiceId(invoiceId);
			List<Invoicedetail> list=invoicedetailService.list(invoiceId);
			double tp=0;
			for(Invoicedetail od:list) {
				tp+=od.gettotalPrice();
			}
			invoiceEditModel.settotalPrice(tp);
			model.addAttribute("contentModel", invoiceEditModel);
		}
		
        return "financial/invoiceaddnew";	
	}*/
	//返回按钮
	/*@AuthPassport
	@RequestMapping(value = "/invoiceaddnew/{invoiceId}", method = RequestMethod.GET)
	public String invoiceaddreturn(HttpServletRequest request, Model model,@PathVariable(value="invoiceId") String invoiceId){	
		if(!model.containsAttribute("contentModel")){
			InvoiceEditModel invoiceEditModel = new InvoiceEditModel();
			
	        
			invoiceEditModel.setinvoiceId(invoiceId);
			model.addAttribute("contentModel", invoiceEditModel);
		}
		if(!model.containsAttribute("contentdetailModel")){
			InvoicedetailEditModel invoicedetailEditModel = new InvoicedetailEditModel();
			model.addAttribute("contentdetailModel", invoicedetailEditModel);
		}
		
        return "financial/invoiceaddnew";	
	}*/

}
