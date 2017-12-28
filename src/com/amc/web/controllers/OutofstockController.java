package com.amc.web.controllers;

import java.util.ArrayList;
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
import com.amc.model.models.Outofstockdetail;
import com.amc.service.interfaces.IOutofstockService;
import com.amc.service.interfaces.IOutofstockdetailService;
import com.amc.web.auth.AuthPassport;
import com.amc.web.models.OutofstockSearchModel;
import com.infrastructure.project.common.utilities.PageListUtil;


@Controller
@RequestMapping(value = "/inventory")

public class OutofstockController extends BaseController{
	@Autowired
    @Qualifier("OutofstockService")
	
	
	private IOutofstockService outofstockService;
	//private IOutofstockdetailService outofstockdetailService;
	
	@AuthPassport
	@RequestMapping(value="/outofstock", method = {RequestMethod.GET})
    public String outofstock(HttpServletRequest request, Model model, OutofstockSearchModel searchModel){
    	model.addAttribute("requestUrl", request.getServletPath());
		model.addAttribute("requestQuery", request.getQueryString());

        model.addAttribute("searchModel", searchModel);
        int pageNo = ServletRequestUtils.getIntParameter(request, PageListUtil.PAGE_NO_NAME, PageListUtil.DEFAULT_PAGE_NO);
        int pageSize = ServletRequestUtils.getIntParameter(request, PageListUtil.PAGE_SIZE_NAME, PageListUtil.DEFAULT_PAGE_SIZE);      
        model.addAttribute("contentModel", outofstockService.listPage(searchModel.getoutofstockId(),searchModel.getstatus(), pageNo, pageSize));
        return "inventory/outofstock";
    }
	
	//打印订单缺货单
	@AuthPassport
	@RequestMapping(value="/printoutofstock/{id}", method = {RequestMethod.GET})
    public String printoutofstock(HttpServletRequest request, Model model,@PathVariable(value="id") Integer id){
		System.out.println("打印");
		List<Outofstock> outofstocklist=outofstockService.listAll();
        Outofstock outofstockModel=new Outofstock();
        String outofstockId = null;
        for(Outofstock o:outofstocklist) {
			if(o.getId()==id) {
				outofstockModel=o;
				outofstockId = o.getoutofstockId();
			}
		}
       
        if(!model.containsAttribute("outofstockModel")){
        		model.addAttribute("outofstockModel",outofstockModel);
        }
        List<Outofstockdetail> outofstockdetaillist=outofstockdetailService.listAll();
        List<Outofstockdetail> outofstockdetailModel = new ArrayList<>();
        for(Outofstockdetail od:outofstockdetaillist) {
			if(od.getoutofstockId()==outofstockId) {
				outofstockdetailModel.add(od);
			}
		}
        
        if(!model.containsAttribute("outofstockdetailModel")){
    			model.addAttribute("outofstockdetailModel",outofstockdetailModel);
        }

        return "inventory/printoutofstock";
    }
	

}
