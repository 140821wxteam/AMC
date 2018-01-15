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

import com.amc.model.models.Order;
import com.amc.model.models.Orderdetail;
import com.amc.model.models.Prepare;
import com.amc.model.models.PurchaseDetail;
import com.amc.model.models.PurchaseOrder;
import com.amc.model.models.Stockin;
import com.amc.service.interfaces.IPurchaseDetailService;
import com.amc.service.interfaces.IPurchaseOrderService;
import com.amc.web.auth.AuthPassport;
import com.amc.web.models.PurchaseOrderEditModel;
import com.amc.web.models.PurchaseOrderSearchModel;
import com.amc.web.models.extension.PurchaseOrderModelExtension;
import com.infrastructure.project.common.exception.EntityOperateException;
import com.infrastructure.project.common.exception.ValidatException;
import com.infrastructure.project.common.utilities.PageListUtil;

@Controller
@RequestMapping(value = "/purchase")


public class PurchaseOrderController extends BaseController{
	@Autowired
    @Qualifier("PurchaseOrderService")
	private IPurchaseOrderService purchaseOrderService;
	//private IPurchaseDetailService purchaseDetailService;
	
	@AuthPassport
	@RequestMapping(value="/purchaseorder", method = RequestMethod.GET)
    public String purchaseorder(HttpServletRequest request, Model model, PurchaseOrderSearchModel searchModel){
    	model.addAttribute("requestUrl", request.getServletPath());
		model.addAttribute("requestQuery", request.getQueryString());

        model.addAttribute("searchModel", searchModel);
        model.addAttribute("vendors", purchaseOrderService.listVendorNames());

        int pageNo = ServletRequestUtils.getIntParameter(request, PageListUtil.PAGE_NO_NAME, PageListUtil.DEFAULT_PAGE_NO);
        int pageSize = ServletRequestUtils.getIntParameter(request, PageListUtil.PAGE_SIZE_NAME, PageListUtil.DEFAULT_PAGE_SIZE);      
        model.addAttribute("contentModel", purchaseOrderService.listPage(searchModel.getorderId(), searchModel.getvendorName(), searchModel.getstatus(), pageNo, pageSize));
        model.addAttribute("adviceModel", purchaseadviceService.listPage(pageNo, pageSize));

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
		purchaseOrderEditModel.setstatus("待审核");
		String orderId =purchaseOrderEditModel.getorderId();
		double tp=0.0;
		List<PurchaseDetail> lists=purchaseDetailService.listAll();
		
		for(PurchaseDetail od:lists) {
			if(od.getorderId().equals(orderId))
			tp+=od.gettotalPrice();
		}
		purchaseOrderEditModel.settotalPrice(tp);
		purchaseOrderEditModel.setvendorId(purchaseOrderService.getVendorId(purchaseOrderEditModel.getvendorName()));
		purchaseOrderService.saveOrder(PurchaseOrderModelExtension.toPurchaseOrder(purchaseOrderEditModel));
		
        String returnUrl = ServletRequestUtils.getStringParameter(request, "returnUrl", null);
        if(returnUrl==null)
        	returnUrl="purchase/purchaseorder";
    	return "redirect:"+returnUrl; 
    
	}
	
	@AuthPassport
	@RequestMapping(value = "/purchaseorderedit/{id}", method = {RequestMethod.GET})
	public String purchaseorderedit(HttpServletRequest request, HttpServletResponse response, Model model, @PathVariable(value="id") Integer id) throws ValidatException, IOException{	
		if(purchaseOrderService.get(id).getstatus().equals("已审核待发送")||purchaseOrderService.get(id).getstatus().equals("已发送待收货")||purchaseOrderService.get(id).getstatus().equals("已收货")) {
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.flush();
		    out.println("<script>");
			out.println("alert('不能编辑！');");
			out.println("history.back();");
			out.println("</script>");
		}		
		if(!model.containsAttribute("contentModel")){
			PurchaseOrderEditModel purchaseOrderEditModel=PurchaseOrderModelExtension.toPurchaseOrderEditModel(purchaseOrderService.get(id));
			model.addAttribute("contentModel", purchaseOrderEditModel);
			model.addAttribute("vendors", purchaseOrderService.listVendorNames());
		}

        return "purchase/purchaseorderedit";	
	}
	
	
	@RequestMapping(value = "/purchaseorderedit/{id}", method = {RequestMethod.POST})
    public String purchaseorderedit(HttpServletRequest request, HttpServletResponse response, Model model, @Valid @ModelAttribute("contentModel") PurchaseOrderEditModel editModel, @PathVariable(value="id") Integer id,BindingResult result) throws EntityOperateException, ValidatException, NoSuchAlgorithmException, IOException {
		if(result.hasErrors())
            return purchaseorderedit(request, response, model, id);
		//vendorService.updateVendor(VendorModelExtension.toVendor(editModel.setId(id)));
        String returnUrl = ServletRequestUtils.getStringParameter(request, "returnUrl", null);
        //System.out.println("return_"+returnUrl);
        List<PurchaseOrder> lists=purchaseOrderService.listAll();
		String orderId=null;
		for(PurchaseOrder o:lists) {
			if(o.getId()==id)
			orderId=o.getorderId();
		}
		
        double tp=0.0;
		List<PurchaseDetail> details=purchaseDetailService.listAll();
		
		for(PurchaseDetail od:details) {
			if(od.getorderId().equals(orderId))
			tp+=od.gettotalPrice();
		}
		editModel.settotalPrice(tp);
		editModel.setorderDate(new java.sql.Date(new java.util.Date().getTime()));
		editModel.setstatus("待审核");
        PurchaseOrder order=PurchaseOrderModelExtension.toPurchaseOrder(editModel);
        order.setId(id);
        purchaseOrderService.updateOrder(order);
        
    if(returnUrl==null)
        returnUrl="purchase/purchaseorderedit";
    	return "redirect:"+returnUrl;    
    }
	
	@AuthPassport
	@RequestMapping(value = "purchaseorderdelete/{id}", method = {RequestMethod.GET})
	public String purchaseorderdelete(HttpServletRequest request, Model model, @PathVariable(value="id") Integer id) throws ValidatException, EntityOperateException{	
		List<PurchaseOrder> lists = purchaseOrderService.listAll();
		String orderId = null;
		for(PurchaseOrder o:lists) {
			if(o.getId()== id)
			orderId = o.getorderId();
		}
		
		List<PurchaseDetail> details=purchaseDetailService.listAll();
		
		for(PurchaseDetail od:details) {
			if(od.getorderId().equals(orderId))
				purchaseDetailService.delete(od.getId());
		}
		purchaseOrderService.delete(id);
		String returnUrl = ServletRequestUtils.getStringParameter(request, "returnUrl", null);
		if(returnUrl==null)
        	returnUrl="purchase/purchaseorder";
        return "redirect:"+returnUrl;	
	
	}
	
	//订单审核通过操作
	@AuthPassport
	@RequestMapping(value = "orderconfirm/{id}", method = {RequestMethod.GET})
	public String orderconfirm(HttpServletRequest request,HttpServletResponse response, Model model, @PathVariable(value="id") Integer id) throws ValidatException, EntityOperateException, NoSuchAlgorithmException, IOException{	
		
		List<PurchaseOrder> lists=purchaseOrderService.listAll();

		for(PurchaseOrder o:lists) {
			if(o.getId()==id) {

				if(o.getstatus().equals("已审核待发送")||o.getstatus().equals("已发送待收货")||o.getstatus().equals("已收货")) {
					response.setContentType("text/html; charset=UTF-8");
					PrintWriter out = response.getWriter();
					out.flush();
				    out.println("<script>");
					out.println("alert('不能审核通过！');");
					out.println("history.back();");
					out.println("</script>");
				}else {
					o.setstatus("已审核待发送");
					purchaseOrderService.updateOrder(o);
					String returnUrl = ServletRequestUtils.getStringParameter(request, "returnUrl", null);
					if(returnUrl==null)
			        	returnUrl="purchase/purchaseorder";
			        return "redirect:"+returnUrl;	
				}
			}
		}
        return "purchase/purchaseorder";		
	}
	
	//发送订单操作
	@AuthPassport
	@RequestMapping(value = "ordersend/{id}", method = {RequestMethod.GET})
	public String ordersend(HttpServletRequest request,HttpServletResponse response, Model model, @PathVariable(value="id") Integer id) throws ValidatException, EntityOperateException, NoSuchAlgorithmException, IOException{	
		List<PurchaseOrder> lists=purchaseOrderService.listAll();

		for(PurchaseOrder o:lists) {
			if(o.getId()==id) {

				if(o.getstatus().equals("待审核")||o.getstatus().equals("已发送待收货")||o.getstatus().equals("已收货")) {
					response.setContentType("text/html; charset=UTF-8");
					PrintWriter out = response.getWriter();
					out.flush();
				    out.println("<script>");
					out.println("alert('不能发送订单！');");
					out.println("history.back();");
					out.println("</script>");
				}else {
					o.setstatus("已发送待收货");
					purchaseOrderService.updateOrder(o);
					String returnUrl = ServletRequestUtils.getStringParameter(request, "returnUrl", null);
					if(returnUrl==null)
			        	returnUrl="purchase/purchaseorder";
			        return "redirect:"+returnUrl;	
				}
			}
		}
        return "purchase/purchaseorder";		
	}

	//订单到货确认并生成进货单操作
	@AuthPassport
	@RequestMapping(value = "productreceived/{id}", method = {RequestMethod.GET})
	public String productreceived(HttpServletRequest request,HttpServletResponse response, Model model, @PathVariable(value="id") Integer id) throws ValidatException, EntityOperateException, NoSuchAlgorithmException, IOException{	
		List<PurchaseOrder> lists=purchaseOrderService.listAll();
		List<PurchaseDetail> details=purchaseDetailService.listAll();
		String date = new SimpleDateFormat("yyyyMMdd").format(new Date());  
        String seconds = new SimpleDateFormat("HHmmss").format(new Date());
        int num = 0;
		//测试
        System.out.println("zheli");
        for(PurchaseOrder o:lists) {
			if(o.getId()==id) {

				if(o.getstatus().equals("已审核待发送")||o.getstatus().equals("已收货")||o.getstatus().equals("待审核")) {
					response.setContentType("text/html; charset=UTF-8");
					PrintWriter out = response.getWriter();
					out.flush();
				    out.println("<script>");
					out.println("alert('不能确认到货！');");
					out.println("history.back();");
					out.println("</script>");
				}else {
					o.setstatus("已收货");
					purchaseOrderService.updateOrder(o);
					
					//新建进货单
					for(PurchaseDetail od:details) {
						if(od.getorderId().equals(o.getorderId())){
							Stockin stockin = new Stockin();
					        String stockinId= "SI"+date+seconds+num;
					        stockin.setStockinId(stockinId);
					        stockin.setProductId(od.getproductId());
					        stockin.setAmount(od.getquantity());
					        stockin.setVendorId(o.getvendorId());
					        stockin.setCreateTime(Calendar.getInstance());
					        stockin.setNote(od.getnote());
					        stockin.setStatus("未处理");
					        stockinService.saveStockin(stockin);
						}			
					    num++;
					}
					String returnUrl = ServletRequestUtils.getStringParameter(request, "returnUrl", null);
					if(returnUrl==null)
			        	returnUrl="purchase/purchaseorder";
			        return "redirect:"+returnUrl;
			       
				}
			}
		}
        return "purchase/purchaseorder";	
	
	}

}
