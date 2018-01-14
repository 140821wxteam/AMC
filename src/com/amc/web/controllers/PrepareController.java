package com.amc.web.controllers;

import java.io.IOException;
import java.io.PrintWriter;
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

import com.amc.model.models.Customers;
import com.amc.model.models.Deliver;
import com.amc.model.models.DeliverDetail;
import com.amc.model.models.Order;
import com.amc.model.models.Orderdetail;
import com.amc.model.models.Prepare;
import com.amc.model.models.Preparedetail;
import com.amc.model.models.Product;
import com.amc.service.interfaces.IPrepareService;
import com.amc.service.interfaces.IPreparedetailService;
import com.amc.service.services.PreparedetailService;
import com.amc.web.auth.AccountAuth;
import com.amc.web.auth.AuthPassport;
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

public class PrepareController extends BaseController{
	@Autowired
    @Qualifier("PrepareService")
	
	
	private IPrepareService prepareService;
	//private IPreparedetailService preparedetailService;
	
	@AuthPassport
	@RequestMapping(value="/prepare", method = {RequestMethod.GET})
    public String prepare(HttpServletRequest request, Model model, PrepareSearchModel searchModel){
    	model.addAttribute("requestUrl", request.getServletPath());
		model.addAttribute("requestQuery", request.getQueryString());

        model.addAttribute("searchModel", searchModel);
        model.addAttribute("customerIds",customerService.listcustomersId());
        int pageNo = ServletRequestUtils.getIntParameter(request, PageListUtil.PAGE_NO_NAME, PageListUtil.DEFAULT_PAGE_NO);
        int pageSize = ServletRequestUtils.getIntParameter(request, PageListUtil.PAGE_SIZE_NAME, PageListUtil.DEFAULT_PAGE_SIZE);      
        model.addAttribute("contentModel", prepareService.listPage(searchModel.getprepareId(), searchModel.getcustomerId(), pageNo, pageSize));
        return "inventory/prepare";
    }
	
	
	@AuthPassport
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
    
	}
	
	@AuthPassport
	@RequestMapping(value = "/preparedelete/{id}", method = {RequestMethod.GET})
	public String orderdelete(HttpServletRequest request, HttpServletResponse response,Model model, @PathVariable(value="id") Integer id) throws ValidatException, EntityOperateException, IOException{	
		Prepare prepare = prepareService.get(id);
		String prepareId = prepare.getprepareId();
		List<Preparedetail> list = preparedetailService.getpreparedetaillist(prepareId);
		for(Preparedetail p:list) {
			preparedetailService.delete(p);
		}
			prepareService.delete(id);
		
		
		String returnUrl = ServletRequestUtils.getStringParameter(request, "returnUrl", null);
		if(returnUrl==null)
        	returnUrl="inventory/prepare";
        return "redirect:"+returnUrl;	
	
	}
	
	//确认备货操作
		@AuthPassport
		@RequestMapping(value = "/prepareconfirm/{id}", method = {RequestMethod.GET})
		public String prepareconfirm(HttpServletRequest request, HttpServletResponse response,Model model, @PathVariable(value="id") Integer id) throws ValidatException, EntityOperateException, NoSuchAlgorithmException, IOException{	
			AccountAuth user=(AccountAuth)request.getSession().getAttribute("accountAuth");
			String username=user.getUsername();
			Prepare prepare=prepareService.get(id);
			String prepareId=prepare.getprepareId();
					if(!prepare.getstatus().equals("待备货")) {
						response.setContentType("text/html; charset=UTF-8");
						PrintWriter out = response.getWriter();
						out.flush();
					    out.println("<script>");
						out.println("alert('不能确认备货！');");
						out.println("history.back();");
						out.println("</script>");
					}else {
						prepare.setstatus("已备货");
						prepareService.updatePrepare(prepare);
					}
				
			
			
			List<Preparedetail> details=preparedetailService.listAll();
			
			for(Preparedetail pd:details) {
				if(pd.getprepareId().equals(prepareId))
				{
					if(!pd.getstatus().equals("待备货")) {
						break;
					}
					pd.setstatus("已备货");
					pd.setpreparePers(username);
					preparedetailService.updatePreparedetail(pd);
				}
					
			}
			String returnUrl = ServletRequestUtils.getStringParameter(request, "returnUrl", null);
			if(returnUrl==null)
	        	returnUrl="inventory/prepare";
	        return "redirect:"+returnUrl;	
		
		}

		//发货操作
		@AuthPassport
		@RequestMapping(value = "/todeliver/{id}", method = {RequestMethod.GET})
		public String todeliver(HttpServletRequest request, HttpServletResponse response,Model model, @PathVariable(value="id") Integer id) throws ValidatException, EntityOperateException, NoSuchAlgorithmException, IOException{
			Prepare prepare = prepareService.get(id);
			if(prepare.getstatus().equals("已生成发货单")) {
				response.setContentType("text/html; charset=UTF-8");
				PrintWriter out = response.getWriter();
				out.flush();
			    out.println("<script>");
				out.println("alert('已发货！');");
				out.println("history.back();");
				out.println("</script>");
			}else
			if(prepare.getstatus().equals("待备货")) {
				response.setContentType("text/html; charset=UTF-8");
				PrintWriter out = response.getWriter();
				out.flush();
			    out.println("<script>");
				out.println("alert('尚未备货！');");
				out.println("history.back();");
				out.println("</script>");
			}else 
			if(prepare.getstatus().equals("已备货")){
				prepare.setstatus("已生成发货单");
				prepareService.updatePrepare(prepare);
				
				List<Preparedetail> pdlist =preparedetailService.getpreparedetaillist(prepare.getprepareId());
				for(Preparedetail pdl:pdlist) {
					pdl.setstatus("已生成发货单");
					preparedetailService.updatePreparedetail(pdl);
				}
				//生成发货单
				Deliver deliver = new Deliver();
				deliver.setDeliverId(prepare.getprepareId()+"DE");
				deliver.setOrderId(prepare.getorderId());
				deliver.setPrepareId(prepare.getprepareId());
				deliver.setCustomerId(prepare.getcustomerId());
				//Customers customer = customerService.getCustomer(prepare.getcustomerId());
				//deliver.setReceiveAddr(customer.getcustomerAddr());
				//deliver.setReceivePers(customer.getcontactPerson());
				deliver.setCreateTime(Calendar.getInstance());
				deliver.setStatus("未处理");
				
				//生成发货单明细
				String prepareId = prepare.getprepareId();
				List<Preparedetail> preparedetaillist = preparedetailService.getpreparedetaillist(prepareId);
				int no = 0;
				double totalPrice = 0;
				for(Preparedetail pd:preparedetaillist) {
					no++;
					DeliverDetail deliverdetail = new DeliverDetail();
					deliverdetail.setDeliverId(prepare.getprepareId()+"DE");
					deliverdetail.setDeliverdetailId(prepare.getprepareId()+"DE"+no);
					deliverdetail.setProductId(pd.getproductId());
					Product product = productService.getproduct(pd.getproductId()).get(0);
					deliverdetail.setProductName(product.getproductName());
					deliverdetail.setFactoryId(product.getproductOrigin());
					deliverdetail.setNum(pd.getamount());
					String orderId = prepare.getorderId();//订单号
					String productId = pd.getproductId();
					Orderdetail orderdetail = orderdetailService.getorderdetailByoIdpId(orderId, productId);
					double unitPrice = orderdetail.getunitPrice();
					int demand = orderdetail.getquantityDemand()-orderdetail.getquantitySupplied();
					if(demand-pd.getamount()<0) deliverdetail.setShortNum(0);
					else deliverdetail.setShortNum(demand-pd.getamount());
					deliverDetailService.save(deliverdetail);
					totalPrice+=unitPrice*pd.getamount();
				}
				
				deliver.setAmountMoney(totalPrice);
				deliverService.save(deliver);
			}
			String returnUrl = ServletRequestUtils.getStringParameter(request, "returnUrl", null);
			
			if(returnUrl==null)
	        	returnUrl="inventory/prepare";
	        return "redirect:"+returnUrl;	
				
		}	




	
}
