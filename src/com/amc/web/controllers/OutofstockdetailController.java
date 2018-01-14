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

import com.amc.model.models.Orderdetail;
import com.amc.model.models.Outofstock;
import com.amc.model.models.Outofstockdetail;
import com.amc.service.interfaces.IOutofstockdetailService;
import com.amc.web.auth.AuthPassport;
import com.amc.web.models.OrderdetailEditModel;
import com.amc.web.models.OutofstockdetailEditModel;
import com.amc.web.models.OutofstockdetailSearchModel;
import com.amc.web.models.extension.OrderdetailModelExtension;
import com.amc.web.models.extension.OutofstockdetailModelExtension;
import com.infrastructure.project.common.exception.ValidatException;
import com.infrastructure.project.common.utilities.PageListUtil;


@Controller
@RequestMapping(value = "/inventory")

public class OutofstockdetailController extends BaseController{
	@Autowired
    @Qualifier("OutofstockdetailService")
	
	
	private IOutofstockdetailService outofstockdetailService;
	
	
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
        model.addAttribute("id", id);
        int pageNo = ServletRequestUtils.getIntParameter(request, PageListUtil.PAGE_NO_NAME, PageListUtil.DEFAULT_PAGE_NO);
        int pageSize = ServletRequestUtils.getIntParameter(request, PageListUtil.PAGE_SIZE_NAME, PageListUtil.DEFAULT_PAGE_SIZE);      
        model.addAttribute("contentModel", outofstockdetailService.listPage(searchModel.getoutofstockId(),searchModel.getstatus(), pageNo, pageSize));
        return "inventory/outofstockdetailview";
    }
	//查看缺货单详情的页面
		@AuthPassport
		@RequestMapping(value = "/outofstockdetailviewer/{outofstockdetailId}", method = {RequestMethod.GET})
		public String outofstockdetailviewer(HttpServletRequest request, Model model, @PathVariable(value="outofstockdetailId") String outofstockdetailId) throws ValidatException{	
			if(!model.containsAttribute("contentModel")){
				OutofstockdetailEditModel outofstockdetailEditModel = new OutofstockdetailEditModel();
				List<Outofstockdetail> lists=outofstockdetailService.listAll();
				for(Outofstockdetail l:lists) {
					if(l.getoutofstockdetailId().equals(outofstockdetailId)) { 
						
						outofstockdetailEditModel=OutofstockdetailModelExtension.toOutofstockdetailEditModel(l);
						outofstockdetailEditModel.setId(l.getId());
					}
				}
				//OrderdetailEditModel orderdetailEditModel=OrderdetailModelExtension.toOrderdetailEditModel(orderdetailService.get(id));
				model.addAttribute("contentModel", outofstockdetailEditModel);
			}

	        return "inventory/outofstockdetailviewer";	
		}

}
