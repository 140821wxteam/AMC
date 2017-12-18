package com.amc.web.controllers;

import java.security.NoSuchAlgorithmException;
import java.text.SimpleDateFormat;
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
import com.amc.service.interfaces.IOrderService;
import com.amc.service.interfaces.IOutofstockService;
import com.amc.service.interfaces.IOutofstockdetailService;
import com.amc.web.auth.AuthPassport;
import com.amc.web.models.OrderEditModel;
import com.amc.web.models.OrderSearchModel;
import com.amc.web.models.OutofstockSearchModel;
import com.amc.web.models.OutofstockdetailSearchModel;
import com.amc.web.models.extension.OrderModelExtension;
import com.infrastructure.project.common.exception.EntityOperateException;
import com.infrastructure.project.common.exception.ValidatException;
import com.infrastructure.project.common.utilities.PageListUtil;


@Controller
@RequestMapping(value = "/inventory")

public class OutofstockdetailController extends BaseController{
	@Autowired
    @Qualifier("OutofstockdetailService")
	
	
	private IOutofstockdetailService outofstockdetailService;
	//private IOrderdetailService orderdetailService;
	
	@AuthPassport
	@RequestMapping(value="/outofstockdetail/{id}", method = {RequestMethod.GET})
    public String outofstockdetail(HttpServletRequest request, Model model, OutofstockdetailSearchModel searchModel,@PathVariable(value="id") Integer id){
    	model.addAttribute("requestUrl", request.getServletPath());
		model.addAttribute("requestQuery", request.getQueryString());
		List<Outofstock> lists=outofstockService.listAll();
		String outofstockId=null;
		for(Outofstock l:lists) {
			if(l.getId()==id) { 
				outofstockId=l.getoutofstockId();
			}
		}
		
		searchModel.setoutofstockId(outofstockId);

        model.addAttribute("searchModel", searchModel);
        int pageNo = ServletRequestUtils.getIntParameter(request, PageListUtil.PAGE_NO_NAME, PageListUtil.DEFAULT_PAGE_NO);
        int pageSize = ServletRequestUtils.getIntParameter(request, PageListUtil.PAGE_SIZE_NAME, PageListUtil.DEFAULT_PAGE_SIZE);      
        model.addAttribute("contentModel", outofstockdetailService.listPage(searchModel.getoutofstockId(),searchModel.getstatus(), pageNo, pageSize));
        return "inventory/outofstockdetailview";
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

}
