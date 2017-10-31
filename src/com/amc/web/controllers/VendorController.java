package com.amc.web.controllers;

import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.amc.model.models.Vendor;
import com.amc.service.interfaces.IVendorService;
import com.amc.web.auth.AuthPassport;
import com.amc.web.models.AccountRegisterModel;
import com.amc.web.models.RoleEditModel;
import com.amc.web.models.VendorEditModel;
import com.amc.web.models.VendorSearchModel;
import com.amc.web.models.extension.AccountRegisterModelExtension;
import com.amc.web.models.extension.VendorModelExtension;
import com.infrastructure.project.common.exception.EntityOperateException;
import com.infrastructure.project.common.exception.ValidatException;
import com.infrastructure.project.common.utilities.PageListUtil;

@Controller
@RequestMapping(value = "/basedata")
public class VendorController extends BaseController{
	@Autowired
    @Qualifier("VendorService")
	private IVendorService vendorService;
	
	@AuthPassport
	@RequestMapping(value="/vendor", method = {RequestMethod.GET})
    public String vendor(HttpServletRequest request, Model model, VendorSearchModel searchModel){
    	model.addAttribute("requestUrl", request.getServletPath());
		model.addAttribute("requestQuery", request.getQueryString());

        model.addAttribute("searchModel", searchModel);
        int pageNo = ServletRequestUtils.getIntParameter(request, PageListUtil.PAGE_NO_NAME, PageListUtil.DEFAULT_PAGE_NO);
        int pageSize = ServletRequestUtils.getIntParameter(request, PageListUtil.PAGE_SIZE_NAME, PageListUtil.DEFAULT_PAGE_SIZE);      
        model.addAttribute("contentModel", vendorService.listPage(searchModel.getvendorId(), searchModel.getvendorName(), pageNo, pageSize));
        return "basedata/vendor";
    }
	
	@AuthPassport
	@RequestMapping(value = "/vendoradd", method = RequestMethod.GET)
	public String vendoradd(HttpServletRequest request, Model model){	
		if(!model.containsAttribute("contentModel")){
			model.addAttribute("contentModel", new VendorEditModel());
		}
        return "basedata/vendoradd";	
	}
	@RequestMapping(value="/vendoradd", method = {RequestMethod.POST})
	public String vendoradd(HttpServletRequest request, Model model, @Valid @ModelAttribute("contentModel") VendorEditModel vendorEditModel, BindingResult result) throws ValidatException, EntityOperateException, NoSuchAlgorithmException{
		      
		 vendorService.saveVendor(VendorModelExtension.toVendor(vendorEditModel));
        
        String returnUrl = ServletRequestUtils.getStringParameter(request, "returnUrl", null);
        if(returnUrl==null)
        	returnUrl="basedata/vendor";
    	return "redirect:"+returnUrl; 	
	}
	
}
