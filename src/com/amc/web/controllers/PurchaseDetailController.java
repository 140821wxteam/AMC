package com.amc.web.controllers;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.text.SimpleDateFormat;
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
import org.springframework.web.bind.annotation.ResponseBody;

import com.amc.model.models.Inventory;
import com.amc.model.models.Product;
import com.amc.model.models.PurchaseDetail;
import com.amc.model.models.PurchaseOrder;
import com.amc.service.interfaces.IPurchaseDetailService;
import com.amc.service.interfaces.IPurchaseOrderService;
import com.amc.web.auth.AuthPassport;
import com.amc.web.jsonmodels.InventoryJson;
import com.amc.web.models.InventorySearchModel;
import com.amc.web.models.OrderdetailSearchModel;
import com.amc.web.models.PurchaseDetailEditModel;
import com.amc.web.models.PurchaseDetailSearchModel;
import com.amc.web.models.extension.PurchaseDetailModelExtension;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.infrastructure.project.common.exception.EntityOperateException;
import com.infrastructure.project.common.exception.ValidatException;
import com.infrastructure.project.common.utilities.PageListUtil;

@Controller
@RequestMapping(value = "/purchase")
public class PurchaseDetailController extends BaseController{
	@Autowired
    @Qualifier("PurchaseDetailService")
	private IPurchaseDetailService purchaseDetailService;
	
	@AuthPassport
	@RequestMapping(value="/purchasedetail/{orderId}", method = {RequestMethod.GET})
    public String purchasedetail(HttpServletRequest request, Model model, PurchaseDetailSearchModel searchModel,@PathVariable(value="orderId") String orderId){
    	model.addAttribute("requestUrl", request.getServletPath());
		model.addAttribute("requestQuery", request.getQueryString());
		//String orderId = request.getParameter("orderId");
		searchModel.setorderId(orderId);
        model.addAttribute("searchModel", searchModel);
        model.addAttribute("orderId", orderId);
        int pageNo = ServletRequestUtils.getIntParameter(request, PageListUtil.PAGE_NO_NAME, PageListUtil.DEFAULT_PAGE_NO);
        int pageSize = ServletRequestUtils.getIntParameter(request, PageListUtil.PAGE_SIZE_NAME, PageListUtil.DEFAULT_PAGE_SIZE);      
        model.addAttribute("contentdetailModel", purchaseDetailService.listPage(searchModel.getorderId(), pageNo, pageSize));
        return "purchase/purchasedetail";
    }

	//查看订单详情
	@AuthPassport
	@RequestMapping(value="/purchasedetailview/{id}", method = {RequestMethod.GET})
    public String purchasedetailview(HttpServletRequest request, Model model, PurchaseDetailSearchModel searchModel,@PathVariable(value="id") Integer id){
    	model.addAttribute("requestUrl", request.getServletPath());
		model.addAttribute("requestQuery", request.getQueryString());
		//String orderId = request.getParameter("orderId");
		List<PurchaseOrder> lists=purchaseOrderService.listAll();
		String orderId=null;
		for(PurchaseOrder l:lists) {
			if(l.getId()==id) { 
				orderId=l.getorderId();
			}
		}
		System.out.println(orderId);
		searchModel.setorderId(orderId);
        model.addAttribute("searchModel", searchModel);
        int pageNo = ServletRequestUtils.getIntParameter(request, PageListUtil.PAGE_NO_NAME, PageListUtil.DEFAULT_PAGE_NO);
        int pageSize = ServletRequestUtils.getIntParameter(request, PageListUtil.PAGE_SIZE_NAME, PageListUtil.DEFAULT_PAGE_SIZE);      
        model.addAttribute("contentdetailModel", purchaseDetailService.listPage(searchModel.getorderId(), pageNo, pageSize));
        return "purchase/purchasedetailview";
    }
	//查看订单详情的页面
	@AuthPassport
	@RequestMapping(value = "/purchasedetailviewer/{orderdetailId}", method = {RequestMethod.GET})
	public String purchasedetailviewer(HttpServletRequest request, Model model, @PathVariable(value="orderdetailId") String orderdetailId) throws ValidatException{	
		if(!model.containsAttribute("contentModel")){
			PurchaseDetailEditModel purchaseDetailEditModel = new PurchaseDetailEditModel();
			List<PurchaseDetail> lists=purchaseDetailService.listAll();
			for(PurchaseDetail l:lists) {
				if(l.getorderdetailId().equals(orderdetailId)) { 
					
					purchaseDetailEditModel=PurchaseDetailModelExtension.toPurchaseDetailEditModel(l);
					purchaseDetailEditModel.setId(l.getId());
				}
			}
			//OrderdetailEditModel orderdetailEditModel=OrderdetailModelExtension.toOrderdetailEditModel(orderdetailService.get(id));
			model.addAttribute("contentModel", purchaseDetailEditModel);
		}

        return "purchase/purchasedetailviewer";	
	}
	
	@AuthPassport
	@RequestMapping(value = "/purchasedetailadd/{orderId}", method = RequestMethod.GET)
	public String purchasedetailadd(HttpServletRequest request, Model model,@PathVariable(value="orderId") String orderId/*@RequestParam("orderId") String orderId*/){
		
		if(!model.containsAttribute("contentModel")){
			PurchaseDetailEditModel purchaseDetailEditModel=new PurchaseDetailEditModel();
			purchaseDetailEditModel.setorderId(orderId);
			 
	        String seconds = new SimpleDateFormat("HHmmss").format(new Date());
	        
	        purchaseDetailEditModel.setorderdetailId(orderId+"D"+seconds);
	        purchaseDetailEditModel.setorderId(orderId);
			model.addAttribute("productIds",productService.listproductId());
			
			//System.out.println(productService.getproduct("C001").getproductName());
			model.addAttribute("contentModel", purchaseDetailEditModel);
		}
        return "purchase/purchasedetailadd";	
	}
	
	//获取实时的产品信息
	@AuthPassport
	@RequestMapping(value="/purchasedetailadd/{orderId}/getProductName/{productId}", method = {RequestMethod.POST})
	@ResponseBody
    public void getProductName(HttpServletRequest request,HttpServletResponse response, Model model,@PathVariable(value="orderId") String orderId,@PathVariable(value="productId") String productId) throws IOException{
		
		ObjectMapper mapper = new ObjectMapper();    //提供java-json相互转换功能的类
        String json = mapper.writeValueAsString(productService.getproduct(productId));    //将list中的对象转换为Json格式的数组
        
        //将json数据返回给客户端
        response.setContentType("text/html; charset=utf-8");
        response.getWriter().write(json);
		
	}
	
	@RequestMapping(value="/purchasedetailadd/{orderId}", method = {RequestMethod.POST})
	public String savepurchasedetailadd(HttpServletRequest request, Model model, @Valid @ModelAttribute("contentModel") PurchaseDetailEditModel purchaseDetailEditModel, BindingResult result,@PathVariable(value="orderId") String orderId) throws ValidatException, EntityOperateException, NoSuchAlgorithmException{
		purchaseDetailEditModel.setorderdetailId(request.getParameter("orderdetailId"));
		purchaseDetailEditModel.setproductId(request.getParameter("productId"));
		purchaseDetailEditModel.setproductName(request.getParameter("productName"));
		purchaseDetailEditModel.setquantity(Integer.parseInt(request.getParameter("quantity")));
		purchaseDetailEditModel.setunitPrice(Double.parseDouble(request.getParameter("unitPrice")));
		purchaseDetailEditModel.settotalPrice(Double.parseDouble(request.getParameter("totalPrice")));
		purchaseDetailEditModel.setnote(request.getParameter("note"));
		
		purchaseDetailService.savePurchaseDetail(PurchaseDetailModelExtension.toPurchaseDetail(purchaseDetailEditModel));
        
		String returnUrl = ServletRequestUtils.getStringParameter(request, "returnUrl", null);
        if(returnUrl==null)
        returnUrl="/purchase/purchasedetail/"+orderId;
    	return "redirect:"+returnUrl; 
	}
	
	@AuthPassport
	@RequestMapping(value = "/purchasedetailedit/{orderdetailId}", method = {RequestMethod.GET})
	public String purchasedetailedit(HttpServletRequest request, Model model, @PathVariable(value="orderdetailId") String orderdetailId) throws ValidatException{	
		if(!model.containsAttribute("contentModel")){
			PurchaseDetailEditModel purchaseDetailEditModel = new PurchaseDetailEditModel();
			List<PurchaseDetail> lists=purchaseDetailService.listAll();
			for(PurchaseDetail l:lists) {
				if(l.getorderdetailId().equals(orderdetailId)) { 
					
					purchaseDetailEditModel=PurchaseDetailModelExtension.toPurchaseDetailEditModel(l);
					purchaseDetailEditModel.setId(l.getId());
				}
			}
			model.addAttribute("productIds",productService.listproductId());
			model.addAttribute("contentModel", purchaseDetailEditModel);
		}

        return "purchase/purchasedetailedit";	
	}
	
	//获取实时的产品信息1
		@AuthPassport
		@RequestMapping(value="/purchasedetailedit/{orderdetailId}/getProductName/{productId}", method = {RequestMethod.POST})
		@ResponseBody
	    public void getProductName1(HttpServletRequest request,HttpServletResponse response, Model model,@PathVariable(value="orderdetailId") String orderdetailId,@PathVariable(value="productId") String productId) throws IOException{
			
			ObjectMapper mapper = new ObjectMapper();    //提供java-json相互转换功能的类
	        String json = mapper.writeValueAsString(productService.getproduct(productId));    //将list中的对象转换为Json格式的数组
	        
	        //将json数据返回给客户端
	        response.setContentType("text/html; charset=utf-8");
	        response.getWriter().write(json);
			
		}
	
	@RequestMapping(value="/purchasedetailedit/{orderdetailId}", method = {RequestMethod.POST})
	public String purchasedetailedit(HttpServletRequest request, Model model, @Valid @ModelAttribute("contentModel") PurchaseDetailEditModel purchaseDetailEditModel, BindingResult result,@PathVariable(value="orderdetailId") String orderdetailId) throws ValidatException, EntityOperateException, NoSuchAlgorithmException{
		
    	if(result.hasErrors())
            return purchasedetailedit(request, model, orderdetailId);
		//vendorService.updateVendor(VendorModelExtension.toVendor(editModel.setId(id)));
        String returnUrl = ServletRequestUtils.getStringParameter(request, "returnUrl", null);
        //System.out.println("return_"+returnUrl);
        PurchaseDetail purchaseDetail=PurchaseDetailModelExtension.toPurchaseDetail(purchaseDetailEditModel);
        String id=request.getParameter("id");
        purchaseDetail.setId(Integer.parseInt(id));
        //orderdetail.setorderdetailId(orderdetailId);
        purchaseDetailService.updatePurchaseDetail(purchaseDetail);
        String orderId=request.getParameter("orderId");
    if(returnUrl==null)
        returnUrl="/purchase/purchasedetail/"+orderId;
    	return "redirect:"+returnUrl;  
	}

	@AuthPassport
	@RequestMapping(value = "purchasedetaildelete/{orderdetailId}", method = {RequestMethod.GET})
	public String delete(HttpServletRequest request, Model model, @PathVariable(value="orderdetailId") String orderdetailId) throws ValidatException, EntityOperateException{	
		List<PurchaseDetail> lists=purchaseDetailService.listAll();
		PurchaseDetail purchaseDetail=new PurchaseDetail();
		String orderId=null;
		for(PurchaseDetail l:lists) {
			if(l.getorderdetailId().equals(orderdetailId)) { 
				
				purchaseDetail=l;
				orderId=l.getorderId();
			}
		}
		purchaseDetailService.delete(purchaseDetail.getId());
		String returnUrl = ServletRequestUtils.getStringParameter(request, "returnUrl", null);
		if(returnUrl==null)
        	returnUrl="/purchase/purchasedetail/"+orderId;
        return "redirect:"+returnUrl;	
	
	}
}
