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

import com.amc.model.models.Deliver;
import com.amc.model.models.Order;
import com.amc.model.models.Orderdetail;
import com.amc.model.models.Product;
import com.amc.service.interfaces.IDeliverDetailService;
import com.amc.service.interfaces.IOrderService;
import com.amc.service.interfaces.IOrderdetailService;
import com.amc.service.interfaces.IProductService;
import com.amc.web.auth.AuthPassport;
import com.amc.web.models.DeliverDetailSearchModel;
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
@RequestMapping(value = "/inventory")
public class DeliverDetailController extends BaseController{
	@Autowired
    @Qualifier("DeliverDetailService")
	private IDeliverDetailService deliverDetailService;
	//private IOrderService orderService;
	
	
	@AuthPassport
	@RequestMapping(value="/deliverdetailview/{id}", method = {RequestMethod.GET})
    public String deliverdetailview(HttpServletRequest request, Model model, DeliverDetailSearchModel searchModel, @PathVariable(value="id") Integer id){
    	model.addAttribute("requestUrl", request.getServletPath());
		model.addAttribute("requestQuery", request.getQueryString());
		//String orderId = request.getParameter("orderId");
		List<Deliver> lists=deliverService.listAll();
		String deliverId=null;
		for(Deliver l:lists) {
			if(l.getId()==id) { 
				deliverId=l.getDeliverId();
			}
		}
		//System.out.println(orderId);
		searchModel.setDeliverId(deliverId);
        model.addAttribute("searchModel", searchModel);
        int pageNo = ServletRequestUtils.getIntParameter(request, PageListUtil.PAGE_NO_NAME, PageListUtil.DEFAULT_PAGE_NO);
        int pageSize = ServletRequestUtils.getIntParameter(request, PageListUtil.PAGE_SIZE_NAME, PageListUtil.DEFAULT_PAGE_SIZE);      
        model.addAttribute("contentdetailModel", deliverDetailService.listPage(searchModel.getDeliverId(), pageNo, pageSize));
        return "inventory/deliverdetailview";
    }

}
