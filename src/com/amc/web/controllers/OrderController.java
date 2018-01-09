package com.amc.web.controllers;

import java.io.IOException;
import java.io.PrintWriter;
import java.security.NoSuchAlgorithmException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
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
import com.amc.model.models.Outofstock;
import com.amc.model.models.Outofstockdetail;
import com.amc.model.models.Prepare;
import com.amc.model.models.Preparedetail;
import com.amc.service.interfaces.IInventoryService;
import com.amc.service.interfaces.IOrderService;
import com.amc.service.interfaces.IOutofstockService;
import com.amc.service.interfaces.IOutofstockdetailService;
import com.amc.web.auth.AccountAuth;
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
        model.addAttribute("customerIds",customerService.listcustomersId());
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
			model.addAttribute("customerIds",customerService.listcustomersId());
		}
		
        return "sales/orderaddnew";	
	}
	
	
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
	public String orderedit(HttpServletRequest request,HttpServletResponse response, Model model, @PathVariable(value="id") Integer id) throws ValidatException, IOException{
		if(orderService.get(id).getstatus().equals("审核通过")||orderService.get(id).getstatus().equals("已退回")||orderService.get(id).getstatus().equals("已处理")) {
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.flush();
		    out.println("<script>");
			out.println("alert('不能编辑！');");
			out.println("history.back();");
			out.println("</script>");
		}
		if(!model.containsAttribute("contentModel")){
			OrderEditModel orderEditModel=OrderModelExtension.toOrderEditModel(orderService.get(id));
			model.addAttribute("customerIds",customerService.listcustomersId());
			model.addAttribute("contentModel", orderEditModel);
		}

        return "sales/orderedit";	
	}
	
	@AuthPassport
	@RequestMapping(value = "/orderedit/{id}", method = {RequestMethod.POST})
    public String orderedit(HttpServletRequest request,HttpServletResponse response, Model model, @Valid @ModelAttribute("contentModel") OrderEditModel editModel, @PathVariable(value="id") Integer id,BindingResult result) throws EntityOperateException, ValidatException, NoSuchAlgorithmException, IOException {
		if(result.hasErrors())
            return orderedit(request,response, model, id);
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
	public String orderback(HttpServletRequest request, HttpServletResponse response,Model model, @PathVariable(value="id") Integer id) throws ValidatException, EntityOperateException, NoSuchAlgorithmException, IOException{	
		List<Order> lists=orderService.listAll();
		String orderId=null;
		for(Order o:lists) {
			if(o.getId()==id) {
				orderId=o.getorderId();
				if(o.getstatus().equals("审核通过")||o.getstatus().equals("已退回")||o.getstatus().equals("已处理")) {
					response.setContentType("text/html; charset=UTF-8");
					PrintWriter out = response.getWriter();
					out.flush();
				    out.println("<script>");
					out.println("alert('不能退回！');");
					out.println("history.back();");
					out.println("</script>");
				}else {
					o.setstatus("已退回");
					orderService.updateOrder(o);
				}
			}
		}
		
		List<Orderdetail> details=orderdetailService.listAll();
		
		for(Orderdetail od:details) {
			if(od.getorderId().equals(orderId))
			{
				if(od.getstatus().equals("审核通过")||od.getstatus().equals("已退回")||od.getstatus().equals("已处理")) {
					break;
				}
				od.setstatus("已退回");
				orderdetailService.updateOrderdetail(od);
			}
				
		}
		String returnUrl = ServletRequestUtils.getStringParameter(request, "returnUrl", null);
		if(returnUrl==null)
        	returnUrl="sales/order";
        return "redirect:"+returnUrl;	
	
	}
	
	//订单确认操作
		@AuthPassport
		@RequestMapping(value = "orderconfirm/{id}", method = {RequestMethod.GET})
		public String orderconfirm(HttpServletRequest request,HttpServletResponse response, Model model, @PathVariable(value="id") Integer id) throws ValidatException, EntityOperateException, NoSuchAlgorithmException, IOException{	
			List<Order> lists=orderService.listAll();
			String orderId=null;
			for(Order o:lists) {
				if(o.getId()==id) {
					orderId=o.getorderId();
					if(o.getstatus().equals("审核通过")||o.getstatus().equals("已退回")||o.getstatus().equals("已处理")) {
						response.setContentType("text/html; charset=UTF-8");
						PrintWriter out = response.getWriter();
						out.flush();
					    out.println("<script>");
						out.println("alert('不能审核通过！');");
						out.println("history.back();");
						out.println("</script>");
					}else {
						o.setstatus("审核通过");
						orderService.updateOrder(o);
					}
				}
			}
			
			List<Orderdetail> details=orderdetailService.listAll();
			
			for(Orderdetail od:details) {
				if(od.getorderId().equals(orderId))
				{
					if(od.getstatus().equals("审核通过")||od.getstatus().equals("已退回")||od.getstatus().equals("已处理")) {
						break;
					}
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
	@RequestMapping(value = "/orderinprocess/{id}", method = {RequestMethod.GET})
	public String orderinprocess(HttpServletRequest request,HttpServletResponse response, Model model, @PathVariable(value="id") Integer id) throws ValidatException, EntityOperateException, NoSuchAlgorithmException, IOException{	
		AccountAuth user=(AccountAuth)request.getSession().getAttribute("accountAuth");
		String username=user.getUsername();
		//System.out.println("处理！"+username);
		List<Order> lists=orderService.listAll();
		String orderId=null;
		String customerId=null;
		Order order=null;
		for(Order o:lists) {
			if(o.getId()==id) {
				if(o.getstatus().equals("未完成")||o.getstatus().equals("已退回")||o.getstatus().equals("已处理")) {
					response.setContentType("text/html; charset=UTF-8");
					PrintWriter out = response.getWriter();
					out.flush();
				    out.println("<script>");
					out.println("alert('不能处理！');");
					out.println("history.back();");
					out.println("</script>");
				}else {
					orderId=o.getorderId();
					customerId=o.getcustomerId();
					order=o;
					//order.setstatus("已处理");
					//orderService.updateOrder(order);
				}
			}
		}
		//System.out.println(orderId+" "+customerId);
		String date = new SimpleDateFormat("yyyyMMdd").format(new Date());  
        String seconds = new SimpleDateFormat("HHmmss").format(new Date());
		//新建缺件表
		Outofstock  outofstock = new Outofstock();//用于新建订单缺货单
		//String date = new SimpleDateFormat("yyyyMMdd").format(new Date());  
        //String seconds = new SimpleDateFormat("HHmmss").format(new Date());
        String outofstockId=orderId+"OOS"+date+seconds;
        //System.out.println(outofstockId);
        outofstock.setoutofstockId(outofstockId);//随机生成订单缺货单编号
        outofstock.setorderId(orderId);
        outofstock.setcustomerId(customerId);
        outofstock.setcreateTime(Calendar.getInstance());
        outofstock.setstatus("待处理");
        
      //新建备货单
        Prepare  prepare = new Prepare();//用于新建备货单
        String prepareId=orderId+"P"+date+seconds;
        prepare.setprepareId(prepareId);
        prepare.setorderId(orderId);
        prepare.setcustomerId(customerId);
        prepare.setcreateTime(Calendar.getInstance());
        prepare.setstatus("待备货");
        
		List<Orderdetail> details=orderdetailService.listAll();
		List<Orderdetail> orderdetail_used=new ArrayList<>();//存储订单号对应的订单明细
		int orderNum=0;//订单项目数
		
		int fitNum=0;//订单完全满足项目数
		int partfitNum=0;//订单部分满足项目数
		int outofstockNum=0;//订单完全缺货项目数
		for(Orderdetail od:details) {
			if(od.getorderId().equals(orderId)) {
				if(od.getstatus().equals("已处理")) fitNum++;
				if(od.getstatus().equals("审核通过")||od.getstatus().equals("仍有缺货")) {
					orderdetail_used.add(od);
				}
			}
		}
		orderNum = details.size();
		//System.out.println("orderNum "+orderNum);
		outofstock.setorderNum(orderNum);
		prepare.setorderNum(orderNum);
		for(Orderdetail od_used:orderdetail_used) {
			String productId = od_used.getproductId();
			String productName= od_used.getproductName();
			System.out.println("productName "+productName);
			//System.out.println(inventoryService.listinventory(productId));
			int current_inventoryLevel = inventoryService.listinventory(productId).getinventoryLevel();
			System.out.println(od_used.getquantityDemand()+" "+current_inventoryLevel);
			//如果该订单明细的产品需求量减去已供数量小于当前产品库存，则证明订单完全满足项目数应该+1
			if((od_used.getquantityDemand()-od_used.getquantitySupplied())<current_inventoryLevel) {
				fitNum++;
				//生成备货单，同时修改库存
				Preparedetail preparedetail = new Preparedetail();
				preparedetail.setprepareId(prepareId);
				preparedetail.setpreparedetailId(prepareId+"D"+new SimpleDateFormat("HHmmss").format(new Date()));
				preparedetail.setproductId(productId);
				preparedetail.setproductName(productName);
				preparedetail.setfactoryId("");//要修改
				preparedetail.setpreparePers("");//要修改
				preparedetail.setamount(od_used.getquantityDemand()-od_used.getquantitySupplied());
				preparedetail.setstatus("待备货");
				preparedetailService.savePreparedetail(preparedetail);//存储备货单
				//修改库存
				Inventory inventory =new Inventory();
				inventory.setproductId(productId);
				inventory.setproductName(productName);
				inventory.setinventoryLevel(current_inventoryLevel-(od_used.getquantityDemand()-od_used.getquantitySupplied()));
				inventory.setcreateTime(Calendar.getInstance());
				inventory.setstatus("未知");//修改
				inventoryService.saveInventory(inventory);
				//修改订单明细中已供数量
				od_used.setquantitySupplied(od_used.getquantityDemand());
				od_used.setstatus("已处理");
				orderdetailService.updateOrderdetail(od_used);
			}
				
			//如果该订单明细的产品需求量减去已供数量大于当前产品库存，而且当前产品库存不为0，则证明订单部分满足项目数应该+1；并且应该在缺货单明细中添加信息
			if((od_used.getquantityDemand()-od_used.getquantitySupplied())>current_inventoryLevel && current_inventoryLevel>0) {
				partfitNum++;
				Outofstockdetail outofstockdetail = new Outofstockdetail();
				outofstockdetail.setoutofstockId(outofstockId);
				outofstockdetail.setoutofstockdetailId(outofstockId+"D"+new SimpleDateFormat("HHmmss").format(new Date()));
				outofstockdetail.setproductId(productId);
				outofstockdetail.setquantityDemand(od_used.getquantityDemand());
				outofstockdetail.setquantitySupplied(current_inventoryLevel);
				outofstockdetail.setquantityNeeded((od_used.getquantityDemand()-od_used.getquantitySupplied())-current_inventoryLevel);
				outofstockdetail.setoperateTime(Calendar.getInstance());
				outofstockdetail.setstatus("待处理");
				outofstockdetail.setoperatorName(username);
				outofstockdetailService.saveOutofstockdetail(outofstockdetail);
				
				//生成备货单，同时修改库存
				Preparedetail preparedetail = new Preparedetail();
				preparedetail.setprepareId(prepareId);
				preparedetail.setpreparedetailId(prepareId+"D"+new SimpleDateFormat("HHmmss").format(new Date()));
				preparedetail.setproductId(productId);
				preparedetail.setproductName(productName);
				preparedetail.setfactoryId("");//要修改
				preparedetail.setpreparePers("");//要修改
				preparedetail.setamount(current_inventoryLevel);//ss的关系？
				preparedetail.setstatus("待备货");
				preparedetailService.savePreparedetail(preparedetail);//存储备货单
				//修改库存
				Inventory inventory =new Inventory();
				inventory.setproductId(productId);
				inventory.setproductName(productName);
				inventory.setinventoryLevel(0);//ss的关系？
				inventory.setstatus("未知");//修改
				inventory.setcreateTime(Calendar.getInstance());
				inventoryService.saveInventory(inventory);
				//修改订单明细中已供数量
				od_used.setquantitySupplied(current_inventoryLevel);
				od_used.setstatus("仍有缺货");
				orderdetailService.updateOrderdetail(od_used);
			}
			//如果该订单明细的产品需求量减去已供数量大于当前产品库存，而且当前产品库存0，则证明订单完全缺货项目数应该+1
			if((od_used.getquantityDemand()-od_used.getquantitySupplied())>current_inventoryLevel && current_inventoryLevel==0) {
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
				outofstockdetail.setoperatorName(username);
				outofstockdetailService.saveOutofstockdetail(outofstockdetail);
				//修改订单明细中已供数量
				od_used.setquantitySupplied(0);
				od_used.setstatus("仍有缺货");
				orderdetailService.updateOrderdetail(od_used);
			}
				
		}
		System.out.println(fitNum);
		System.out.println(partfitNum);
		//保存缺件表
		outofstock.setfitNum(fitNum);
		outofstock.setpartfitNum(partfitNum);
		outofstock.setoutofstockNum(outofstockNum);
		if(partfitNum!=0 || outofstockNum!=0) outofstockService.saveOutofstock(outofstock);
		//保存备货单
		prepare.setfitNum(fitNum);
		prepare.setpartfitNum(partfitNum);
		prepare.setoutofstockNum(outofstockNum);
		if(partfitNum!=0 || fitNum!=0) prepareService.savePrepare(prepare);
		
		if(partfitNum!=0 || outofstockNum!=0) {
			order.setstatus("仍有缺货"); 
		}
		else order.setstatus("已处理");
		orderService.updateOrder(order);
		String returnUrl = ServletRequestUtils.getStringParameter(request, "returnUrl", null);
		if(returnUrl==null)
        	returnUrl="sales/order";
        return "redirect:"+returnUrl;	
	
	}
}
