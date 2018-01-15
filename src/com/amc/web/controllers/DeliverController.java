package com.amc.web.controllers;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;

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
import com.amc.model.models.CuikuanDetail;
import com.amc.model.models.Deliver;
import com.amc.model.models.DeliverDetail;
import com.amc.model.models.Invoice;
import com.amc.model.models.Order;
import com.amc.model.models.Orderdetail;
import com.amc.model.models.Prepare;
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
	
	@AuthPassport
	@RequestMapping(value = "tocuikuan/{id}", method = {RequestMethod.GET})
	public String tocuikuan(HttpServletRequest request,HttpServletResponse response, Model model, @PathVariable(value="id") Integer id) throws ValidatException, EntityOperateException, NoSuchAlgorithmException, IOException{	
		List<Deliver> deliverList=deliverService.listAll();
		Deliver deliver=new Deliver();
		Cuikuan cuikuan=new Cuikuan();
		Prepare prepare=new Prepare();
		CuikuanDetail cuikuanDetail=new CuikuanDetail();

		String date = new SimpleDateFormat("yyyyMMdd").format(new Date());  
        String seconds = new SimpleDateFormat("HHmmss").format(new Date());
        
		for(Deliver de:deliverList){
			if(de.getId()==id){
				deliver=de;			
			}
		}
		for(Prepare pre:prepareService.listAll()){
			if(pre.getprepareId().equals(deliver.getPrepareId())){
				prepare=pre;
			}
		}
		Calendar orderReceiveDate=null;
		for(Order o:orderService.listAll()){
			if(o.getorderId().equals(prepare.getorderId())){
				orderReceiveDate=o.getcreateTime();
			}
		}
		
		if(deliver.getDeliverId()!=null){
			String cuikuanId="CUI"+date+seconds;
			cuikuan.setCuikuanId(cuikuanId);
			cuikuan.setDeliverId(deliver.getDeliverId());
			cuikuan.setCuikuanObjection(1);
			cuikuan.setCustomerId(prepare.getcustomerId());
			cuikuan.setOrderId(prepare.getorderId());
			cuikuan.setOrderReceiveDate(orderReceiveDate);
			cuikuan.setAmountMoney(deliver.getAmountMoney());
			Calendar calendar=Calendar.getInstance();
			cuikuan.setCreateTime(calendar);
			cuikuan.setStatus("未支付");
            cuikuanService.save(cuikuan);
            
            AccountTable at=new AccountTable();
            at.setAccounttableId("AT"+date+seconds);
            at.setCuikuanId(cuikuanId);
            at.setOrderId(prepare.getorderId());
            at.setDeliverId(deliver.getDeliverId());
            at.setCustomerId(prepare.getcustomerId());
            at.setObjection(1);
            at.setReceivable(deliver.getAmountMoney());
            accountTableService.save(at);
            
            for(DeliverDetail dd:deliverDetailService.listAll()){
    			if(dd.getDeliverId().equals(deliver.getDeliverId())){
    				//deliverDetail=dd;
    				cuikuanDetail.setCuikuanId(cuikuan.getCuikuanId());
    	            cuikuanDetail.setProductId(dd.getProductId());
    	            cuikuanDetail.setProductName(dd.getProductName());
    	            cuikuanDetail.setFactoryId(dd.getFactoryId());
    	            cuikuanDetail.setNum(dd.getNum());
    	            Orderdetail orderdetail=new Orderdetail();
    	            for(Orderdetail od:orderdetailService.listAll()){
    	            	if(od.getorderId().equals(prepare.getorderId()) && od.getproductId().equals(dd.getProductId())){
    	            		orderdetail=od;
    	            	}
    	            }
    	            System.out.println(prepare.getorderId()+"----"+dd.getProductId()+"----------------------");
/*    	            Orderdetail orderdetail = orderdetailService.getorderdetailByoIdpId(deliver.getOrderId(), 
    	            		dd.getProductId());
    	            System.out.println("unitPrice"+orderdetail.getunitPrice()+"------------------");
    				double unitPrice = orderdetail.getunitPrice();*/
    				cuikuanDetail.setPrice(orderdetail.getunitPrice());
    				cuikuanDetail.setMoney(orderdetail.getunitPrice()*dd.getNum());
    				cuikuanDetailService.save(cuikuanDetail);
    			}
    		}
            
            deliver.setStatus("已处理");
            deliverService.updateDeliver(deliver);
            
		}
		String returnUrl = ServletRequestUtils.getStringParameter(request, "returnUrl", null);
		if(returnUrl==null)
        	returnUrl="inventory/deliverlist";
        return "redirect:"+returnUrl;	
	
	}
}
