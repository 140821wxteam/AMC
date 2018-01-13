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

import com.amc.model.models.Deliver;
import com.amc.model.models.Inventory;
import com.amc.model.models.Order;
import com.amc.model.models.Orderdetail;
import com.amc.model.models.Outofstock;
import com.amc.model.models.Outofstockdetail;
import com.amc.model.models.Prepare;
import com.amc.model.models.Preparedetail;
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
			List<Prepare> lists=prepareService.listAll();
			String prepareId=null;
			String customerId=null;
			Prepare prepare=null;
			for(Prepare p:lists) {
				if(p.getId()==id) {
					if(p.getstatus().equals("已生成发货单")||p.getstatus().equals("待备货")) {
						response.setContentType("text/html; charset=UTF-8");
						PrintWriter out = response.getWriter();
						out.flush();
					    out.println("<script>");
						out.println("alert('不能处理！');");
						out.println("history.back();");
						out.println("</script>");
					}else if(p.getstatus().equals("已备货")){
						System.out.println("test-----------------------");
						prepareId=p.getprepareId();
						customerId=p.getcustomerId();
						prepare=p;
						p.setstatus("已生成发货单");
						prepareService.updatePrepare(p);
						//order.setstatus("已处理");
						//orderService.updateOrder(order);
					}
				}
			}
			
			//System.out.println(orderId+" "+customerId);
			String date = new SimpleDateFormat("yyyyMMdd").format(new Date());  
	        String seconds = new SimpleDateFormat("HHmmss").format(new Date());

	        String deliverId=prepareId+"D"+date+seconds;
	        System.out.println("-----------------"+deliverId);
        
			List<Preparedetail> details=preparedetailService.listAll();
			List<Preparedetail> preparedetail_used=new ArrayList<>();//存储备货单号对应的备货单明细
			int orderNum=0;//订单项目数
			
			int fitNum=0;//订单完全满足项目数
			int partfitNum=0;//订单部分满足项目数
			int outofstockNum=0;//订单完全缺货项目数
			for(Preparedetail pd:details) {
				if(pd.getprepareId().equals(prepareId)) {
					if(pd.getstatus().equals("待备货")) {
						//这里应该有一个提醒的功能，就是待备货的按理说在整个备货单当中应该修改状态为“部分备货”
						fitNum++;
					}
					if(pd.getstatus().equals("已备货")) {
						preparedetail_used.add(pd);
					}
				}
			}
			orderNum = details.size();
			for(Preparedetail pd_used:preparedetail_used) {
				String productId = pd_used.getproductId();
				String productName= pd_used.getproductName();
				String preparedetailId=pd_used.getpreparedetailId();
				System.out.println("productName "+productName);
				System.out.println(inventoryService.listinventory(productId));
				int current_inventoryLevel = inventoryService.listinventory(productId).getinventoryLevel();
//				System.out.println(od_used.getquantityDemand()+" "+current_inventoryLevel);
				//如果该备货单明细的备货数量小于当前产品库存，可以发货
				if(pd_used.getamount()<current_inventoryLevel) {
					fitNum++;
					//生成发货单
					Deliver deliver_detail = new Deliver();
					deliver_detail.setPrepareId(prepareId);
					deliver_detail.setDeliverDetailId(preparedetailId+"D");
					deliver_detail.setDeliverId(deliverId);
					deliver_detail.setCreateTime(Calendar.getInstance());					
					deliver_detail.setStatus("已到货");
					deliver_detail.setReceiveAddr("待填");
					deliver_detail.setReceivePers("待填");
					deliver_detail.setAmountMoney(0.00);
					deliverService.saveDeliver(deliver_detail);//存储备货单
					pd_used.setstatus("已生成发货单");
					System.out.println("fahuo--------------------------------");
				}
					
			}

			String returnUrl = ServletRequestUtils.getStringParameter(request, "returnUrl", null);
			System.out.println(returnUrl);
			//这里是可以打印的
			if(returnUrl==null)
	        	returnUrl="inventory/prepare";
	        return "redirect:"+returnUrl;	
			
			
		//	return searchModelName;	
			
		}		
}
