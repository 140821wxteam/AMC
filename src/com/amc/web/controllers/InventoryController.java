package com.amc.web.controllers;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
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
import org.springframework.web.bind.annotation.ResponseBody;

import com.amc.model.models.Inventory;
import com.amc.model.models.Product;
import com.amc.service.interfaces.IInventoryService;
import com.amc.web.auth.AuthPassport;
import com.amc.web.jsonmodels.InventoryJson;
import com.amc.web.models.InventorySearchModel;
import com.fasterxml.jackson.databind.ObjectMapper;
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
/*	
	@AuthPassport
	@RequestMapping(value = "/productadd", method = RequestMethod.GET)
	public String productadd(HttpServletRequest request, Model model){	
		if(!model.containsAttribute("contentModel")){
			model.addAttribute("contentModel", new ProductEditModel());
		}
        return "basedata/productadd";	
	}
	@RequestMapping(value="/productadd", method = {RequestMethod.POST})
	public String productadd(HttpServletRequest request, Model model, @Valid @ModelAttribute("contentModel") ProductEditModel productEditModel, BindingResult result) throws ValidatException, EntityOperateException, NoSuchAlgorithmException{
		      
		productService.saveProduct(ProductModelExtension.toProduct(productEditModel));
        
        String returnUrl = ServletRequestUtils.getStringParameter(request, "returnUrl", null);
        if(returnUrl==null)
        	returnUrl="basedata/product";
    	return "redirect:"+returnUrl; 	
	}
	
	@AuthPassport
	@RequestMapping(value = "/productedit/{id}", method = {RequestMethod.GET})
	public String productedit(HttpServletRequest request, Model model, @PathVariable(value="id") Integer id) throws ValidatException{	
		if(!model.containsAttribute("contentModel")){
			ProductEditModel productEditModel=ProductModelExtension.toProductEditModel(productService.get(id));
			model.addAttribute("contentModel", productEditModel);
		}

        return "basedata/productedit";	
	}
	
	@AuthPassport
	@RequestMapping(value = "/productedit/{id}", method = {RequestMethod.POST})
    public String productedit(HttpServletRequest request, Model model, @Valid @ModelAttribute("contentModel") ProductEditModel editModel, @PathVariable(value="id") Integer id,BindingResult result) throws EntityOperateException, ValidatException, NoSuchAlgorithmException {
		if(result.hasErrors())
            return productedit(request, model, id);
		//vendorService.updateVendor(VendorModelExtension.toVendor(editModel.setId(id)));
        String returnUrl = ServletRequestUtils.getStringParameter(request, "returnUrl", null);
        //System.out.println("return_"+returnUrl);
        Product product=ProductModelExtension.toProduct(editModel);
        product.setId(id);
        productService.updateProduct(product);
        
    if(returnUrl==null)
        returnUrl="basedata/product";
    	return "redirect:"+returnUrl;    
    }
	
	@AuthPassport
	@RequestMapping(value = "/productdelete/{id}", method = {RequestMethod.GET})
	public String delete(HttpServletRequest request, Model model, @PathVariable(value="id") Integer id) throws ValidatException, EntityOperateException{	
		productService.delete(id);
		String returnUrl = ServletRequestUtils.getStringParameter(request, "returnUrl", null);
		if(returnUrl==null)
        	returnUrl="basedata/product";
        return "redirect:"+returnUrl;	
	}
	*/
}
