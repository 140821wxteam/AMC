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

import com.amc.model.models.Outofstock;
import com.amc.model.models.Outofstockdetail;
import com.amc.model.models.Prepare;
import com.amc.model.models.Preparedetail;
import com.amc.model.models.Product;
import com.amc.service.interfaces.IPreparedetailService;
import com.amc.service.interfaces.IProductService;
import com.amc.web.auth.AuthPassport;
import com.amc.web.models.OutofstockdetailEditModel;
import com.amc.web.models.OutofstockdetailSearchModel;
import com.amc.web.models.PreparedetailEditModel;
import com.amc.web.models.PreparedetailSearchModel;
import com.amc.web.models.ProductEditModel;
import com.amc.web.models.ProductSearchModel;
import com.amc.web.models.extension.OutofstockdetailModelExtension;
import com.amc.web.models.extension.PreparedetailModelExtension;
import com.amc.web.models.extension.ProductModelExtension;
import com.infrastructure.project.common.exception.EntityOperateException;
import com.infrastructure.project.common.exception.ValidatException;
import com.infrastructure.project.common.utilities.PageListUtil;

@Controller
@RequestMapping(value = "/inventory")
public class PreparedetailController extends BaseController{
	@Autowired
    @Qualifier("PreparedetailService")
	private IPreparedetailService preparedetailService;
	
	@AuthPassport
	@RequestMapping(value = "/preparedetailadd/{prepareId}", method = RequestMethod.GET)
	public String preparedetailadd(HttpServletRequest request, Model model,@PathVariable(value="prepareId") String prepareId/*@RequestParam("prepareId") String prepareId*/){
		
		if(!model.containsAttribute("contentModel")){
			PreparedetailEditModel preparedetailEditModel=new PreparedetailEditModel();
			preparedetailEditModel.setprepareId(prepareId);
			 
	        String seconds = new SimpleDateFormat("HHmmss").format(new Date());
	        
			preparedetailEditModel.setpreparedetailId(prepareId+"D"+seconds);
			preparedetailEditModel.setprepareId(prepareId);
			model.addAttribute("contentModel", preparedetailEditModel);
		}
        return "inventory/preparedetailadd";	
	}
	@RequestMapping(value="/preparedetailadd/{prepareId}", method = {RequestMethod.POST})
	public String savepreparedetailadd(HttpServletRequest request, Model model, @Valid @ModelAttribute("contentModel") PreparedetailEditModel preparedetailEditModel, BindingResult result,@PathVariable(value="prepareId") String prepareId) throws ValidatException, EntityOperateException, NoSuchAlgorithmException{
		preparedetailEditModel.setstatus("未完成");
		preparedetailEditModel.setpreparedetailId(request.getParameter("preparedetailId"));
		preparedetailEditModel.setproductId(request.getParameter("productId"));
		preparedetailEditModel.setproductName(request.getParameter("productName"));
		preparedetailEditModel.setamount(Integer.parseInt(request.getParameter("amount")));
		preparedetailEditModel.setsize(request.getParameter("size"));
		preparedetailEditModel.setpreparePers(request.getParameter("preparePers"));
		preparedetailEditModel.setnote(request.getParameter("note"));
		
		preparedetailService.savePreparedetail(PreparedetailModelExtension.toPreparedetail(preparedetailEditModel));
        
		String returnUrl = ServletRequestUtils.getStringParameter(request, "returnUrl", null);
        if(returnUrl==null)
        returnUrl="/inventory/preparedetail/"+prepareId;
    	return "redirect:"+returnUrl; 
	}
	
	@AuthPassport
	@RequestMapping(value="/preparedetail/{id}", method = {RequestMethod.GET})
    public String preparedetail(HttpServletRequest request, Model model, PreparedetailSearchModel searchModel,@PathVariable(value="id") Integer id) throws ValidatException{
    	model.addAttribute("requestUrl", request.getServletPath());
		model.addAttribute("requestQuery", request.getQueryString());
		Prepare prepare=prepareService.get(id);
		String prepareId=prepare.getprepareId();
		
		searchModel.setprepareId(prepareId);

        model.addAttribute("searchModel", searchModel);
        int pageNo = ServletRequestUtils.getIntParameter(request, PageListUtil.PAGE_NO_NAME, PageListUtil.DEFAULT_PAGE_NO);
        int pageSize = ServletRequestUtils.getIntParameter(request, PageListUtil.PAGE_SIZE_NAME, PageListUtil.DEFAULT_PAGE_SIZE);      
        model.addAttribute("contentModel", preparedetailService.listPage(searchModel.getprepareId(), pageNo, pageSize));
        return "inventory/preparedetail";
    }
	
	//查看备货单详情的页面
			@AuthPassport
			@RequestMapping(value = "/preparedetailviewer/{preparedetailId}", method = {RequestMethod.GET})
			public String preparedetailviewer(HttpServletRequest request, Model model, @PathVariable(value="preparedetailId") String preparedetailId) throws ValidatException{	
				if(!model.containsAttribute("contentModel")){
					PreparedetailEditModel preparedetailEditModel = new PreparedetailEditModel();
					List<Preparedetail> lists=preparedetailService.listAll();
					for(Preparedetail l:lists) {
						if(l.getpreparedetailId().equals(preparedetailId)) { 
							
							preparedetailEditModel=PreparedetailModelExtension.toPreparedetailEditModel(l);
							preparedetailEditModel.setId(l.getId());
						}
					}
					//OrderdetailEditModel orderdetailEditModel=OrderdetailModelExtension.toOrderdetailEditModel(orderdetailService.get(id));
					model.addAttribute("contentModel", preparedetailEditModel);
				}

		        return "inventory/preparedetailviewer";	
			}

}
