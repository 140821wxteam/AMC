package com.amc.web.controllers;

import java.io.IOException;
import java.io.PrintWriter;
import java.security.NoSuchAlgorithmException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.amc.model.models.Inventory;
import com.amc.model.models.Outofstock;
import com.amc.model.models.Outofstockdetail;
import com.amc.model.models.Prepare;
import com.amc.model.models.Preparedetail;
import com.amc.model.models.PurchaseAdvice;
import com.amc.service.interfaces.IOutofstockService;
import com.amc.web.auth.AuthPassport;
import com.amc.web.models.OutofstockSearchModel;
import com.infrastructure.project.common.exception.EntityOperateException;
import com.infrastructure.project.common.exception.ValidatException;
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
	//缺货单删除
	@AuthPassport
	@RequestMapping(value = "/outofstockdelete/{id}", method = {RequestMethod.GET})
	public String orderdelete(HttpServletRequest request, HttpServletResponse response,Model model, @PathVariable(value="id") Integer id) throws ValidatException, EntityOperateException, IOException{	
		Outofstock outofstock = outofstockService.get(id);
		String outofstockId = outofstock.getoutofstockId();
		List<Outofstockdetail> list = outofstockdetailService.getoutofstockdetaillist(outofstockId);
		for(Outofstockdetail o:list) {
			outofstockdetailService.delete(o);
		}
		outofstockService.delete(id);
		
		
		String returnUrl = ServletRequestUtils.getStringParameter(request, "returnUrl", null);
		if(returnUrl==null)
        	returnUrl="inventory/outofstock";
        return "redirect:"+returnUrl;	
	
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
       
      
        		model.addAttribute("outofstockModel",outofstockModel);
       
//        List<Outofstockdetail> outofstockdetaillist=outofstockdetailService.listAll();
//        List<Outofstockdetail> outofstockdetailModel = new ArrayList<>();
//        for(Outofstockdetail od:outofstockdetaillist) {
//			if(od.getoutofstockId()==outofstockId) {
//				outofstockdetailModel.add(od);
//			}
//		}
        
        /*if(!model.containsAttribute("outofstockdetailModel")){
    			model.addAttribute("outofstockdetailModel",outofstockdetailModel);
        }*/

        return "inventory/printoutofstock";
    }
	//生成采购建议
		@AuthPassport
		@RequestMapping(value="/advice/{id}", method = {RequestMethod.GET})
	    public String advice(HttpServletRequest request,HttpServletResponse response, Model model,@PathVariable(value="id") Integer id) throws ValidatException, EntityOperateException, NoSuchAlgorithmException, IOException{
			Outofstock outofstock = outofstockService.get(id);
			System.out.println("advice");
			if(outofstock.getstatus().equals("已处理")) {
				response.setContentType("text/html; charset=UTF-8");
				PrintWriter out = response.getWriter();
				out.flush();
			    out.println("<script>");
				out.println("alert('不能再处理！');");
				out.println("history.back();");
				out.println("</script>");
			}
			System.out.println("advice");
			String outofstockId = outofstock.getoutofstockId();
			List<Outofstockdetail> outofstockdetails = outofstockdetailService.listAll();
			for(Outofstockdetail o:outofstockdetails) {
				if(o.getoutofstockId().equals(outofstockId)) {
					PurchaseAdvice pa=new PurchaseAdvice();
					
					Inventory inventory = inventoryService.listinventory(o.getproductId());
					String productId = inventory.getproductId();
					int safestock = productService.getsafestock(productId);
					int predict = orderService.getpredict(productId);
					pa.setproductId(inventory.getproductId());
					pa.setproductName(inventory.getproductName());
					pa.setinventoryLevel(inventory.getinventoryLevel());
					pa.setdemand(o.getquantityNeeded());
					//System.out.println(safestock+o.getquantityNeeded()+predict);
					//建议订货量的确定，判断库存水平能否满足安全库存+预测+需求量
					if((safestock+o.getquantityNeeded()+predict)<=inventory.getinventoryLevel()) {
						pa.setadvice(0);
					}
					else {
						pa.setadvice(safestock+o.getquantityNeeded()+predict-inventory.getinventoryLevel());
					}
					
					List<PurchaseAdvice> lists = purchaseadviceService.listAll();
					int flag=0;//初始状态，表示建议表中是否有本产品的建议
					for(PurchaseAdvice p:lists) {
						if(p.getproductId().equals(inventory.getproductId())) {
							flag = 1;
							p.setinventoryLevel(pa.getinventoryLevel());
							p.setdemand(pa.getdemand());
							p.setadvice(pa.getadvice());
							purchaseadviceService.updatePurchaseAdvice(p);
							break;
						}
					}
					if(flag==0) 
						purchaseadviceService.savePurchaseAdvice(pa);
					o.setstatus("已处理");
					outofstockdetailService.updateOutofstockdetail(o);
				}
			}
			outofstock.setstatus("已处理");
			outofstockService.updateOutofstock(outofstock);
			
	        String returnUrl = ServletRequestUtils.getStringParameter(request, "returnUrl", null);

			if(returnUrl==null)
		        returnUrl="inventory/outofstock";
		    	return "redirect:"+returnUrl;
	    }

}
