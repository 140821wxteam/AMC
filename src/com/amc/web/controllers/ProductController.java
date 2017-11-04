package com.amc.web.controllers;

import java.security.NoSuchAlgorithmException;

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

import com.amc.model.models.Product;
import com.amc.service.interfaces.IProductService;
import com.amc.web.auth.AuthPassport;
import com.amc.web.models.ProductEditModel;
import com.amc.web.models.ProductSearchModel;
import com.amc.web.models.extension.ProductModelExtension;
import com.infrastructure.project.common.exception.EntityOperateException;
import com.infrastructure.project.common.exception.ValidatException;
import com.infrastructure.project.common.utilities.PageListUtil;

@Controller
@RequestMapping(value = "/basedata")
public class ProductController extends BaseController{
	@Autowired
    @Qualifier("ProductService")
	private IProductService productService;
	
	@AuthPassport
	@RequestMapping(value="/product", method = {RequestMethod.GET})
    public String product(HttpServletRequest request, Model model, ProductSearchModel searchModel){
    	model.addAttribute("requestUrl", request.getServletPath());
		model.addAttribute("requestQuery", request.getQueryString());

        model.addAttribute("searchModel", searchModel);
        int pageNo = ServletRequestUtils.getIntParameter(request, PageListUtil.PAGE_NO_NAME, PageListUtil.DEFAULT_PAGE_NO);
        int pageSize = ServletRequestUtils.getIntParameter(request, PageListUtil.PAGE_SIZE_NAME, PageListUtil.DEFAULT_PAGE_SIZE);      
        model.addAttribute("contentModel", productService.listPage(searchModel.getproductId(), searchModel.getproductName(), pageNo, pageSize));
        return "basedata/product";
    }
	
	@AuthPassport
	@RequestMapping(value = "/productadd", method = RequestMethod.GET)
	public String productadd(HttpServletRequest request, Model model){	
		if(!model.containsAttribute("contentModel")){
			model.addAttribute("contentModel", new ProductEditModel());
		}
        return "basedata/productadd";	
	}
	@RequestMapping(value="/productadd", method = {RequestMethod.POST})
	public String productadd(HttpServletRequest request, Model model, @Valid @ModelAttribute("contentModel") ProductEditModel productEditModel, BindingResult result) throws ValidatException, EntityOperateException, NoSuchAlgorithmException{
		      
		productService.saveProduct(ProductModelExtension.toProduct(productEditModel));
        
        String returnUrl = ServletRequestUtils.getStringParameter(request, "returnUrl", null);
        if(returnUrl==null)
        	returnUrl="basedata/product";
    	return "redirect:"+returnUrl; 	
	}
	
	@AuthPassport
	@RequestMapping(value = "/productedit/{id}", method = {RequestMethod.GET})
	public String productedit(HttpServletRequest request, Model model, @PathVariable(value="id") Integer id) throws ValidatException{	
		if(!model.containsAttribute("contentModel")){
			ProductEditModel productEditModel=ProductModelExtension.toProductEditModel(productService.get(id));
			model.addAttribute("contentModel", productEditModel);
		}

        return "basedata/productedit";	
	}
	
	@AuthPassport
	@RequestMapping(value = "/productedit/{id}", method = {RequestMethod.POST})
    public String productedit(HttpServletRequest request, Model model, @Valid @ModelAttribute("contentModel") ProductEditModel editModel, @PathVariable(value="id") Integer id,BindingResult result) throws EntityOperateException, ValidatException, NoSuchAlgorithmException {
		if(result.hasErrors())
            return productedit(request, model, id);
		//vendorService.updateVendor(VendorModelExtension.toVendor(editModel.setId(id)));
        String returnUrl = ServletRequestUtils.getStringParameter(request, "returnUrl", null);
        //System.out.println("return_"+returnUrl);
        Product product=ProductModelExtension.toProduct(editModel);
        product.setId(id);
        productService.updateProduct(product);
        
    if(returnUrl==null)
        returnUrl="basedata/product";
    	return "redirect:"+returnUrl;    
    }
	
	@AuthPassport
	@RequestMapping(value = "/productdelete/{id}", method = {RequestMethod.GET})
	public String delete(HttpServletRequest request, Model model, @PathVariable(value="id") Integer id) throws ValidatException, EntityOperateException{	
		productService.delete(id);
		String returnUrl = ServletRequestUtils.getStringParameter(request, "returnUrl", null);
		if(returnUrl==null)
        	returnUrl="basedata/product";
        return "redirect:"+returnUrl;	
	}
}
