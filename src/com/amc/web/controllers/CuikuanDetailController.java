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

import com.amc.model.models.Cuikuan;
import com.amc.service.interfaces.ICuikuanDetailService;
import com.amc.web.auth.AuthPassport;
import com.amc.web.models.CuikuanDetailSearchModel;
import com.infrastructure.project.common.utilities.PageListUtil;

@Controller
@RequestMapping(value = "/financial")
public class CuikuanDetailController extends BaseController{
	@Autowired
    @Qualifier("CuikuanDetailService")
	private ICuikuanDetailService cuikuanDetailService;
	
	
	
	@AuthPassport
	@RequestMapping(value="/cuikuandetail/{id}", method = {RequestMethod.GET})
    public String cuikuandetail(HttpServletRequest request, Model model, CuikuanDetailSearchModel searchModel,@PathVariable(value="id") Integer id){
    //System.out.println("id"+id);
		model.addAttribute("requestUrl", request.getServletPath());
		model.addAttribute("requestQuery", request.getQueryString());
		List<Cuikuan> lists=cuikuanService.listAll();
		String cuikuanId=null;
		for(Cuikuan l:lists) {
			if(l.getId()==id) { 
				cuikuanId=l.getCuikuanId();
			}
		}
		
		searchModel.setCuikuanId(cuikuanId);

        model.addAttribute("searchModel", searchModel);
        int pageNo = ServletRequestUtils.getIntParameter(request, PageListUtil.PAGE_NO_NAME, PageListUtil.DEFAULT_PAGE_NO);
        int pageSize = ServletRequestUtils.getIntParameter(request, PageListUtil.PAGE_SIZE_NAME, PageListUtil.DEFAULT_PAGE_SIZE);      
        model.addAttribute("contentModel", cuikuanDetailService.listPage(searchModel.getCuikuanId(), pageNo, pageSize));
        return "financial/cuikuandetail";
    }
	
	
	

}
