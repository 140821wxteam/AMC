package com.amc.web.controllers;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.amc.model.models.Deliver;
import com.amc.model.models.Outofstock;
import com.amc.service.interfaces.IDeliverDetailService;
import com.amc.service.interfaces.IDeliverService;
import com.amc.web.auth.AuthPassport;
import com.amc.web.models.DeliverDetailSearchModel;
import com.amc.web.models.OutofstockdetailSearchModel;
import com.infrastructure.project.common.utilities.PageListUtil;

@Controller
@RequestMapping(value = "/inventory")
public class DeliverDetailController extends BaseController{
	@Autowired
    @Qualifier("DeliverDetailService")
	private IDeliverDetailService deliverDetailService;
	
	
	
	@AuthPassport
	@RequestMapping(value="/deliverdetail/{id}", method = {RequestMethod.GET})
    public String deliverdetail(HttpServletRequest request, Model model, DeliverDetailSearchModel searchModel,@PathVariable(value="id") Integer id){
    System.out.println("id"+id);
		model.addAttribute("requestUrl", request.getServletPath());
		model.addAttribute("requestQuery", request.getQueryString());
		List<Deliver> lists=deliverService.listAll();
		String deliverId=null;
		for(Deliver l:lists) {
			if(l.getId()==id) { 
				deliverId=l.getDeliverId();
			}
		}
		
		searchModel.setDeliverId(deliverId);

        model.addAttribute("searchModel", searchModel);
        int pageNo = ServletRequestUtils.getIntParameter(request, PageListUtil.PAGE_NO_NAME, PageListUtil.DEFAULT_PAGE_NO);
        int pageSize = ServletRequestUtils.getIntParameter(request, PageListUtil.PAGE_SIZE_NAME, PageListUtil.DEFAULT_PAGE_SIZE);      
        model.addAttribute("contentModel", deliverDetailService.listPage(searchModel.getDeliverId(), pageNo, pageSize));
        return "inventory/deliverdetailview";
    }
	
	
	

}
