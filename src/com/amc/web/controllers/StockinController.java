package com.amc.web.controllers;


import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.amc.service.interfaces.IStockinService;
import com.amc.web.auth.AuthPassport;
import com.amc.web.models.StockinSearchModel;
import com.infrastructure.project.common.utilities.PageListUtil;


@Controller
@RequestMapping(value = "/inventory")
public class StockinController extends BaseController{
	@Autowired
    @Qualifier("StockinService")
	private IStockinService stockinService;
	
	@AuthPassport
	@RequestMapping(value="/stockin", method = {RequestMethod.GET})
    public String vendor(HttpServletRequest request, Model model, StockinSearchModel searchModel){
    	model.addAttribute("requestUrl", request.getServletPath());
		model.addAttribute("requestQuery", request.getQueryString());

        model.addAttribute("searchModel", searchModel);
        int pageNo = ServletRequestUtils.getIntParameter(request, PageListUtil.PAGE_NO_NAME, PageListUtil.DEFAULT_PAGE_NO);
        int pageSize = ServletRequestUtils.getIntParameter(request, PageListUtil.PAGE_SIZE_NAME, PageListUtil.DEFAULT_PAGE_SIZE);      
        model.addAttribute("contentModel", stockinService.listPage(searchModel.getStockinId(), searchModel.getStatus(), pageNo, pageSize));
        return "inventory/stockin";
    }
/*	
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
	
	@AuthPassport
	@RequestMapping(value = "/vendoredit/{id}", method = {RequestMethod.GET})
	public String vendoredit(HttpServletRequest request, Model model, @PathVariable(value="id") Integer id) throws ValidatException{	
		if(!model.containsAttribute("contentModel")){
			VendorEditModel vendorEditModel=VendorModelExtension.toVendorEditModel(vendorService.get(id));
			model.addAttribute("contentModel", vendorEditModel);
		}

        return "basedata/vendoredit";	
	}
	
	@AuthPassport
	@RequestMapping(value = "/vendoredit/{id}", method = {RequestMethod.POST})
    public String vendoredit(HttpServletRequest request, Model model, @Valid @ModelAttribute("contentModel") VendorEditModel editModel, @PathVariable(value="id") Integer id,BindingResult result) throws EntityOperateException, ValidatException, NoSuchAlgorithmException {
		if(result.hasErrors())
            return vendoredit(request, model, id);
		//vendorService.updateVendor(VendorModelExtension.toVendor(editModel.setId(id)));
        String returnUrl = ServletRequestUtils.getStringParameter(request, "returnUrl", null);
        //System.out.println("return_"+returnUrl);
        Vendor vendor=VendorModelExtension.toVendor(editModel);
        vendor.setId(id);
        vendorService.updateVendor(vendor);
        
    if(returnUrl==null)
        returnUrl="basedata/vendor";
    	return "redirect:"+returnUrl;    
    }
	
	@AuthPassport
	@RequestMapping(value = "/vendordelete/{id}", method = {RequestMethod.GET})
	public String delete(HttpServletRequest request, Model model, @PathVariable(value="id") Integer id) throws ValidatException, EntityOperateException{	
		vendorService.delete(id);
		String returnUrl = ServletRequestUtils.getStringParameter(request, "returnUrl", null);
		if(returnUrl==null)
        	returnUrl="basedata/vendor";
        return "redirect:"+returnUrl;	
	}
	*/
}
