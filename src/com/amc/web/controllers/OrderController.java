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

import com.amc.model.models.Order;
import com.amc.model.models.Orderdetail;
import com.amc.model.models.Product;
import com.amc.model.models.Orderdetail.OrderdetailListForm;
import com.amc.service.interfaces.IOrderService;
import com.amc.service.interfaces.IOrderdetailService;
import com.amc.service.services.OrderdetailService;
import com.amc.web.auth.AuthPassport;
import com.amc.web.jsonmodels.OrderdetailJson;
import com.amc.web.jsonmodels.OrderdetailJson.OrderdetailJsonListForm;
import com.amc.web.models.OrderEditModel;
import com.amc.web.models.OrderSearchModel;
import com.amc.web.models.OrderdetailEditModel;
import com.amc.web.models.OrderdetailSearchModel;
import com.amc.web.models.ProductEditModel;
import com.amc.web.models.extension.OrderModelExtension;
import com.amc.web.models.extension.OrderdetailModelExtension;
import com.amc.web.models.extension.ProductModelExtension;
import com.infrastructure.project.common.exception.EntityOperateException;
import com.infrastructure.project.common.exception.ValidatException;
import com.infrastructure.project.common.utilities.PageListUtil;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

@Controller
@RequestMapping(value = "/sales")

public class OrderController extends BaseController{
	@Autowired
    @Qualifier("OrderService")
	
	
	private IOrderService orderService;
	//private IOrderdetailService orderdetailService;
	
	@AuthPassport
	@RequestMapping(value="/order", method = {RequestMethod.GET})
    public String order(HttpServletRequest request, Model model, OrderSearchModel searchModel){
    	model.addAttribute("requestUrl", request.getServletPath());
		model.addAttribute("requestQuery", request.getQueryString());

        model.addAttribute("searchModel", searchModel);
        int pageNo = ServletRequestUtils.getIntParameter(request, PageListUtil.PAGE_NO_NAME, PageListUtil.DEFAULT_PAGE_NO);
        int pageSize = ServletRequestUtils.getIntParameter(request, PageListUtil.PAGE_SIZE_NAME, PageListUtil.DEFAULT_PAGE_SIZE);      
        model.addAttribute("contentModel", orderService.listPage(searchModel.getorderId(), searchModel.getcustomerId(), searchModel.getstatus(), pageNo, pageSize));
        return "sales/order";
    }
	
	
/*	@AuthPassport
	@RequestMapping(value="/orderdetail", method = {RequestMethod.GET})
    public String orderdetail(HttpServletRequest request, Model model, OrderdetailSearchModel searchModel){
    	model.addAttribute("requestUrl", request.getServletPath());
		model.addAttribute("requestQuery", request.getQueryString());

        model.addAttribute("searchModel", searchModel);
        int pageNo = ServletRequestUtils.getIntParameter(request, PageListUtil.PAGE_NO_NAME, PageListUtil.DEFAULT_PAGE_NO);
        int pageSize = ServletRequestUtils.getIntParameter(request, PageListUtil.PAGE_SIZE_NAME, PageListUtil.DEFAULT_PAGE_SIZE);      
        model.addAttribute("contentdetailModel", orderdetailService.listPage(searchModel.getorderdetailId(), pageNo, pageSize));
        return "sales/orderdetail";
    }
*/	
	
	@AuthPassport
	@RequestMapping(value = "/orderaddnew", method = RequestMethod.GET)
	public String orderadd(HttpServletRequest request, Model model) throws NoSuchAlgorithmException, EntityOperateException, ValidatException{	
		if(!model.containsAttribute("contentModel")){
			OrderEditModel orderEditModel = new OrderEditModel();
			//生成订单号
			String date = new SimpleDateFormat("yyyyMMdd").format(new Date());  
	        String seconds = new SimpleDateFormat("HHmmss").format(new Date());
	        
			orderEditModel.setorderId("S"+date+seconds);
			//double tp=orderService.Orderfigure("S"+date+seconds);
			//orderEditModel.settotalPrice(tp);
			model.addAttribute("contentModel", orderEditModel);
		}
		
        return "sales/orderaddnew";	
	}
	
	/*@AuthPassport
	@RequestMapping(value = "/orderfigure/{orderId}", method = RequestMethod.GET)
	public String figure(HttpServletRequest request, Model model,@RequestParam("orderId") String orderId,@PathVariable(value="orderId") String orderId) throws NoSuchAlgorithmException, EntityOperateException, ValidatException{	
		if(!model.containsAttribute("contentModel")){
			OrderEditModel orderEditModel = new OrderEditModel();
			
			orderEditModel.setorderId(orderId);
			List<Orderdetail> list=orderdetailService.list(orderId);
			double tp=0;
			for(Orderdetail od:list) {
				tp+=od.gettotalPrice();
			}
			orderEditModel.settotalPrice(tp);
			model.addAttribute("contentModel", orderEditModel);
		}
		
        return "sales/orderaddnew";	
	}*/
	//返回按钮
	/*@AuthPassport
	@RequestMapping(value = "/orderaddnew/{orderId}", method = RequestMethod.GET)
	public String orderaddreturn(HttpServletRequest request, Model model,@PathVariable(value="orderId") String orderId){	
		if(!model.containsAttribute("contentModel")){
			OrderEditModel orderEditModel = new OrderEditModel();
			
	        
			orderEditModel.setorderId(orderId);
			model.addAttribute("contentModel", orderEditModel);
		}
		if(!model.containsAttribute("contentdetailModel")){
			OrderdetailEditModel orderdetailEditModel = new OrderdetailEditModel();
			model.addAttribute("contentdetailModel", orderdetailEditModel);
		}
		
        return "sales/orderaddnew";	
	}*/
	@RequestMapping(value="/orderaddnew", method = {RequestMethod.POST})	
	public String orderadd(HttpServletRequest request, Model model, @Valid @ModelAttribute("contentModel") OrderEditModel orderEditModel, BindingResult result) throws ValidatException, EntityOperateException, NoSuchAlgorithmException{
		orderEditModel.setcreateTime(Calendar.getInstance());
		orderEditModel.setstatus("未完成");
		String orderId=orderEditModel.getorderId();
		double tp=0.0;
		List<Orderdetail> lists=orderdetailService.listAll();
		
		for(Orderdetail od:lists) {
			if(od.getorderId().equals(orderId))
			tp+=od.gettotalPrice();
		}
		orderEditModel.settotalPrice(tp);
		orderService.saveOrder(OrderModelExtension.toOrder(orderEditModel));
		
      String returnUrl = ServletRequestUtils.getStringParameter(request, "returnUrl", null);
        if(returnUrl==null)
        	returnUrl="sales/order";
    	return "redirect:"+returnUrl; 
    
	}
	
	@AuthPassport
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
    }
	
	@AuthPassport
	@RequestMapping(value = "orderdelete/{id}", method = {RequestMethod.GET})
	public String orderdelete(HttpServletRequest request, Model model, @PathVariable(value="id") Integer id) throws ValidatException, EntityOperateException{	
		List<Order> lists=orderService.listAll();
		String orderId=null;
		for(Order o:lists) {
			if(o.getId()==id)
			orderId=o.getorderId();
		}
		
		List<Orderdetail> details=orderdetailService.listAll();
		
		for(Orderdetail od:details) {
			if(od.getorderId().equals(orderId))
				orderdetailService.delete(od.getId());
		}
		orderService.delete(id);
		String returnUrl = ServletRequestUtils.getStringParameter(request, "returnUrl", null);
		if(returnUrl==null)
        	returnUrl="sales/order";
        return "redirect:"+returnUrl;	
	
	}
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
	
	}
}
