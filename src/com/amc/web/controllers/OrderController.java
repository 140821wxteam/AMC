package com.amc.web.controllers;

import java.security.NoSuchAlgorithmException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
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

import com.amc.model.models.Order;
import com.amc.model.models.Orderdetail;
import com.amc.model.models.Outofstock;
import com.amc.model.models.Outofstockdetail;
import com.amc.service.interfaces.IInventoryService;
import com.amc.service.interfaces.IOrderService;
import com.amc.service.interfaces.IOutofstockService;
import com.amc.service.interfaces.IOutofstockdetailService;
import com.amc.web.auth.AuthPassport;
import com.amc.web.models.OrderEditModel;
import com.amc.web.models.OrderSearchModel;
import com.amc.web.models.extension.OrderModelExtension;
import com.infrastructure.project.common.exception.EntityOperateException;
import com.infrastructure.project.common.exception.ValidatException;
import com.infrastructure.project.common.utilities.PageListUtil;


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
			if(o.getId()==id) {
				orderId=o.getorderId();
				o.setstatus("退回");
				orderService.updateOrder(o);
			}
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
	
	//订单退回操作
		@AuthPassport
		@RequestMapping(value = "orderconfirm/{id}", method = {RequestMethod.GET})
		public String orderconfirm(HttpServletRequest request, Model model, @PathVariable(value="id") Integer id) throws ValidatException, EntityOperateException, NoSuchAlgorithmException{	
			List<Order> lists=orderService.listAll();
			String orderId=null;
			for(Order o:lists) {
				if(o.getId()==id) {
					orderId=o.getorderId();
					o.setstatus("审核通过");
					orderService.updateOrder(o);
				}
			}
			
			List<Orderdetail> details=orderdetailService.listAll();
			
			for(Orderdetail od:details) {
				if(od.getorderId().equals(orderId))
				{
					od.setstatus("审核通过");
					orderdetailService.updateOrderdetail(od);
				}
					
			}
			String returnUrl = ServletRequestUtils.getStringParameter(request, "returnUrl", null);
			if(returnUrl==null)
	        	returnUrl="sales/order";
	        return "redirect:"+returnUrl;	
		
		}
		
	
	//订单审核通过后进行处理，与库存状况比较，生成备货单，缺货单等
	@AuthPassport
	@RequestMapping(value = "orderinprocess/{id}", method = {RequestMethod.GET})
	public String orderinprocess(HttpServletRequest request, Model model, @PathVariable(value="id") Integer id) throws ValidatException, EntityOperateException, NoSuchAlgorithmException{	
		System.out.println("处理！");
		List<Order> lists=orderService.listAll();
		String orderId=null;
		String customerId=null;
		for(Order o:lists) {
			if(o.getId()==id) {
				orderId=o.getorderId();
				customerId=o.getcustomerId();
			}
		}
		System.out.println(orderId+" "+customerId);
		Outofstock  outofstock = new Outofstock();//用于新建订单缺货单
		//String date = new SimpleDateFormat("yyyyMMdd").format(new Date());  
        //String seconds = new SimpleDateFormat("HHmmss").format(new Date());
        String outofstockId=orderId+"OOS";
        System.out.println(outofstockId);
        outofstock.setoutofstockId(outofstockId);//随机生成订单缺货单编号
        outofstock.setorderId(orderId);
        outofstock.setcustomerId(customerId);
        outofstock.setcreateTime(Calendar.getInstance());
        outofstock.setstatus("处理中");
		List<Orderdetail> details=orderdetailService.listAll();
		List<Orderdetail> orderdetail_used=new ArrayList<>();//存储订单号对应的订单明细
		int orderNum=0;//订单项目数
		
		int fitNum=0;//订单完全满足项目数
		int partfitNum=0;//订单部分满足项目数
		int outofstockNum=0;//订单完全缺货项目数
		for(Orderdetail od:details) {
			if(od.getorderId().equals(orderId))
				orderdetail_used.add(od);				
		}
		orderNum = orderdetail_used.size();
		System.out.println("orderNum "+orderNum);
		outofstock.setorderNum(orderNum);
		for(Orderdetail od_used:orderdetail_used) {
			String productId = od_used.getproductId();
			System.out.println("productId "+productId);
			System.out.println(inventoryService.listinventory(productId));
			int current_inventoryLevel = inventoryService.listinventory(productId).getinventoryLevel();
			System.out.println(od_used.getquantityDemand()+" "+current_inventoryLevel);
			//如果该订单明细的产品需求量小于当前产品库存，则证明订单完全满足项目数应该+1
			if(od_used.getquantityDemand()<current_inventoryLevel)
				fitNum++;
			//如果该订单明细的产品需求量大于当前产品库存，而且当前产品库存不为0，则证明订单部分满足项目数应该+1；并且应该在缺货单明细中添加信息
			if(od_used.getquantityDemand()>current_inventoryLevel && current_inventoryLevel>0) {
				partfitNum++;
				Outofstockdetail outofstockdetail = new Outofstockdetail();
				outofstockdetail.setoutofstockId(outofstockId);
				outofstockdetail.setoutofstockdetailId(outofstockId+"D"+new SimpleDateFormat("HHmmss").format(new Date()));
				outofstockdetail.setproductId(productId);
				outofstockdetail.setquantityDemand(od_used.getquantityDemand());
				outofstockdetail.setquantitySupplied(current_inventoryLevel);
				outofstockdetail.setquantityNeeded(od_used.getquantityDemand()-current_inventoryLevel);
				outofstockdetail.setoperateTime(Calendar.getInstance());
				outofstockdetail.setstatus("待处理");
				outofstockdetailService.saveOutofstockdetail(outofstockdetail);
			}
			//如果该订单明细的产品需求量大于当前产品库存，而且当前产品库存0，则证明订单完全缺货项目数应该+1
			if(od_used.getquantityDemand()>current_inventoryLevel && current_inventoryLevel==0) {
				outofstockNum++;	
				Outofstockdetail outofstockdetail = new Outofstockdetail();
				outofstockdetail.setoutofstockId(outofstockId);
				outofstockdetail.setoutofstockdetailId(outofstockId+"D"+new SimpleDateFormat("HHmmss").format(new Date()));
				outofstockdetail.setproductId(productId);
				outofstockdetail.setquantityDemand(od_used.getquantityDemand());
				outofstockdetail.setquantitySupplied(current_inventoryLevel);
				outofstockdetail.setquantityNeeded(od_used.getquantityDemand()-current_inventoryLevel);
				outofstockdetail.setoperateTime(Calendar.getInstance());
				outofstockdetail.setstatus("待处理");
				outofstockdetailService.saveOutofstockdetail(outofstockdetail);
			}
				
		}
		System.out.println(fitNum);
		System.out.println(partfitNum);
		
		outofstock.setfitNum(fitNum);
		outofstock.setpartfitNum(partfitNum);
		outofstock.setoutofstockNum(outofstockNum);
		if(partfitNum!=0 || outofstockNum!=0) outofstockService.saveOutofstock(outofstock);
		String returnUrl = ServletRequestUtils.getStringParameter(request, "returnUrl", null);
		if(returnUrl==null)
        	returnUrl="sales/order";
        return "redirect:"+returnUrl;	
	
	}
}