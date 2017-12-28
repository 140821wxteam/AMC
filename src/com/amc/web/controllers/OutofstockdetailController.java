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

import com.amc.model.models.Outofstock;
import com.amc.service.interfaces.IOutofstockdetailService;
import com.amc.web.auth.AuthPassport;
import com.amc.web.models.OutofstockdetailSearchModel;
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

}
