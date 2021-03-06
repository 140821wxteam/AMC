package com.amc.web.controllers;

import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
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

import com.amc.model.models.Customers;
import com.amc.model.models.Vendor;
import com.amc.service.interfaces.ICustomersService;
import com.amc.web.auth.AuthPassport;
import com.amc.web.models.AccountRegisterModel;
import com.amc.web.models.CustomersEditModel;
import com.amc.web.models.CustomersSearchModel;
import com.amc.web.models.RoleEditModel;
import com.amc.web.models.VendorEditModel;
import com.amc.web.models.extension.AccountRegisterModelExtension;
import com.amc.web.models.extension.CustomerModelExtension;
import com.amc.web.models.extension.VendorModelExtension;
import com.infrastructure.project.common.exception.EntityOperateException;
import com.infrastructure.project.common.exception.ValidatException;
import com.infrastructure.project.common.utilities.PageListUtil;

@Controller
@RequestMapping(value = "/basedata")
public class CustomerController extends BaseController{
	@Autowired
    @Qualifier("CustomerService")
	private ICustomersService customerService;
	
	@AuthPassport
	@RequestMapping(value="/customer", method = {RequestMethod.GET})
    public String customer(HttpServletRequest request, Model model, CustomersSearchModel searchModel){
    	model.addAttribute("requestUrl", request.getServletPath());
		model.addAttribute("requestQuery", request.getQueryString());

        model.addAttribute("searchModel", searchModel);
        int pageNo = ServletRequestUtils.getIntParameter(request, PageListUtil.PAGE_NO_NAME, PageListUtil.DEFAULT_PAGE_NO);
        int pageSize = ServletRequestUtils.getIntParameter(request, PageListUtil.PAGE_SIZE_NAME, PageListUtil.DEFAULT_PAGE_SIZE);      
        model.addAttribute("contentModel", customerService.listPage(searchModel.getcustomerId(), searchModel.getcustomerName(), pageNo, pageSize));
        return "basedata/customer";
    }
	
	@AuthPassport
	@RequestMapping(value = "/customeradd", method = RequestMethod.GET)
	public String customeradd(HttpServletRequest request, Model model){
		List<String> provinces = new ArrayList<>();
		provinces.add("北京");provinces.add("天津");provinces.add("上海");
		provinces.add("重庆");provinces.add("河北");provinces.add("河南");
		provinces.add("云南");provinces.add("辽宁");provinces.add("黑龙江");
		provinces.add("湖南");provinces.add("安徽");provinces.add("山东");
		provinces.add("新疆");provinces.add("江苏");provinces.add("浙江");
		provinces.add("江西");provinces.add("湖北");provinces.add("广西");
		provinces.add("甘肃");provinces.add("陕西");provinces.add("山西");
		provinces.add("内蒙古");provinces.add("吉林");provinces.add("福建");
		provinces.add("贵州");provinces.add("广东");provinces.add("青海");
		provinces.add("西藏");provinces.add("四川");provinces.add("宁夏");
		provinces.add("海南");provinces.add("台湾");provinces.add("香港");provinces.add("澳门");
		if(!model.containsAttribute("contentModel")){
			model.addAttribute("contentModel", new CustomersEditModel());
			model.addAttribute("provinces", provinces);
		}
        return "basedata/customeradd";	
	}
	@RequestMapping(value="/customeradd", method = {RequestMethod.POST})
	public String customeradd(HttpServletRequest request, Model model, @Valid @ModelAttribute("contentModel") CustomersEditModel customerEditModel, BindingResult result) throws ValidatException, EntityOperateException, NoSuchAlgorithmException{
		 customerEditModel.setreputation(10);   
		 customerService.saveCustomer(CustomerModelExtension.toCustomer(customerEditModel));
        
        String returnUrl = ServletRequestUtils.getStringParameter(request, "returnUrl", null);
        if(returnUrl==null)
        	returnUrl="basedata/customer";
    	return "redirect:"+returnUrl; 	
	}
	
//	@AuthPassport
//	@RequestMapping(value = "/customerdelete", method = RequestMethod.GET)
//	public String customerdelete(HttpServletRequest request, Model model){	
//		if(!model.containsAttribute("contentModel")){
//			model.addAttribute("contentModel", new CustomersEditModel());
//		}
//        return "basedata/customerdelete";	
//	}
//	@RequestMapping(value="/customerdelete", method = {RequestMethod.POST})
//	public String customerdelete(HttpServletRequest request, Model model, @Valid @ModelAttribute("contentModel") CustomersEditModel customerEditModel, BindingResult result) throws ValidatException, EntityOperateException, NoSuchAlgorithmException{
//		      
//		 customerService.saveCustomer(CustomerModelExtension.toCustomer(customerEditModel));
//        
//        String returnUrl = ServletRequestUtils.getStringParameter(request, "returnUrl", null);
//        if(returnUrl==null)
//        	returnUrl="basedata/customer";
//    	return "redirect:"+returnUrl; 	
//	}
	@AuthPassport
	@RequestMapping(value = "/customeredit/{id}", method = {RequestMethod.GET})
	public String customeredit(HttpServletRequest request, Model model, @PathVariable(value="id") Integer id) throws ValidatException{	
		List<String> provinces = new ArrayList<>();
		provinces.add("北京");provinces.add("天津");provinces.add("上海");
		provinces.add("重庆");provinces.add("河北");provinces.add("河南");
		provinces.add("云南");provinces.add("辽宁");provinces.add("黑龙江");
		provinces.add("湖南");provinces.add("安徽");provinces.add("山东");
		provinces.add("新疆");provinces.add("江苏");provinces.add("浙江");
		provinces.add("江西");provinces.add("湖北");provinces.add("广西");
		provinces.add("甘肃");provinces.add("陕西");provinces.add("山西");
		provinces.add("内蒙古");provinces.add("吉林");provinces.add("福建");
		provinces.add("贵州");provinces.add("广东");provinces.add("青海");
		provinces.add("西藏");provinces.add("四川");provinces.add("宁夏");
		provinces.add("海南");provinces.add("台湾");provinces.add("香港");provinces.add("澳门");
		if(!model.containsAttribute("contentModel")){
			CustomersEditModel customerEditModel=CustomerModelExtension.toCustomerEditModel(customerService.get(id));
			model.addAttribute("contentModel", customerEditModel);
			model.addAttribute("provinces", provinces);
		}

        return "basedata/customeredit";	
	}
	
	
	@AuthPassport
	@RequestMapping(value = "/customeredit/{id}", method = {RequestMethod.POST})
    public String customeredit(HttpServletRequest request, Model model, @Valid @ModelAttribute("contentModel") CustomersEditModel editModel, @PathVariable(value="id") Integer id,BindingResult result) throws EntityOperateException, ValidatException, NoSuchAlgorithmException {
		if(result.hasErrors())
            return customeredit(request, model, id);
		//customerService.updateVendor(VendorModelExtension.toVendor(editModel.setId(id)));
        String returnUrl = ServletRequestUtils.getStringParameter(request, "returnUrl", null);
        //System.out.println("return_"+returnUrl);
        Customers customer=CustomerModelExtension.toCustomer(editModel);
        customer.setId(id);
        customerService.updateCustomer(customer);
        
    if(returnUrl==null)
        returnUrl="basedata/customer";
    	return "redirect:"+returnUrl;    
    }
	
	@AuthPassport
	@RequestMapping(value = "/customerdelete/{id}", method = {RequestMethod.GET})
	public String delete(HttpServletRequest request, Model model, @PathVariable(value="id") Integer id) throws ValidatException, EntityOperateException{	
		customerService.delete(id);
		String returnUrl = ServletRequestUtils.getStringParameter(request, "returnUrl", null);
		if(returnUrl==null)
        	returnUrl="basedata/customer";
        return "redirect:"+returnUrl;	
	}
	
}
