package com.amc.web.controllers;


import java.io.IOException;
import java.io.PrintWriter;
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

import com.amc.model.models.Inventory;
import com.amc.model.models.Order;
import com.amc.model.models.Orderdetail;
import com.amc.model.models.Stockin;
import com.amc.service.interfaces.IStockinService;
import com.amc.web.auth.AuthPassport;
import com.amc.web.models.OrderEditModel;
import com.amc.web.models.StockinEditModel;
import com.amc.web.models.StockinSearchModel;
import com.amc.web.models.extension.StockinModelExtension;
import com.infrastructure.project.common.exception.EntityOperateException;
import com.infrastructure.project.common.exception.ValidatException;
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
	
	@AuthPassport
	@RequestMapping(value = "/stockinadd", method = RequestMethod.GET)
	public String stockinadd(HttpServletRequest request, Model model){	
		if(!model.containsAttribute("contentModel")){
			StockinEditModel stockinEditModel = new StockinEditModel();
			
			String date = new SimpleDateFormat("yyyyMMdd").format(new Date());  
	        String seconds = new SimpleDateFormat("HHmmss").format(new Date());
	        
	        stockinEditModel.setStockinId("SI"+date+seconds);
			model.addAttribute("contentModel", stockinEditModel);
			model.addAttribute("productIds", productService.listproductId());
			model.addAttribute("vendorIds", vendorService.listvendorId());
		}
        return "inventory/stockinadd";	
	}
	@RequestMapping(value="/stockinadd", method = {RequestMethod.POST})
	public String stockinadd(HttpServletRequest request, Model model, @Valid @ModelAttribute("contentModel") StockinEditModel stockinEditModel, BindingResult result) throws ValidatException, EntityOperateException, NoSuchAlgorithmException{
		stockinEditModel.setCreateTime(Calendar.getInstance());
		stockinEditModel.setStatus("未处理");     
		stockinService.saveStockin(StockinModelExtension.toStockin(stockinEditModel));
        
        String returnUrl = ServletRequestUtils.getStringParameter(request, "returnUrl", null);
        if(returnUrl==null)
        	returnUrl="inventory/stockin";
    	return "redirect:"+returnUrl; 	
	}
	
	@AuthPassport
	@RequestMapping(value = "/stockinedit/{id}", method = {RequestMethod.GET})
	public String stockinedit(HttpServletRequest request,HttpServletResponse response, Model model, @PathVariable(value="id") Integer id) throws ValidatException, IOException{	
		if(stockinService.get(id).getStatus().equals("确认入库")) {
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.flush();
		    out.println("<script>");
			out.println("alert('不能编辑！');");
			out.println("history.back();");
			out.println("</script>");
		}
		if(!model.containsAttribute("contentModel")){
			StockinEditModel stockinEditModel=StockinModelExtension.toStockinEditModel(stockinService.get(id));
			model.addAttribute("productIds", productService.listproductId());
			model.addAttribute("vendorIds", vendorService.listvendorId());
			model.addAttribute("contentModel", stockinEditModel);
		}

        return "inventory/stockinedit";	
	}
	
	@AuthPassport
	@RequestMapping(value = "/stockinedit/{id}", method = {RequestMethod.POST})
    public String stockinedit(HttpServletRequest request,HttpServletResponse response, Model model, @Valid @ModelAttribute("contentModel") StockinEditModel editModel, @PathVariable(value="id") Integer id,BindingResult result) throws EntityOperateException, ValidatException, NoSuchAlgorithmException, IOException {
		if(result.hasErrors())
            return stockinedit(request,response, model, id);
		//vendorService.updateVendor(VendorModelExtension.toVendor(editModel.setId(id)));
        String returnUrl = ServletRequestUtils.getStringParameter(request, "returnUrl", null);
        //System.out.println("return_"+returnUrl);
        Stockin stockin_init = stockinService.get(id);
        Stockin stockin=StockinModelExtension.toStockin(editModel);
        stockin.setId(id);
        stockin.setStatus(stockin_init.getStatus());
        stockin.setCreateTime(stockin_init.getCreateTime());
        stockinService.updateStockin(stockin);
        
    if(returnUrl==null)
        returnUrl="inventory/stockin";
    	return "redirect:"+returnUrl;    
    }
	
	@AuthPassport
	@RequestMapping(value = "/stockindelete/{id}", method = {RequestMethod.GET})
	public String delete(HttpServletRequest request, HttpServletResponse response,Model model, @PathVariable(value="id") Integer id) throws ValidatException, EntityOperateException, IOException{	
		
	    stockinService.delete(id);
		String returnUrl = ServletRequestUtils.getStringParameter(request, "returnUrl", null);
		if(returnUrl==null)
        	returnUrl="inventory/stockin";
        return "redirect:"+returnUrl;	
	}
	
	@AuthPassport
	@RequestMapping(value = "/stockinconfirm/{id}", method = {RequestMethod.GET})
    public String stockinconfirm(HttpServletRequest request,HttpServletResponse response, @PathVariable(value="id") Integer id) throws EntityOperateException, ValidatException, NoSuchAlgorithmException, IOException {
		Stockin stockin = stockinService.get(id);
		stockin.setStatus("确认入库");
		stockinService.updateStockin(stockin);
		
		String productId = stockin.getProductId();
		Inventory inventory_init = inventoryService.listinventory(productId);
		Inventory inventory = new Inventory();
		inventory.setproductId(productId);
		inventory.setproductName(inventory_init.getproductName());
		inventory.setinventoryLevel(inventory_init.getinventoryLevel()+stockin.getAmount());
		inventory.setcreateTime(Calendar.getInstance());
		inventory.setstatus(inventory_init.getstatus());
		inventory.setnote(inventory_init.getnote());
		inventoryService.save(inventory);
		String returnUrl = ServletRequestUtils.getStringParameter(request, "returnUrl", null);
		if(returnUrl==null)
        	returnUrl="inventory/stockin";
        return "redirect:"+returnUrl;	
	}	
}
