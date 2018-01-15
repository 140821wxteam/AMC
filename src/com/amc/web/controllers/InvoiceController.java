package com.amc.web.controllers;


import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
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

import com.amc.model.models.AccountTable;
import com.amc.model.models.Cuikuan;
import com.amc.model.models.Invoice;
import com.amc.model.models.Orderdetail;
import com.amc.service.interfaces.IInvoiceService;
import com.amc.web.auth.AuthPassport;
import com.amc.web.models.InvoiceEditModel;
import com.amc.web.models.InvoiceSearchModel;
import com.amc.web.models.OrderEditModel;
import com.amc.web.models.extension.InvoiceModelExtension;
import com.amc.web.models.extension.OrderModelExtension;
import com.infrastructure.project.common.exception.EntityOperateException;
import com.infrastructure.project.common.exception.ValidatException;
import com.infrastructure.project.common.utilities.PageListUtil;

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
        model.addAttribute("contentModel", invoiceService.listPage(searchModel.getInvoiceId(), searchModel.getOrderId(), pageNo, pageSize));
        return "financial/invoice";
    }
	
	@AuthPassport
	@RequestMapping(value = "changeinvoicestatus/{id}", method = {RequestMethod.GET})
	public String changeinvoicestatus(HttpServletRequest request,HttpServletResponse response, Model model, @PathVariable(value="id") Integer id) throws ValidatException, EntityOperateException, NoSuchAlgorithmException, IOException{	
		List<Invoice> invoiceList=invoiceService.listAll();
		Invoice invoice=new Invoice();
		String invoiceId=null;
		int objection=-1;
		
		for(Invoice in:invoiceList){
			if(in.getId()==id){
				invoice=in;
				invoiceId=in.getInvoiceId();
				objection=in.getObjection();
			}
		}
		if(invoiceId!=null && objection==0){
			invoice.setStatus("收到");
			invoiceService.updateInvoice(invoice);
		}
		if(invoiceId!=null && objection==1){
			invoice.setStatus("寄出");
			invoiceService.updateInvoice(invoice);
		}

		String returnUrl = ServletRequestUtils.getStringParameter(request, "returnUrl", null);
		if(returnUrl==null)
        	returnUrl="financial/invoice";
        return "redirect:"+returnUrl;	
	
	}
	
	@AuthPassport
	@RequestMapping(value = "changepurchasebusiness/{id}", method = {RequestMethod.GET})
	public String changepurchasebusiness(HttpServletRequest request,HttpServletResponse response, Model model, @PathVariable(value="id") Integer id) throws ValidatException, EntityOperateException, NoSuchAlgorithmException, IOException{	
		List<Invoice> inList=invoiceService.listAll();

		String invoiceId=null;
		for(Invoice in:inList){
			if(in.getId()==id  && in.getObjection()==0){
				invoiceId=in.getInvoiceId();
			}
		}
		if(invoiceId!=null){
			for(AccountTable at:accountTableService.listAll()){
				if(invoiceId.equals(at.getInvoiceId())){
					at.setPurchaseBusiness(at.getPayable());
					at.setPayable(0);;
					accountTableService.updateAccountTable(at);
				}
			}
		}
		String returnUrl = ServletRequestUtils.getStringParameter(request, "returnUrl", null);
		if(returnUrl==null)
        	returnUrl="financial/invoice";
        return "redirect:"+returnUrl;	
	
	}
	
	@AuthPassport
	@RequestMapping(value = "/invoiceadd", method = RequestMethod.GET)
	public String invoiceadd(HttpServletRequest request, Model model) throws NoSuchAlgorithmException, EntityOperateException, ValidatException{	
		if(!model.containsAttribute("contentModel")){
			InvoiceEditModel invoiceEditModel = new InvoiceEditModel();
			//生成发票编号
			String date = new SimpleDateFormat("yyyyMMdd").format(new Date());  
	        String seconds = new SimpleDateFormat("HHmmss").format(new Date());
	        
			invoiceEditModel.setInvoiceId("S"+date+seconds);

			model.addAttribute("contentModel", invoiceEditModel);
		}
		
        return "financial/invoiceadd";	
	}
	
	@RequestMapping(value="/invoiceadd", method = {RequestMethod.POST})	
	public String invoiceadd(HttpServletRequest request, Model model, @Valid @ModelAttribute("contentModel") InvoiceEditModel invoiceEditModel, BindingResult result) throws ValidatException, EntityOperateException, NoSuchAlgorithmException{		
		invoiceEditModel.setCreateTime(Calendar.getInstance());
		//invoiceEditModel.setStatus("收到");
		invoiceEditModel.setObjection(0);
		invoiceService.save(InvoiceModelExtension.toInvoice(invoiceEditModel));
		
        String returnUrl = ServletRequestUtils.getStringParameter(request, "returnUrl", null);
        if(returnUrl==null)
        	returnUrl="financial/invoice";
    	return "redirect:"+returnUrl; 
    
	}

}
