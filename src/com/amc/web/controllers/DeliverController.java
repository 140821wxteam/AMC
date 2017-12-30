package com.amc.web.controllers;

import java.security.NoSuchAlgorithmException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;

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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.amc.model.models.Deliver;
import com.amc.model.models.Preparedetail;
import com.amc.service.interfaces.IDeliverService;
import com.amc.service.interfaces.IPrepareService;
import com.amc.service.interfaces.IPreparedetailService;
import com.amc.service.services.PreparedetailService;
import com.amc.web.auth.AuthPassport;
import com.amc.web.models.DeliverDetailSearchModel;
import com.amc.web.models.DeliverSearchModel;
import com.amc.web.models.PrepareEditModel;
import com.amc.web.models.PrepareSearchModel;
import com.amc.web.models.PreparedetailEditModel;
import com.amc.web.models.PreparedetailSearchModel;
import com.amc.web.models.extension.PrepareModelExtension;
import com.amc.web.models.extension.PreparedetailModelExtension;
import com.infrastructure.project.common.exception.EntityOperateException;
import com.infrastructure.project.common.exception.ValidatException;
import com.infrastructure.project.common.utilities.PageListUtil;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

@Controller
@RequestMapping(value = "/inventory")

public class DeliverController extends BaseController{
	@Autowired
    @Qualifier("DeliverService")
	
	
	private IDeliverService deliverService;
	//private IPreparedetailService preparedetailService;
	
	@AuthPassport
	@RequestMapping(value="/deliverlist", method = {RequestMethod.GET})
    public String deliverlist(HttpServletRequest request, Model model, DeliverSearchModel searchModel){
    	model.addAttribute("requestUrl", request.getServletPath());
		model.addAttribute("requestQuery", request.getQueryString());

        model.addAttribute("searchModel", searchModel);
        int pageNo = ServletRequestUtils.getIntParameter(request, PageListUtil.PAGE_NO_NAME, PageListUtil.DEFAULT_PAGE_NO);
        int pageSize = ServletRequestUtils.getIntParameter(request, PageListUtil.PAGE_SIZE_NAME, PageListUtil.DEFAULT_PAGE_SIZE);      
        model.addAttribute("contentModel", deliverService.listPageByPrepare(searchModel.getDeliverId(), searchModel.getCustomerId(), pageNo, pageSize, prepareService));
        return "inventory/deliverlist";
    }
	
	
	
/*	@AuthPassport
	@RequestMapping(value="/preparedetail", method = {RequestMethod.GET})
    public String preparedetail(HttpServletRequest request, Model model, PreparedetailSearchModel searchModel){
    	model.addAttribute("requestUrl", request.getServletPath());
		model.addAttribute("requestQuery", request.getQueryString());

        model.addAttribute("searchModel", searchModel);
        int pageNo = ServletRequestUtils.getIntParameter(request, PageListUtil.PAGE_NO_NAME, PageListUtil.DEFAULT_PAGE_NO);
        int pageSize = ServletRequestUtils.getIntParameter(request, PageListUtil.PAGE_SIZE_NAME, PageListUtil.DEFAULT_PAGE_SIZE);      
        model.addAttribute("contentdetailModel", preparedetailService.listPage(searchModel.getpreparedetailId(), pageNo, pageSize));
        return "inventory/preparedetail";
    }
*/	
	
/*	@AuthPassport
	@RequestMapping(value = "/prepareaddnew", method = RequestMethod.GET)
	public String prepareadd(HttpServletRequest request, Model model) throws NoSuchAlgorithmException, EntityOperateException, ValidatException{	
		if(!model.containsAttribute("contentModel")){
			PrepareEditModel prepareEditModel = new PrepareEditModel();
			//生成订单号
			String date = new SimpleDateFormat("yyyyMMdd").format(new Date());  
	        String seconds = new SimpleDateFormat("HHmmss").format(new Date());
	        
			prepareEditModel.setprepareId("S"+date+seconds);
			//double tp=prepareService.Preparefigure("S"+date+seconds);
			//prepareEditModel.settotalPrice(tp);
			model.addAttribute("contentModel", prepareEditModel);
		}
		
        return "inventory/prepareaddnew";	
	}
	
	@AuthPassport
	@RequestMapping(value = "/preparefigure/{prepareId}", method = RequestMethod.GET)
	public String figure(HttpServletRequest request, Model model,@RequestParam("prepareId") String prepareId,@PathVariable(value="prepareId") String prepareId) throws NoSuchAlgorithmException, EntityOperateException, ValidatException{	
		if(!model.containsAttribute("contentModel")){
			PrepareEditModel prepareEditModel = new PrepareEditModel();
			
			prepareEditModel.setprepareId(prepareId);
			List<Preparedetail> list=preparedetailService.list(prepareId);
			double tp=0;
			for(Preparedetail od:list) {
				tp+=od.gettotalPrice();
			}
			prepareEditModel.settotalPrice(tp);
			model.addAttribute("contentModel", prepareEditModel);
		}
		
        return "inventory/prepareaddnew";	
	}
	//返回按钮
	@AuthPassport
	@RequestMapping(value = "/prepareaddnew/{prepareId}", method = RequestMethod.GET)
	public String prepareaddreturn(HttpServletRequest request, Model model,@PathVariable(value="prepareId") String prepareId){	
		if(!model.containsAttribute("contentModel")){
			PrepareEditModel prepareEditModel = new PrepareEditModel();
			
	        
			prepareEditModel.setprepareId(prepareId);
			model.addAttribute("contentModel", prepareEditModel);
		}
		if(!model.containsAttribute("contentdetailModel")){
			PreparedetailEditModel preparedetailEditModel = new PreparedetailEditModel();
			model.addAttribute("contentdetailModel", preparedetailEditModel);
		}
		
        return "inventory/prepareaddnew";	
	}
	@RequestMapping(value="/prepareaddnew", method = {RequestMethod.POST})	
	public String prepareadd(HttpServletRequest request, Model model, @Valid @ModelAttribute("contentModel") PrepareEditModel prepareEditModel, BindingResult result) throws ValidatException, EntityOperateException, NoSuchAlgorithmException{
		prepareEditModel.setcreateTime(Calendar.getInstance());
		prepareEditModel.setstatus("未完成");
//		String prepareId=prepareEditModel.getprepareId();
//		double tp=0.0;
//		List<Preparedetail> lists=preparedetailService.listAll();
//		
//		for(Preparedetail od:lists) {
//			if(od.getprepareId().equals(prepareId))
//			tp+=od.gettotalPrice();
//		}
//		prepareEditModel.setsumPrice(tp);
//		prepareService.savePrepare(PrepareModelExtension.toPrepare(prepareEditModel));
//		
      String returnUrl = ServletRequestUtils.getStringParameter(request, "returnUrl", null);
        if(returnUrl==null)
        	returnUrl="inventory/prepare";
    	return "redirect:"+returnUrl; 
    
	}*/
}
