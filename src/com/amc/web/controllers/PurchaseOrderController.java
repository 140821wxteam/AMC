package com.amc.web.controllers;

import java.security.NoSuchAlgorithmException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

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

import com.amc.model.models.PurchaseOrder;
import com.amc.service.interfaces.IPurchaseOrderService;
import com.amc.service.interfaces.IVendorService;
import com.amc.service.services.VendorService;
import com.amc.web.auth.AuthPassport;
import com.amc.web.models.PurchaseOrderEditModel;
import com.amc.web.models.PurchaseOrderSearchModel;
import com.amc.web.models.extension.PurchaseOrderModelExtension;
import com.infrastructure.project.common.exception.EntityOperateException;
import com.infrastructure.project.common.exception.ValidatException;
import com.infrastructure.project.common.utilities.PageListUtil;

@Controller
@RequestMapping(value = "/purchase")


public class PurchaseOrderController {
	@Autowired
    @Qualifier("PurchaseOrderService")
	private IPurchaseOrderService purchaseOrderService;
	//private IOrderdetailService orderdetailService;
	
	@AuthPassport
	@RequestMapping(value="/purchaseorder", method = RequestMethod.GET)
    public String purchaseorder(HttpServletRequest request, Model model, PurchaseOrderSearchModel searchModel){
    	model.addAttribute("requestUrl", request.getServletPath());
		model.addAttribute("requestQuery", request.getQueryString());

        model.addAttribute("searchModel", searchModel);
        model.addAttribute("vendors", purchaseOrderService.listVendorNames());
        //model.addAttribute("vendors", new String[]{});
        int pageNo = ServletRequestUtils.getIntParameter(request, PageListUtil.PAGE_NO_NAME, PageListUtil.DEFAULT_PAGE_NO);
        int pageSize = ServletRequestUtils.getIntParameter(request, PageListUtil.PAGE_SIZE_NAME, PageListUtil.DEFAULT_PAGE_SIZE);      
        model.addAttribute("contentModel", purchaseOrderService.listPage(searchModel.getorderId(), searchModel.getvendorName(), searchModel.getstatus(), pageNo, pageSize));
        return "purchase/purchaseorder";
    }
	
	@AuthPassport
	@RequestMapping(value = "/purchaseorderadd", method = RequestMethod.GET)
	public String purchaseorderadd(HttpServletRequest request, Model model) throws NoSuchAlgorithmException, EntityOperateException, ValidatException{	
		if(!model.containsAttribute("contentModel")){
			PurchaseOrderEditModel purchaseOrderEditModel = new PurchaseOrderEditModel();
			//生成订单号
			String date = new SimpleDateFormat("yyyyMMdd").format(new Date());  
	        String seconds = new SimpleDateFormat("HHmmss").format(new Date());
	        
	        purchaseOrderEditModel.setorderId("P"+date+seconds);
			model.addAttribute("vendors", purchaseOrderService.listVendorNames());
			model.addAttribute("contentModel", purchaseOrderEditModel);
		}	
        return "purchase/purchaseorderadd";	
	}
	
	@RequestMapping(value="/purchaseorderadd", method = {RequestMethod.POST})	
	public String purchaseorderadd(HttpServletRequest request, Model model, @Valid @ModelAttribute("contentModel") PurchaseOrderEditModel purchaseOrderEditModel, BindingResult result) throws ValidatException, EntityOperateException, NoSuchAlgorithmException{
		purchaseOrderEditModel.setorderDate(new java.sql.Date(new java.util.Date().getTime()));
		purchaseOrderEditModel.setstatus("未完成");
		/*String orderId =purchaseOrderEditModel.getorderId();*/
		double tp=0.0;
		/*List<POdetail> lists=podetailService.listAll();
		
		for(Orderdetail od:lists) {
			if(od.getorderId().equals(orderId))
			tp+=od.gettotalPrice();
		}*/
		purchaseOrderEditModel.settotalPrice(tp);
		purchaseOrderEditModel.setvendorId(purchaseOrderService.getVendorId(purchaseOrderEditModel.getvendorName()));
		purchaseOrderService.saveOrder(PurchaseOrderModelExtension.toPurchaseOrder(purchaseOrderEditModel));
		
        String returnUrl = ServletRequestUtils.getStringParameter(request, "returnUrl", null);
        if(returnUrl==null)
        	returnUrl="purchase/purchaseorder";
    	return "redirect:"+returnUrl; 
    
	}
	
	/*@AuthPassport
	@RequestMapping(value = "/orderedit/{id}", method = {RequestMethod.GET})
	public String orderedit(HttpServletRequest request, Model model, @PathVariable(value="id") Integer id) throws ValidatException{	
		if(!model.containsAttribute("contentModel")){
			OrderEditModel orderEditModel=OrderModelExtension.toOrderEditModel(orderService.get(id));
			model.addAttribute("contentModel", orderEditModel);
		}

        return "sales/orderedit";	
	}
	
	@AuthPassport
	@RequestMapping(value = "/orderedit/{id}", method = {RequestMethod.POST})
    public String orderedit(HttpServletRequest request, Model model, @Valid @ModelAttribute("contentModel") OrderEditModel editModel, @PathVariable(value="id") Integer id,BindingResult result) throws EntityOperateException, ValidatException, NoSuchAlgorithmException {
		if(result.hasErrors())
            return orderedit(request, model, id);
		//vendorService.updateVendor(VendorModelExtension.toVendor(editModel.setId(id)));
        String returnUrl = ServletRequestUtils.getStringParameter(request, "returnUrl", null);
        //System.out.println("return_"+returnUrl);
        List<Order> lists=orderService.listAll();
		String orderId=null;
		for(Order o:lists) {
			if(o.getId()==id)
			orderId=o.getorderId();
		}
		
        double tp=0.0;
		List<Orderdetail> details=orderdetailService.listAll();
		
		for(Orderdetail od:details) {
			if(od.getorderId().equals(orderId))
			tp+=od.gettotalPrice();
		}
		editModel.settotalPrice(tp);
		editModel.setcreateTime(Calendar.getInstance());
		editModel.setstatus("未完成");
        Order order=OrderModelExtension.toOrder(editModel);
        order.setId(id);
        orderService.updateOrder(order);
        
    if(returnUrl==null)
        returnUrl="sales/orderedit";
    	return "redirect:"+returnUrl;    
    }*/
	
	@AuthPassport
	@RequestMapping(value = "purchaseorderdelete/{id}", method = {RequestMethod.GET})
	public String orderdelete(HttpServletRequest request, Model model, @PathVariable(value="id") Integer id) throws ValidatException, EntityOperateException{	
		List<PurchaseOrder> lists = purchaseOrderService.listAll();
		String orderId = null;
		for(PurchaseOrder o:lists) {
			if(o.getId()== id)
			orderId = o.getorderId();
		}
		
		/*List<Orderdetail> details=orderdetailService.listAll();
		
		for(Orderdetail od:details) {
			if(od.getorderId().equals(orderId))
				orderdetailService.delete(od.getId());
		}*/
		purchaseOrderService.delete(id);
		String returnUrl = ServletRequestUtils.getStringParameter(request, "returnUrl", null);
		if(returnUrl==null)
        	returnUrl="purchase/purchaseorder";
        return "redirect:"+returnUrl;	
	
	}
	/*
	//订单退回操作
	@AuthPassport
	@RequestMapping(value = "orderback/{id}", method = {RequestMethod.GET})
	public String orderback(HttpServletRequest request, Model model, @PathVariable(value="id") Integer id) throws ValidatException, EntityOperateException, NoSuchAlgorithmException{	
		List<Order> lists=orderService.listAll();
		String orderId=null;
		for(Order o:lists) {
			if(o.getId()==id)
			orderId=o.getorderId();
			o.setstatus("退回");
			orderService.updateOrder(o);
		}
		
		List<Orderdetail> details=orderdetailService.listAll();
		
		for(Orderdetail od:details) {
			if(od.getorderId().equals(orderId))
			{
				od.setstatus("退回");
				orderdetailService.updateOrderdetail(od);
			}
				
		}
		String returnUrl = ServletRequestUtils.getStringParameter(request, "returnUrl", null);
		if(returnUrl==null)
        	returnUrl="sales/order";
        return "redirect:"+returnUrl;	
	
	} */

}
