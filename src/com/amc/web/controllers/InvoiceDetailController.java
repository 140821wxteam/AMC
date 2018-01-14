package com.amc.web.controllers;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.amc.model.models.Invoice;
import com.amc.service.interfaces.IInvoiceDetailService;
import com.amc.web.auth.AuthPassport;
import com.amc.web.models.InvoiceDetailSearchModel;
import com.infrastructure.project.common.utilities.PageListUtil;

@Controller
@RequestMapping(value = "/financial")
public class InvoiceDetailController extends BaseController{
	@Autowired
    @Qualifier("InvoiceDetailService")
	private IInvoiceDetailService invoiceDetailService;
	
	@AuthPassport
	@RequestMapping(value="/invoicedetail/{id}", method = {RequestMethod.GET})
    public String invoicedetail(HttpServletRequest request, Model model, InvoiceDetailSearchModel searchModel,@PathVariable(value="id") Integer id){
    	model.addAttribute("requestUrl", request.getServletPath());
		model.addAttribute("requestQuery", request.getQueryString());
		List<Invoice> lists=invoiceService.listAll();
		String invoiceId=null;
		for(Invoice in:lists) {
			if(in.getId()==id) { 
				invoiceId=in.getInvoiceId();
			}
		}
		
		searchModel.setInvoiceId(invoiceId);

        model.addAttribute("searchModel", searchModel);
        int pageNo = ServletRequestUtils.getIntParameter(request, PageListUtil.PAGE_NO_NAME, PageListUtil.DEFAULT_PAGE_NO);
        int pageSize = ServletRequestUtils.getIntParameter(request, PageListUtil.PAGE_SIZE_NAME, PageListUtil.DEFAULT_PAGE_SIZE);      
        model.addAttribute("contentModel", invoiceDetailService.listPage(searchModel.getInvoiceId(), pageNo, pageSize));
        return "financial/invoicedetail";
    }

}
