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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.portlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.amc.model.models.Orderdetail;
import com.amc.model.models.Product;
import com.amc.service.interfaces.IOrderdetailService;
import com.amc.service.interfaces.IProductService;
import com.amc.web.auth.AuthPassport;
import com.amc.web.models.OrderdetailEditModel;
import com.amc.web.models.OrderdetailSearchModel;
import com.amc.web.models.ProductEditModel;
import com.amc.web.models.ProductSearchModel;
import com.amc.web.models.extension.OrderdetailModelExtension;
import com.amc.web.models.extension.ProductModelExtension;
import com.infrastructure.project.common.exception.EntityOperateException;
import com.infrastructure.project.common.exception.ValidatException;
import com.infrastructure.project.common.utilities.PageListUtil;

@Controller
@RequestMapping(value = "/sales")
public class OrderdetailController extends BaseController{
	@Autowired
    @Qualifier("OrderdetailService")
	private IOrderdetailService orderdetailService;
	
	@AuthPassport
	@RequestMapping(value="/orderdetail/{orderId}", method = {RequestMethod.GET})
    public String orderdetail(HttpServletRequest request, Model model, OrderdetailSearchModel searchModel,@PathVariable(value="orderId") String orderId){
    	model.addAttribute("requestUrl", request.getServletPath());
		model.addAttribute("requestQuery", request.getQueryString());
		//String orderId = request.getParameter("orderId");
		searchModel.setorderId(orderId);
        model.addAttribute("searchModel", searchModel);
        int pageNo = ServletRequestUtils.getIntParameter(request, PageListUtil.PAGE_NO_NAME, PageListUtil.DEFAULT_PAGE_NO);
        int pageSize = ServletRequestUtils.getIntParameter(request, PageListUtil.PAGE_SIZE_NAME, PageListUtil.DEFAULT_PAGE_SIZE);      
        model.addAttribute("contentdetailModel", orderdetailService.listPage(searchModel.getorderId(), pageNo, pageSize));
        return "sales/orderdetail";
    }
	//查看订单详情
	@AuthPassport
	@RequestMapping(value="/orderdetailview/{id}", method = {RequestMethod.GET})
    public String orderdetailview(HttpServletRequest request, Model model, OrderdetailSearchModel searchModel,@PathVariable(value="id") Integer id){
    	model.addAttribute("requestUrl", request.getServletPath());
		model.addAttribute("requestQuery", request.getQueryString());
		//String orderId = request.getParameter("orderId");
		List<Orderdetail> lists=orderdetailService.listAll();
		String orderId=null;
		for(Orderdetail l:lists) {
			if(l.getId()==id) { 
				
				orderId=l.getorderId();
			}
		}
		searchModel.setorderId(orderId);
        model.addAttribute("searchModel", searchModel);
        int pageNo = ServletRequestUtils.getIntParameter(request, PageListUtil.PAGE_NO_NAME, PageListUtil.DEFAULT_PAGE_NO);
        int pageSize = ServletRequestUtils.getIntParameter(request, PageListUtil.PAGE_SIZE_NAME, PageListUtil.DEFAULT_PAGE_SIZE);      
        model.addAttribute("contentdetailModel", orderdetailService.listPage(searchModel.getorderId(), pageNo, pageSize));
        return "sales/orderdetailview";
    }
	//查看订单详情的页面
	@AuthPassport
	@RequestMapping(value = "/orderdetailviewer/{orderdetailId}", method = {RequestMethod.GET})
	public String orderdetailviewer(HttpServletRequest request, Model model, @PathVariable(value="orderdetailId") String orderdetailId) throws ValidatException{	
		if(!model.containsAttribute("contentModel")){
			OrderdetailEditModel orderdetailEditModel = new OrderdetailEditModel();
			List<Orderdetail> lists=orderdetailService.listAll();
			for(Orderdetail l:lists) {
				if(l.getorderdetailId().equals(orderdetailId)) { 
					
					orderdetailEditModel=OrderdetailModelExtension.toOrderdetailEditModel(l);
					orderdetailEditModel.setId(l.getId());
				}
			}
			//OrderdetailEditModel orderdetailEditModel=OrderdetailModelExtension.toOrderdetailEditModel(orderdetailService.get(id));
			model.addAttribute("contentModel", orderdetailEditModel);
		}

        return "sales/orderdetailviewer";	
	}
	
	@AuthPassport
	@RequestMapping(value = "/orderdetailadd/{orderId}", method = RequestMethod.GET)
	public String orderdetailadd(HttpServletRequest request, Model model,@PathVariable(value="orderId") String orderId/*@RequestParam("orderId") String orderId*/){
		
		if(!model.containsAttribute("contentModel")){
			OrderdetailEditModel orderdetailEditModel=new OrderdetailEditModel();
			orderdetailEditModel.setorderId(orderId);
			 
	        String seconds = new SimpleDateFormat("HHmmss").format(new Date());
	        
			orderdetailEditModel.setorderdetailId(orderId+"D"+seconds);
			orderdetailEditModel.setorderId(orderId);
			model.addAttribute("contentModel", orderdetailEditModel);
		}
        return "sales/orderdetailadd";	
	}
	@RequestMapping(value="/orderdetailadd/{orderId}", method = {RequestMethod.POST})
	public String saveorderdetailadd(HttpServletRequest request, Model model, @Valid @ModelAttribute("contentModel") OrderdetailEditModel orderdetailEditModel, BindingResult result,@PathVariable(value="orderId") String orderId) throws ValidatException, EntityOperateException, NoSuchAlgorithmException{
		orderdetailEditModel.setstatus("未完成");
		orderdetailEditModel.setorderdetailId(request.getParameter("orderdetailId"));
		orderdetailEditModel.setproductId(request.getParameter("productId"));
		orderdetailEditModel.setproductName(request.getParameter("productName"));
		orderdetailEditModel.setquantityDemand(Integer.parseInt(request.getParameter("quantityDemand")));
		orderdetailEditModel.setquantitySupplied(Integer.parseInt(request.getParameter("quantitySupplied")));
		orderdetailEditModel.setunitPrice(Double.parseDouble(request.getParameter("unitPrice")));
		orderdetailEditModel.settotalPrice(Double.parseDouble(request.getParameter("totalPrice")));
		orderdetailEditModel.setnote(request.getParameter("note"));
		
		orderdetailService.saveOrderdetail(OrderdetailModelExtension.toOrderdetail(orderdetailEditModel));
        
		String returnUrl = ServletRequestUtils.getStringParameter(request, "returnUrl", null);
        if(returnUrl==null)
        returnUrl="/sales/orderdetail/"+orderId;
    	return "redirect:"+returnUrl; 
	}
	
	@AuthPassport
	@RequestMapping(value = "/orderdetailedit/{orderdetailId}", method = {RequestMethod.GET})
	public String orderdetailedit(HttpServletRequest request, Model model, @PathVariable(value="orderdetailId") String orderdetailId) throws ValidatException{	
		if(!model.containsAttribute("contentModel")){
			OrderdetailEditModel orderdetailEditModel = new OrderdetailEditModel();
			List<Orderdetail> lists=orderdetailService.listAll();
			for(Orderdetail l:lists) {
				if(l.getorderdetailId().equals(orderdetailId)) { 
					
					orderdetailEditModel=OrderdetailModelExtension.toOrderdetailEditModel(l);
					orderdetailEditModel.setId(l.getId());
				}
			}
			//OrderdetailEditModel orderdetailEditModel=OrderdetailModelExtension.toOrderdetailEditModel(orderdetailService.get(id));
			model.addAttribute("contentModel", orderdetailEditModel);
		}

        return "sales/orderdetailedit";	
	}
	
	@RequestMapping(value="/orderdetailedit/{orderdetailId}", method = {RequestMethod.POST})
	public String orderdetailedit(HttpServletRequest request, Model model, @Valid @ModelAttribute("contentModel") OrderdetailEditModel orderdetailEditModel, BindingResult result,@PathVariable(value="orderdetailId") String orderdetailId) throws ValidatException, EntityOperateException, NoSuchAlgorithmException{
		
    	if(result.hasErrors())
            return orderdetailedit(request, model, orderdetailId);
		//vendorService.updateVendor(VendorModelExtension.toVendor(editModel.setId(id)));
        String returnUrl = ServletRequestUtils.getStringParameter(request, "returnUrl", null);
        //System.out.println("return_"+returnUrl);
        Orderdetail orderdetail=OrderdetailModelExtension.toOrderdetail(orderdetailEditModel);
        String id=request.getParameter("id");
        orderdetail.setId(Integer.parseInt(id));
        //orderdetail.setorderdetailId(orderdetailId);
        orderdetailService.updateOrderdetail(orderdetail);
        String orderId=request.getParameter("orderId");
    if(returnUrl==null)
        returnUrl="/sales/orderdetail/"+orderId;
    	return "redirect:"+returnUrl;  
	}

	@AuthPassport
	@RequestMapping(value = "orderdetaildelete/{orderdetailId}", method = {RequestMethod.GET})
	public String delete(HttpServletRequest request, Model model, @PathVariable(value="orderdetailId") String orderdetailId) throws ValidatException, EntityOperateException{	
		List<Orderdetail> lists=orderdetailService.listAll();
		Orderdetail orderdetail=new Orderdetail();
		String orderId=null;
		for(Orderdetail l:lists) {
			if(l.getorderdetailId().equals(orderdetailId)) { 
				
				orderdetail=l;
				orderId=l.getorderId();
			}
		}
		orderdetailService.delete(orderdetail.getId());
		String returnUrl = ServletRequestUtils.getStringParameter(request, "returnUrl", null);
		if(returnUrl==null)
        	returnUrl="/sales/orderdetail/"+orderId;
        return "redirect:"+returnUrl;	
	
	}
}
