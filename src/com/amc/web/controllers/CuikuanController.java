package com.amc.web.controllers;

import java.io.IOException;
import java.io.PrintWriter;
import java.security.NoSuchAlgorithmException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
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

import com.amc.model.models.AccountTable;
import com.amc.model.models.Cuikuan;
import com.amc.model.models.Customers;
import com.amc.service.interfaces.ICuikuanService;
import com.amc.web.auth.AuthPassport;
import com.amc.web.models.CuikuanSearchModel;
import com.infrastructure.project.common.exception.EntityOperateException;
import com.infrastructure.project.common.exception.ValidatException;
import com.infrastructure.project.common.utilities.PageListUtil;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

@Controller
@RequestMapping(value = "/financial")

public class CuikuanController extends BaseController{
	@Autowired
    @Qualifier("CuikuanService")
	
	
	private ICuikuanService cuikuanService;
	
	@AuthPassport
	@RequestMapping(value="/cuikuan", method = {RequestMethod.GET})
    public String cuikuan(HttpServletRequest request, Model model, CuikuanSearchModel searchModel){
    	model.addAttribute("requestUrl", request.getServletPath());
		model.addAttribute("requestQuery", request.getQueryString());

        model.addAttribute("searchModel", searchModel);
        int pageNo = ServletRequestUtils.getIntParameter(request, PageListUtil.PAGE_NO_NAME, PageListUtil.DEFAULT_PAGE_NO);
        int pageSize = ServletRequestUtils.getIntParameter(request, PageListUtil.PAGE_SIZE_NAME, PageListUtil.DEFAULT_PAGE_SIZE);      
        model.addAttribute("contentModel", cuikuanService.listPage(searchModel.getCuikuanId(), searchModel.getCustomerId(), pageNo, pageSize));
        return "financial/cuikuan";
    }
	
	@AuthPassport
	@RequestMapping(value = "changereceivable/{id}", method = {RequestMethod.GET})
	public String changereceivable(HttpServletRequest request,HttpServletResponse response, Model model, @PathVariable(value="id") Integer id) throws ValidatException, EntityOperateException, NoSuchAlgorithmException, IOException{	
		List<Cuikuan> cuiList=cuikuanService.listAll();
		//List<AccountTable> atList=accountTableService.listAll();
		String cuikuanId=null;
		int objection=-1;
		double money=-1;
		for(Cuikuan cui:cuiList){
			if(cui.getId()==id  && cui.getCuikuanObjection()==0){
				cuikuanId=cui.getCuikuanId();
				objection=0;
				money=cui.getAmountMoney();
			}
		}
		if(cuikuanId!=null){
			AccountTable at=new AccountTable();
			at.setCuikuanId(cuikuanId);
			at.setObjection(objection);
			at.setPayable(money);;
			accountTableService.save(at);
		}
		String returnUrl = ServletRequestUtils.getStringParameter(request, "returnUrl", null);
		if(returnUrl==null)
        	returnUrl="financial/cuikuan";
        return "redirect:"+returnUrl;	
	
	}
	
	@AuthPassport
	@RequestMapping(value = "changestatus/{id}", method = {RequestMethod.GET})
	public String changestatus(HttpServletRequest request,HttpServletResponse response, Model model, @PathVariable(value="id") Integer id) throws ValidatException, EntityOperateException, NoSuchAlgorithmException, IOException{	
		List<Cuikuan> cuiList=cuikuanService.listAll();
		Cuikuan ck=new Cuikuan();
		String cuikuanId=null;
		int objection=-1;
		
		for(Cuikuan cui:cuiList){
			if(cui.getId()==id  && cui.getStatus().equals("未支付")){
				ck=cui;
				cuikuanId=cui.getCuikuanId();
				objection=cui.getCuikuanObjection();
			}
		}
		if(cuikuanId!=null && objection==0){
			ck.setStatus("已支付");
			cuikuanService.updateCuikuan(ck);
			for(AccountTable at:accountTableService.listAll()){
				if(cuikuanId.equals(at.getCuikuanId())){
					at.setPurchaseBusiness(at.getPayable());
					at.setPayable(0);
					accountTableService.updateAccountTable(at);
				}
			}
		}
		if(cuikuanId!=null && objection==1){
			ck.setStatus("已支付");
			cuikuanService.updateCuikuan(ck);
			for(AccountTable at:accountTableService.listAll()){
				if(cuikuanId==at.getCuikuanId()){
					at.setSalesBusiness(at.getReceivable());
					at.setReceivable(0);
					accountTableService.updateAccountTable(at);
				}
			}
		}
		String returnUrl = ServletRequestUtils.getStringParameter(request, "returnUrl", null);
		if(returnUrl==null)
        	returnUrl="financial/cuikuan";
        return "redirect:"+returnUrl;	
	
	}
	
	@AuthPassport
	@RequestMapping(value = "editreputation/{id}", method = {RequestMethod.GET})
	public String editreputation(HttpServletRequest request,HttpServletResponse response, Model model, @PathVariable(value="id") Integer id) throws ValidatException, EntityOperateException, NoSuchAlgorithmException, IOException{	
		List<Cuikuan> cuiList=cuikuanService.listAll();
		Customers cus=new Customers();
		String customerId=null;
		int reputation=0;

		for(Cuikuan cui:cuiList){
			if(cui.getId()==id){
				customerId=cui.getCustomerId();
			}
		}
		for(Customers customer:customerService.listAll()){
			if(customer.getcustomerId().equals(customerId)){
				cus=customer;
				reputation=customer.getreputation();
			}
		}
		if(customerId!=null){
			//cus.setcustomerId(customerId);
			cus.setreputation(reputation-1);
			customerService.updateCustomer(cus);
		}
		String returnUrl = ServletRequestUtils.getStringParameter(request, "returnUrl", null);
		if(returnUrl==null)
        	returnUrl="financial/cuikuan";
        return "redirect:"+returnUrl;	
	
	}
	
}
