package com.amc.web.controllers;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.TreeMap;
import java.util.Map.Entry;

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

import com.amc.model.models.Orderdetail;
import com.amc.model.models.Order;
import com.amc.model.models.Orderdetail;
import com.amc.model.models.Outofstock;
import com.amc.model.models.Outofstockdetail;
import com.amc.model.models.Product;
import com.amc.service.interfaces.IOrderdetailService;
import com.amc.service.interfaces.IOrderService;
import com.amc.service.interfaces.IOutofstockService;
import com.amc.service.interfaces.IOutofstockdetailService;
import com.amc.service.services.OrderService;
import com.amc.web.auth.AuthPassport;
import com.amc.web.jsonmodels.OrderdetailJson;
import com.amc.web.jsonmodels.OrderdetailJson;
import com.amc.web.models.DeliverSearchModel;
import com.amc.web.models.InventorySearchModel;
import com.amc.web.models.OrderdetailSearchModel;
import com.amc.web.models.OrderEditModel;
import com.amc.web.models.OrderSearchModel;
import com.amc.web.models.OrderdetailSearchModel;
import com.amc.web.models.extension.OrderModelExtension;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.infrastructure.project.common.exception.EntityOperateException;
import com.infrastructure.project.common.exception.ValidatException;
import com.infrastructure.project.common.utilities.PageListUtil;


@Controller
@RequestMapping(value = "/sales")

public class OrderController extends BaseController{
	@Autowired
    @Qualifier("OrderService")
	
	
	private IOrderService orderService;
	//private IOrderdetailService orderdetailService;
	
	@AuthPassport
	@RequestMapping(value="/order", method = {RequestMethod.GET})
    public String order(HttpServletRequest request, Model model, OrderSearchModel searchModel){
    	model.addAttribute("requestUrl", request.getServletPath());
		model.addAttribute("requestQuery", request.getQueryString());

        model.addAttribute("searchModel", searchModel);
        int pageNo = ServletRequestUtils.getIntParameter(request, PageListUtil.PAGE_NO_NAME, PageListUtil.DEFAULT_PAGE_NO);
        int pageSize = ServletRequestUtils.getIntParameter(request, PageListUtil.PAGE_SIZE_NAME, PageListUtil.DEFAULT_PAGE_SIZE);      
        model.addAttribute("contentModel", orderService.listPage(searchModel.getorderId(), searchModel.getcustomerId(), searchModel.getstatus(), pageNo, pageSize));
        return "sales/order";
    }
	
	
/*	@AuthPassport
	@RequestMapping(value="/orderdetail", method = {RequestMethod.GET})
    public String orderdetail(HttpServletRequest request, Model model, OrderdetailSearchModel searchModel){
    	model.addAttribute("requestUrl", request.getServletPath());
		model.addAttribute("requestQuery", request.getQueryString());

        model.addAttribute("searchModel", searchModel);
        int pageNo = ServletRequestUtils.getIntParameter(request, PageListUtil.PAGE_NO_NAME, PageListUtil.DEFAULT_PAGE_NO);
        int pageSize = ServletRequestUtils.getIntParameter(request, PageListUtil.PAGE_SIZE_NAME, PageListUtil.DEFAULT_PAGE_SIZE);      
        model.addAttribute("contentdetailModel", orderdetailService.listPage(searchModel.getorderdetailId(), pageNo, pageSize));
        return "sales/orderdetail";
    }
*/	
	
	@AuthPassport
	@RequestMapping(value = "/orderaddnew", method = RequestMethod.GET)
	public String orderadd(HttpServletRequest request, Model model) throws NoSuchAlgorithmException, EntityOperateException, ValidatException{	
		if(!model.containsAttribute("contentModel")){
			OrderEditModel orderEditModel = new OrderEditModel();
			//生成订单号
			String date = new SimpleDateFormat("yyyyMMdd").format(new Date());  
	        String seconds = new SimpleDateFormat("HHmmss").format(new Date());
	        
			orderEditModel.setorderId("S"+date+seconds);
			//double tp=orderService.Orderfigure("S"+date+seconds);
			//orderEditModel.settotalPrice(tp);
			model.addAttribute("contentModel", orderEditModel);
		}
		
        return "sales/orderaddnew";	
	}
	
	/*@AuthPassport
	@RequestMapping(value = "/orderfigure/{orderId}", method = RequestMethod.GET)
	public String figure(HttpServletRequest request, Model model,@RequestParam("orderId") String orderId,@PathVariable(value="orderId") String orderId) throws NoSuchAlgorithmException, EntityOperateException, ValidatException{	
		if(!model.containsAttribute("contentModel")){
			OrderEditModel orderEditModel = new OrderEditModel();
			
			orderEditModel.setorderId(orderId);
			List<Orderdetail> list=orderdetailService.list(orderId);
			double tp=0;
			for(Orderdetail od:list) {
				tp+=od.gettotalPrice();
			}
			orderEditModel.settotalPrice(tp);
			model.addAttribute("contentModel", orderEditModel);
		}
		
        return "sales/orderaddnew";	
	}*/
	//返回按钮
	/*@AuthPassport
	@RequestMapping(value = "/orderaddnew/{orderId}", method = RequestMethod.GET)
	public String orderaddreturn(HttpServletRequest request, Model model,@PathVariable(value="orderId") String orderId){	
		if(!model.containsAttribute("contentModel")){
			OrderEditModel orderEditModel = new OrderEditModel();
			
	        
			orderEditModel.setorderId(orderId);
			model.addAttribute("contentModel", orderEditModel);
		}
		if(!model.containsAttribute("contentdetailModel")){
			OrderdetailEditModel orderdetailEditModel = new OrderdetailEditModel();
			model.addAttribute("contentdetailModel", orderdetailEditModel);
		}
		
        return "sales/orderaddnew";	
	}*/
	@RequestMapping(value="/orderaddnew", method = {RequestMethod.POST})	
	public String orderadd(HttpServletRequest request, Model model, @Valid @ModelAttribute("contentModel") OrderEditModel orderEditModel, BindingResult result) throws ValidatException, EntityOperateException, NoSuchAlgorithmException{
		orderEditModel.setcreateTime(Calendar.getInstance());
		orderEditModel.setstatus("未完成");
		String orderId=orderEditModel.getorderId();
		double tp=0.0;
		List<Orderdetail> lists=orderdetailService.listAll();
		
		for(Orderdetail od:lists) {
			if(od.getorderId().equals(orderId))
			tp+=od.gettotalPrice();
		}
		orderEditModel.settotalPrice(tp);
		orderService.saveOrder(OrderModelExtension.toOrder(orderEditModel));
		
      String returnUrl = ServletRequestUtils.getStringParameter(request, "returnUrl", null);
        if(returnUrl==null)
        	returnUrl="sales/order";
    	return "redirect:"+returnUrl; 
    
	}
	
	@AuthPassport
	@RequestMapping(value = "/orderedit/{id}", method = {RequestMethod.GET})
	public String orderedit(HttpServletRequest request, Model model, @PathVariable(value="id") Integer id) throws ValidatException{	
		if(!model.containsAttribute("contentModel")){
			OrderEditModel orderEditModel=OrderModelExtension.toOrderEditModel(orderService.get(id));
			model.addAttribute("contentModel", orderEditModel);
		}

        return "sales/orderedit";	
	}
	
	@AuthPassport
	@RequestMapping(value = "/orderedit/{id}", method = {RequestMethod.POST})
    public String orderedit(HttpServletRequest request, Model model, @Valid @ModelAttribute("contentModel") OrderEditModel editModel, @PathVariable(value="id") Integer id,BindingResult result) throws EntityOperateException, ValidatException, NoSuchAlgorithmException {
		if(result.hasErrors())
            return orderedit(request, model, id);
		//vendorService.updateVendor(VendorModelExtension.toVendor(editModel.setId(id)));
        String returnUrl = ServletRequestUtils.getStringParameter(request, "returnUrl", null);
        //System.out.println("return_"+returnUrl);
        List<Order> lists=orderService.listAll();
		String orderId=null;
		for(Order o:lists) {
			if(o.getId()==id)
			orderId=o.getorderId();
		}
		
        double tp=0.0;
		List<Orderdetail> details=orderdetailService.listAll();
		
		for(Orderdetail od:details) {
			if(od.getorderId().equals(orderId))
			tp+=od.gettotalPrice();
		}
		editModel.settotalPrice(tp);
		editModel.setcreateTime(Calendar.getInstance());
		editModel.setstatus("未完成");
        Order order=OrderModelExtension.toOrder(editModel);
        order.setId(id);
        orderService.updateOrder(order);
        
    if(returnUrl==null)
        returnUrl="sales/orderedit";
    	return "redirect:"+returnUrl;    
    }
	
	@AuthPassport
	@RequestMapping(value = "orderdelete/{id}", method = {RequestMethod.GET})
	public String orderdelete(HttpServletRequest request, Model model, @PathVariable(value="id") Integer id) throws ValidatException, EntityOperateException{	
		List<Order> lists=orderService.listAll();
		String orderId=null;
		for(Order o:lists) {
			if(o.getId()==id)
			orderId=o.getorderId();
		}
		
		List<Orderdetail> details=orderdetailService.listAll();
		
		for(Orderdetail od:details) {
			if(od.getorderId().equals(orderId))
				orderdetailService.delete(od.getId());
		}
		orderService.delete(id);
		String returnUrl = ServletRequestUtils.getStringParameter(request, "returnUrl", null);
		if(returnUrl==null)
        	returnUrl="sales/order";
        return "redirect:"+returnUrl;	
	
	}
	//订单退回操作
	@AuthPassport
	@RequestMapping(value = "orderback/{id}", method = {RequestMethod.GET})
	public String orderback(HttpServletRequest request, Model model, @PathVariable(value="id") Integer id) throws ValidatException, EntityOperateException, NoSuchAlgorithmException{	
		List<Order> lists=orderService.listAll();
		String orderId=null;
		for(Order o:lists) {
			if(o.getId()==id) {
				orderId=o.getorderId();
				o.setstatus("退回");
				orderService.updateOrder(o);
			}
		}
		
		List<Orderdetail> details=orderdetailService.listAll();
		
		for(Orderdetail od:details) {
			if(od.getorderId().equals(orderId))
			{
				od.setstatus("退回");
				orderdetailService.updateOrderdetail(od);
			}
				
		}
		String returnUrl = ServletRequestUtils.getStringParameter(request, "returnUrl", null);
		if(returnUrl==null)
        	returnUrl="sales/order";
        return "redirect:"+returnUrl;	
	
	}
	
	//订单退回操作
		@AuthPassport
		@RequestMapping(value = "orderconfirm/{id}", method = {RequestMethod.GET})
		public String orderconfirm(HttpServletRequest request, Model model, @PathVariable(value="id") Integer id) throws ValidatException, EntityOperateException, NoSuchAlgorithmException{	
			List<Order> lists=orderService.listAll();
			String orderId=null;
			for(Order o:lists) {
				if(o.getId()==id) {
					orderId=o.getorderId();
					o.setstatus("审核通过");
					orderService.updateOrder(o);
				}
			}
			
			List<Orderdetail> details=orderdetailService.listAll();
			
			for(Orderdetail od:details) {
				if(od.getorderId().equals(orderId))
				{
					od.setstatus("审核通过");
					orderdetailService.updateOrderdetail(od);
				}
					
			}
			String returnUrl = ServletRequestUtils.getStringParameter(request, "returnUrl", null);
			if(returnUrl==null)
	        	returnUrl="sales/order";
	        return "redirect:"+returnUrl;	
		
		}
		
	
	//订单审核通过后进行处理，与库存状况比较，生成备货单，缺货单等
	@AuthPassport
	@RequestMapping(value = "orderinprocess/{id}", method = {RequestMethod.GET})
	public String orderinprocess(HttpServletRequest request, Model model, @PathVariable(value="id") Integer id) throws ValidatException, EntityOperateException, NoSuchAlgorithmException{	
		System.out.println("处理！");
		List<Order> lists=orderService.listAll();
		String orderId=null;
		String customerId=null;
		for(Order o:lists) {
			if(o.getId()==id) {
				orderId=o.getorderId();
				customerId=o.getcustomerId();
			}
		}
		System.out.println(orderId+" "+customerId);
		Outofstock  outofstock = new Outofstock();//用于新建订单缺货单
		//String date = new SimpleDateFormat("yyyyMMdd").format(new Date());  
        //String seconds = new SimpleDateFormat("HHmmss").format(new Date());
        String outofstockId=orderId+"OOS";
        System.out.println(outofstockId);
        outofstock.setoutofstockId(outofstockId);//随机生成订单缺货单编号
        outofstock.setorderId(orderId);
        outofstock.setcustomerId(customerId);
        outofstock.setcreateTime(Calendar.getInstance());
        outofstock.setstatus("处理中");
		List<Orderdetail> details=orderdetailService.listAll();
		List<Orderdetail> orderdetail_used=new ArrayList<>();//存储订单号对应的订单明细
		int orderNum=0;//订单项目数
		
		int fitNum=0;//订单完全满足项目数
		int partfitNum=0;//订单部分满足项目数
		int outofstockNum=0;//订单完全缺货项目数
		for(Orderdetail od:details) {
			if(od.getorderId().equals(orderId))
				orderdetail_used.add(od);				
		}
		orderNum = orderdetail_used.size();
		System.out.println("orderNum "+orderNum);
		outofstock.setorderNum(orderNum);
		for(Orderdetail od_used:orderdetail_used) {
			String productId = od_used.getproductId();
			System.out.println("productId "+productId);
			System.out.println(inventoryService.listinventory(productId));
			int current_inventoryLevel = inventoryService.listinventory(productId).getinventoryLevel();
			System.out.println(od_used.getquantityDemand()+" "+current_inventoryLevel);
			//如果该订单明细的产品需求量小于当前产品库存，则证明订单完全满足项目数应该+1
			if(od_used.getquantityDemand()<current_inventoryLevel)
				fitNum++;
			//如果该订单明细的产品需求量大于当前产品库存，而且当前产品库存不为0，则证明订单部分满足项目数应该+1；并且应该在缺货单明细中添加信息
			if(od_used.getquantityDemand()>current_inventoryLevel && current_inventoryLevel>0) {
				partfitNum++;
				Outofstockdetail outofstockdetail = new Outofstockdetail();
				outofstockdetail.setoutofstockId(outofstockId);
				outofstockdetail.setoutofstockdetailId(outofstockId+"D"+new SimpleDateFormat("HHmmss").format(new Date()));
				outofstockdetail.setproductId(productId);
				outofstockdetail.setquantityDemand(od_used.getquantityDemand());
				outofstockdetail.setquantitySupplied(current_inventoryLevel);
				outofstockdetail.setquantityNeeded(od_used.getquantityDemand()-current_inventoryLevel);
				outofstockdetail.setoperateTime(Calendar.getInstance());
				outofstockdetail.setstatus("待处理");
				outofstockdetailService.saveOutofstockdetail(outofstockdetail);
			}
			//如果该订单明细的产品需求量大于当前产品库存，而且当前产品库存0，则证明订单完全缺货项目数应该+1
			if(od_used.getquantityDemand()>current_inventoryLevel && current_inventoryLevel==0) {
				outofstockNum++;	
				Outofstockdetail outofstockdetail = new Outofstockdetail();
				outofstockdetail.setoutofstockId(outofstockId);
				outofstockdetail.setoutofstockdetailId(outofstockId+"D"+new SimpleDateFormat("HHmmss").format(new Date()));
				outofstockdetail.setproductId(productId);
				outofstockdetail.setquantityDemand(od_used.getquantityDemand());
				outofstockdetail.setquantitySupplied(current_inventoryLevel);
				outofstockdetail.setquantityNeeded(od_used.getquantityDemand()-current_inventoryLevel);
				outofstockdetail.setoperateTime(Calendar.getInstance());
				outofstockdetail.setstatus("待处理");
				outofstockdetailService.saveOutofstockdetail(outofstockdetail);
			}
				
		}
		System.out.println(fitNum);
		System.out.println(partfitNum);
		
		outofstock.setfitNum(fitNum);
		outofstock.setpartfitNum(partfitNum);
		outofstock.setoutofstockNum(outofstockNum);
		if(partfitNum!=0 || outofstockNum!=0) outofstockService.saveOutofstock(outofstock);
		String returnUrl = ServletRequestUtils.getStringParameter(request, "returnUrl", null);
		if(returnUrl==null)
        	returnUrl="sales/order";
        return "redirect:"+returnUrl;	
	
	}
	
	
//	@AuthPassport
//	@RequestMapping(value="/salepredict", method = {RequestMethod.GET})
//    public void getsalepredict(HttpServletRequest request,HttpServletResponse response, Model model,OrderdetailSearchModel searchModel, String productId,String productName) throws IOException{
//    	model.addAttribute("requestUrl", request.getServletPath());
//		model.addAttribute("requestQuery", request.getQueryString());
//
//        model.addAttribute("searchModel", searchModel);
//        int pageNo = ServletRequestUtils.getIntParameter(request, PageListUtil.PAGE_NO_NAME, PageListUtil.DEFAULT_PAGE_NO);
//        int pageSize = ServletRequestUtils.getIntParameter(request, PageListUtil.PAGE_SIZE_NAME, PageListUtil.DEFAULT_PAGE_SIZE);      
//        model.addAttribute("contentModel", orderService.listsalebyOrderdetail(productId, productName, pageNo, pageSize, orderdetailService));
//        //(searchModel.getDeliverId(), searchModel.getCustomerId(), pageNo, pageSize, prepareService));
//		List<OrderdetailJson> i=new ArrayList<>();
//		i=orderService.listsalebyOrderdetail(productId, productName, pageNo, pageSize, orderdetailService);
//		ObjectMapper mapper = new ObjectMapper();    //提供java-json相互转换功能的类
//        
//        String json = mapper.writeValueAsString(i);    //将list中的对象转换为Json格式的数组
//        
//        //将json数据返回给客户端
//        response.setContentType("text/html; charset=utf-8");
//        response.getWriter().write(json);
//		
//    }
	
	@AuthPassport
	@RequestMapping(value="/listchanging/{id}", method = {RequestMethod.GET})
    public String salechanging(HttpServletRequest request, Model model, OrderdetailSearchModel searchModel,@PathVariable(value="id") Integer id){
    	model.addAttribute("requestUrl", request.getServletPath());
		model.addAttribute("requestQuery", request.getQueryString());
		List<Orderdetail> sales=orderdetailService.listAll();
		String productId="";
		for(Orderdetail sale:sales) {
			if(sale.getId()==id) productId=sale.getproductId();
		}
		searchModel.setproductId(productId);
        model.addAttribute("searchModel", searchModel);
        int pageNo = ServletRequestUtils.getIntParameter(request, PageListUtil.PAGE_NO_NAME, PageListUtil.DEFAULT_PAGE_NO);
        int pageSize = ServletRequestUtils.getIntParameter(request, PageListUtil.PAGE_SIZE_NAME, PageListUtil.DEFAULT_PAGE_SIZE);      
        model.addAttribute("contentModel", orderdetailService.listAllPage(searchModel.getproductId(), searchModel.getproductName(), pageNo, pageSize));
        return "sales/listchanging";
    }
	
	@AuthPassport
	@RequestMapping(value="/listchanging/{productId}", method = {RequestMethod.POST})
	@ResponseBody
    public void getlistchangedata(HttpServletRequest request,HttpServletResponse response, Model model, OrderdetailSearchModel searchModel,@PathVariable(value="productId") String productId) throws IOException{
		System.out.println("test-----------------------------");
		//这个打印出来了
		//response.setContentType("application/json;charset=utf-8");
		//String productId=request.getParameter("productId");
//		List<Orderdetail> orderdetail=orderdetailService.listAll();
//		List<Product> product=productService.listAll();
//		List<OrderdetailJson> i=new ArrayList<>();
//		int safestock=0;
//		for(Product p:product) {
//			if(p.getproductId().equals(productId)) safestock=p.getsafeStock();
//		}
//		
//		for(Orderdetail iv:inventory) {
//			if(iv.getproductId().equals(productId)) {
//				OrderdetailJson inventoryjson=new OrderdetailJson();
//				inventoryjson.setproductId(productId);
//				inventoryjson.setproductName(iv.getproductName());
//				inventoryjson.setinventoryLevel(iv.getinventoryLevel());
//				inventoryjson.setsafestock(safestock);
//				String time = (new SimpleDateFormat("yyyy-MM-dd HH:mm:ss:SS")).format(iv.getcreateTime().getTime());
//				inventoryjson.setcreateTime(time);
//				i.add(inventoryjson);
//			}
//		}
		
        Calendar now=Calendar.getInstance();
        int year=now.get(Calendar.YEAR);
		int month=now.get(Calendar.MONTH)+1;//month是从0开始的
		int day=now.get(Calendar.DATE);
		int time=year*10000+month*100+day;
        System.out.println("time  "+time);
        List<Orderdetail> itemsPre = orderdetailService.listAll();
       
        List<OrderdetailJson> result=new ArrayList<>();
        //我决定还是一个月一个月推荐,有一个简单的方法是由于orderdetailId的格式中前几位为时间，因此可以直接根据id来计算
        int allamount=0;
//        HashMap<String,Integer> sales=new HashMap<String,Integer>();
        TreeMap<String, Integer> sales = new TreeMap<String, Integer>();
//                new Comparator<String>() {
//                    public int compare(String obj1, String obj2) {
//                        // 降序排序
//                        return obj2.compareTo(obj1);
//                    }
//                });
        
        ArrayList<Integer> chalist=new ArrayList<>();
        
        for(Orderdetail item:itemsPre){
        	System.out.println("item-----------------------------");
 	        System.out.println(item.getproductId());
 	        System.out.println("item-----------------------------");	
        	if(item.getproductId().equals(productId)){
      	
        	int thistime=Integer.parseInt(item.getorderdetailId().substring(1,9));
        	int thisyear=thistime/10000;
//        	System.out.println("thisyear   "+thisyear);
        	int thismonth=(thistime-thisyear*10000)/100;
//        	System.out.println("thismonth   "+thismonth);
        	int thisday=thistime%100;
//        	System.out.println("thisday   "+thisday);

//        	int yue=(time-thistime)/100;
        	//计算与当前时间隔了多少个月
        	int chayear=year-thisyear;
        	int cha=chayear*12+month-thismonth;
   	
        	if(cha<12 && cha>=0){
        		chalist.add(cha);
        		if(sales.containsKey(String.valueOf(thistime/100))){
        			sales.put(String.valueOf(thistime/100), sales.get(String.valueOf(thistime/100))+item.getquantityDemand());
        			System.out.println(thistime/100+"  "+item.getquantityDemand());
        		}
        		else{
        			sales.put(String.valueOf(thistime/100), item.getquantityDemand());
        			System.out.println(thistime/100+"  "+item.getquantityDemand());
        		}
        	}
        	}
        }
//可能有的月份销量是0，因此要对其进行填充
        for(int m=0;m<12;m++){
        	if(!chalist.contains(m)){
        		int a=(month+12-m)/12;
        		int b=(month+12-m)%12;
        		if(a==1){
        			sales.put(String.valueOf(year*100+b),0);
        		}else if(a==0){
        			sales.put(String.valueOf((year-1)*100+b),0);
        		}
        	}
        }
        for(Entry map:sales.entrySet()){
            OrderdetailJson orderdetailjson=new OrderdetailJson();
            orderdetailjson.setProductId(productId);
//            orderdetailjson.setProductName(productName);
            orderdetailjson.setCreateTime(map.getKey().toString());
            orderdetailjson.setSaleLevel(Integer.parseInt(map.getValue().toString()));
            System.out.println(orderdetailjson.getProductId()+"  "+orderdetailjson.getCreateTime()
            +"  "+orderdetailjson.getSaleLevel());
            result.add(orderdetailjson);
        }
        int m=1;
        
        for(int presale:this.predict(sales)){
        	System.out.println("presale  "+presale);
        	 OrderdetailJson orderdetailjson=new OrderdetailJson();
             orderdetailjson.setProductId(productId);
//             orderdetailjson.setProductName(productName);
             orderdetailjson.setCreateTime(String.valueOf((year+(month+m)/12)*100+(month+m)%12));
             orderdetailjson.setSaleLevel(presale);
             result.add(orderdetailjson);
             m++;
        }
		
		
		ObjectMapper mapper = new ObjectMapper();    //提供java-json相互转换功能的类
        
        String json = mapper.writeValueAsString(result);    //将list中的对象转换为Json格式的数组
        
        //将json数据返回给客户端
        response.setContentType("text/html; charset=utf-8");
        response.getWriter().write(json);
		
		}
	
	public static int[] predict(TreeMap<String, Integer> yearsale){
		System.out.println("predict begin");
		int[] predictlist = new int[2];
		//3个销售周期的加权移动个平均，加权数分别为3,2,1
		int[] input=new int[12];
		int i=0;
		for(Entry entry:yearsale.entrySet()){
			input[i]=(int) entry.getValue();
//			System.out.println("pre i  "+input[i]);
			i++;
		}
		if(input.length>2){
			int a=input[input.length-1];
			int b=input[input.length-2];
			int c=input[input.length-3];
			int pre1=(a*3+b*2+c*1)/6;
			System.out.println(pre1);
			predictlist[0]=pre1;
			int pre2=(pre1*3+a*2+b*1)/6;
			predictlist[1]=pre2;
			System.out.println(pre2);
		}
		else{
			predictlist[0]=input[input.length-1];
		}
		return predictlist;
	}
	
	@AuthPassport
	@RequestMapping(value="/list", method = {RequestMethod.GET})
    public String sales(HttpServletRequest request, Model model, OrderdetailSearchModel searchModel){
    	model.addAttribute("requestUrl", request.getServletPath());
		model.addAttribute("requestQuery", request.getQueryString());

        model.addAttribute("searchModel", searchModel);
        int pageNo = ServletRequestUtils.getIntParameter(request, PageListUtil.PAGE_NO_NAME, PageListUtil.DEFAULT_PAGE_NO);
        int pageSize = ServletRequestUtils.getIntParameter(request, PageListUtil.PAGE_SIZE_NAME, PageListUtil.DEFAULT_PAGE_SIZE);      
        model.addAttribute("contentModel", orderdetailService.listAllPage(searchModel.getproductId(), searchModel.getproductName(), pageNo, pageSize));
        return "sales/list";
    }
}
