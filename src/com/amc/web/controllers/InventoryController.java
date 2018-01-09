package com.amc.web.controllers;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.amc.model.models.Inventory;
import com.amc.model.models.Product;
import com.amc.service.interfaces.IInventoryService;
import com.amc.web.auth.AuthPassport;
import com.amc.web.jsonmodels.InventoryJson;
import com.amc.web.models.InventoryEditModel;
import com.amc.web.models.InventorySearchModel;
import com.amc.web.models.extension.InventoryModelExtension;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.infrastructure.project.common.exception.EntityOperateException;
import com.infrastructure.project.common.exception.ValidatException;
import com.infrastructure.project.common.utilities.PageListUtil;

@Controller
@RequestMapping(value = "/inventory")
public class InventoryController extends BaseController{
	@Autowired
    @Qualifier("InventoryService")
	private IInventoryService inventoryService;
	
	@AuthPassport
	@RequestMapping(value="/list", method = {RequestMethod.GET})
    public String inventory(HttpServletRequest request, Model model, InventorySearchModel searchModel){
    	model.addAttribute("requestUrl", request.getServletPath());
		model.addAttribute("requestQuery", request.getQueryString());

        model.addAttribute("searchModel", searchModel);
        model.addAttribute("productIds",productService.listproductId());
        int pageNo = ServletRequestUtils.getIntParameter(request, PageListUtil.PAGE_NO_NAME, PageListUtil.DEFAULT_PAGE_NO);
        int pageSize = ServletRequestUtils.getIntParameter(request, PageListUtil.PAGE_SIZE_NAME, PageListUtil.DEFAULT_PAGE_SIZE);      
        model.addAttribute("contentModel", inventoryService.listPage(searchModel.getproductId(), searchModel.getproductName(), pageNo, pageSize));
        return "inventory/list";
    }
	@AuthPassport
	@RequestMapping(value="/listchanging/{id}", method = {RequestMethod.GET})
    public String inventorychanging(HttpServletRequest request, Model model, InventorySearchModel searchModel,@PathVariable(value="id") Integer id){
    	model.addAttribute("requestUrl", request.getServletPath());
		model.addAttribute("requestQuery", request.getQueryString());
		List<Inventory> inventories=inventoryService.listAll();
		String productId="";
		for(Inventory inventory:inventories) {
			if(inventory.getId()==id) productId=inventory.getproductId();
		}
		searchModel.setproductId(productId);
        model.addAttribute("searchModel", searchModel);
        int pageNo = ServletRequestUtils.getIntParameter(request, PageListUtil.PAGE_NO_NAME, PageListUtil.DEFAULT_PAGE_NO);
        int pageSize = ServletRequestUtils.getIntParameter(request, PageListUtil.PAGE_SIZE_NAME, PageListUtil.DEFAULT_PAGE_SIZE);      
        model.addAttribute("contentModel", inventoryService.listAllPage(searchModel.getproductId(), searchModel.getproductName(), pageNo, pageSize));
        return "inventory/listchanging";
    }
	
	@AuthPassport
	@RequestMapping(value="/listchanging/{productId}", method = {RequestMethod.POST})
	@ResponseBody
    public void getlistchangedata(HttpServletRequest request,HttpServletResponse response, Model model, InventorySearchModel searchModel,@PathVariable(value="productId") String productId) throws IOException{
		//response.setContentType("application/json;charset=utf-8");
		//String productId=request.getParameter("productId");
		List<Inventory> inventory=inventoryService.listAll();
		List<Product> product=productService.listAll();
		List<InventoryJson> i=new ArrayList<>();
		int safestock=0;
		for(Product p:product) {
			if(p.getproductId().equals(productId)) safestock=p.getsafeStock();
		}
		
		for(Inventory iv:inventory) {
			if(iv.getproductId().equals(productId)) {
				InventoryJson inventoryjson=new InventoryJson();
				inventoryjson.setproductId(productId);
				inventoryjson.setproductName(iv.getproductName());
				inventoryjson.setinventoryLevel(iv.getinventoryLevel());
				inventoryjson.setsafestock(safestock);
				String time = (new SimpleDateFormat("yyyy-MM-dd HH:mm:ss:SS")).format(iv.getcreateTime().getTime());
				inventoryjson.setcreateTime(time);
				i.add(inventoryjson);
			}
		}
		ObjectMapper mapper = new ObjectMapper();    //提供java-json相互转换功能的类
        
        String json = mapper.writeValueAsString(i);    //将list中的对象转换为Json格式的数组
        
        //将json数据返回给客户端
        response.setContentType("text/html; charset=utf-8");
        response.getWriter().write(json);
		
		}
	
	@AuthPassport
	@RequestMapping(value = "inventoryadd", method = RequestMethod.GET)
	public String inventoryadd(HttpServletRequest request, Model model){	
		if(!model.containsAttribute("contentModel")){
			model.addAttribute("contentModel", new InventoryEditModel());
		}
		model.addAttribute("productIds",productService.listproductId());
        return "inventory/inventoryadd";	
	}
	@RequestMapping(value="/inventoryadd", method = {RequestMethod.POST})
	public String inventoryadd(HttpServletRequest request, Model model, @Valid @ModelAttribute("contentModel") InventoryEditModel inventoryEditModel, BindingResult result) throws ValidatException, EntityOperateException, NoSuchAlgorithmException{
		inventoryEditModel.setcreateTime(Calendar.getInstance());
		inventoryEditModel.setstatus("未知");
		inventoryService.saveInventory(InventoryModelExtension.toInventory(inventoryEditModel));
        
        String returnUrl = ServletRequestUtils.getStringParameter(request, "returnUrl", null);
        if(returnUrl==null)
        	returnUrl="inventory/list";
    	return "redirect:"+returnUrl; 	
	}
	
	//获取实时的产品信息
		@AuthPassport
		@RequestMapping(value="/inventoryadd/getProductName/{productId}", method = {RequestMethod.POST})
		@ResponseBody
	    public void getProductName(HttpServletRequest request,HttpServletResponse response, Model model,@PathVariable(value="productId") String productId) throws IOException{
			
			ObjectMapper mapper = new ObjectMapper();    //提供java-json相互转换功能的类
	        String json = mapper.writeValueAsString(productService.getproduct(productId));    //将list中的对象转换为Json格式的数组
	        
	        //将json数据返回给客户端
	        response.setContentType("text/html; charset=utf-8");
	        response.getWriter().write(json);
			
		}

		//刷新库存状态
		@AuthPassport
		@RequestMapping(value="/inventoryrefresh")
	    public String inventoryrefresh(HttpServletRequest request,HttpServletResponse response) throws IOException, NoSuchAlgorithmException, EntityOperateException, ValidatException{
			
			//如果库存量在产品安全库存量之下，即为“不足”
			//如果库存量在产品安全库存量之上，即为“充足”
			List<Inventory> inventorys=inventoryService.listAll();
			//System.out.println("list1");
			List<Product> product=productService.listAll();
			//System.out.println("list2");
			for(Inventory i:inventorys) {
				String productId = i.getproductId();
				
				int safestock=0;
				for(Product p:product) {
					if(p.getproductId().equals(productId)) safestock=p.getsafeStock();
					System.out.println(safestock);
				}
				if(i.getinventoryLevel()>=safestock) 
					i.setstatus("充足");
				else i.setstatus("不充足");
				inventoryService.updateInventory(i);
			}
			String returnUrl = ServletRequestUtils.getStringParameter(request, "returnUrl", null);
	        if(returnUrl==null)
	        	returnUrl="inventory/list";
	        return "redirect:"+returnUrl; 	
		}
}
