package com.amc.service.services;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.util.TreeMap;

import org.hibernate.Criteria;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.mapping.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;

import com.amc.dao.IOrderDao;
import com.amc.model.models.Deliver;
import com.amc.model.models.Inventory;
import com.amc.model.models.Order;
import com.amc.model.models.Orderdetail;
import com.amc.model.models.Prepare;
import com.amc.service.interfaces.IOrderService;
import com.amc.service.interfaces.IOrderdetailService;
import com.amc.web.jsonmodels.OrderdetailJson;
import com.amc.web.models.OrderdetailSearchModel;
import com.infrastructure.project.base.service.services.EnableEntityService;
import com.infrastructure.project.common.exception.EntityOperateException;
import com.infrastructure.project.common.exception.ValidatException;
import com.infrastructure.project.common.utilities.PageList;
import com.infrastructure.project.common.utilities.PageListUtil;;

@Service("OrderService")
public class OrderService extends EnableEntityService<Integer, Order, IOrderDao> implements IOrderService {
	
	/*@Autowired
    @Qualifier("AuthorityService")
	protected IAuthorityService authorityService;*/
	
	@Autowired
    @Qualifier("OrderdetailService")
	protected IOrderdetailService orderdetailService;
	/*
	@Autowired
    @Qualifier("OrganizationService")
	protected IOrganizationService organizationService;
	*/
	@Autowired
	public OrderService(@Qualifier("OrderDao")IOrderDao orderDao){	
		super(orderDao);
	}
	
	@Override
	@SuppressWarnings("unchecked")
	public PageList<Order> listPage(String orderId, String customerId, String status, int pageNo, int pageSize) {		
		Criteria countCriteria = entityDao.getCriteria();	
		Criteria listCriteria = entityDao.getCriteria();
		
		if(orderId!=null && !orderId.isEmpty()){
			countCriteria.add(Restrictions.eq("orderId", orderId)); 
    		listCriteria.add(Restrictions.eq("orderId", orderId)); 
		}
		if(customerId!=null && !customerId.isEmpty()){
			countCriteria.add(Restrictions.eq("customerId", customerId)); 
    		listCriteria.add(Restrictions.eq("customerId", customerId)); 
		}
		if(status!=null && !status.isEmpty()){
			countCriteria.add(Restrictions.eq("status", status)); 
    		listCriteria.add(Restrictions.eq("status", status)); 
		}

        listCriteria.setFirstResult((pageNo-1)*pageSize);  
        listCriteria.setMaxResults(pageSize);
        List<Order> items = listCriteria.list();
        countCriteria.setProjection(Projections.rowCount());
        Integer count=Integer.parseInt(countCriteria.uniqueResult().toString());
        
        return PageListUtil.getPageList(count, pageNo, items, pageSize);
    }
	
	@Override
	public void saveOrder(Order order) throws ValidatException, EntityOperateException{
		super.save(order);
	}
	
	@Override
	public void updateOrder(Order order) throws ValidatException, EntityOperateException{
		Order dbModel=super.get(order.getId());
		dbModel.setorderId(order.getorderId());
		dbModel.setcreateTime(order.getcreateTime());
		dbModel.setcustomerId(order.getcustomerId());
		dbModel.settotalPrice(order.gettotalPrice());
		dbModel.setstatus(order.getstatus());
		dbModel.setnote(order.getnote());
		super.update(dbModel);
	}

	
	/*@SuppressWarnings("null")
	@Override
	public double Orderfigure(String orderId) throws NoSuchAlgorithmException, EntityOperateException, ValidatException {
		IOrderdetailService orderdetailservice = null;
		List<Orderdetail> list = new ArrayList<>();
		list = orderdetailservice.listAll();
		double totalPrice=0;
		for(Orderdetail od:list) {
			if(od.getorderId().equals(orderId)) totalPrice+=od.gettotalPrice();
		}
		return totalPrice;
		
		
	}*/
	
	
	//上一个方法中的部分返回值，返回最新库存状况的实体集合
	@SuppressWarnings("unchecked")
	public Orderdetail listsale(String productId){
		if(productId!=null&&!productId.isEmpty()) {
			Criteria listCriteria = entityDao.getCriteria();
			
			if(productId!=null && !productId.isEmpty()){
	    		listCriteria.add(Restrictions.eq("productId", productId)); 
			}
	
	        List<Orderdetail> items = listCriteria.list();
	        System.out.println("销售记录条数："+items.size());
	        //日期比较,取出销售记录中产品的最新销售状况
	        //销售与库存不一样的地方在于产品销售在明细中，时间在总表中
	        		Calendar c = Calendar.getInstance();
	        		c.set(Calendar.YEAR, -1000);
	        //还有一个问题是要计算一定时间期内的所有的销量之和，这里选择的是三天一个周期，因为系统里的时间比较少
	        
	        		//主要是下面这些地方需要修改，变成两个表的联合查询
	        		Orderdetail orderdetail=new Orderdetail();
	        		//Inventory inventory=new Inventory();
		        	//for(Inventory i:items) {
	        		Order order=new Order();
	        		for(Orderdetail i:items) {
	        			String thisorderid=i.getorderId();
	        			//这里我怎么生成一个对应id的order嘞
	        			List<Order> allorder=super.listAll();
	        			for(Order search:allorder){
	        				if(search.getorderId()==thisorderid){
	        					order=search;
	        					break;
	        				}
	        			}
		        			if(order.getcreateTime().compareTo(c)>0) { 
		        				c=order.getcreateTime();
		        				orderdetail=i;
		        				}
		        		
	        }        
	        return orderdetail;
		}
		else return null;
	}
	
	@Override
	@SuppressWarnings("unchecked")
	public PageList<Orderdetail> listAllPage(String productId, String productName, int pageNo, int pageSize) {		
		Criteria countCriteria = entityDao.getCriteria();	
		Criteria listCriteria = entityDao.getCriteria();
		
		if(productId!=null && !productId.isEmpty()){
			countCriteria.add(Restrictions.eq("productId", productId)); 
    		listCriteria.add(Restrictions.eq("productId", productId)); 
		}
		if(productName!=null && !productName.isEmpty()){
			countCriteria.add(Restrictions.eq("productName", productName)); 
    		listCriteria.add(Restrictions.eq("productName", productName)); 
		}

        listCriteria.setFirstResult((pageNo-1)*pageSize);  
        listCriteria.setMaxResults(pageSize);
        List<Orderdetail> items = listCriteria.list();
        
        
        countCriteria.setProjection(Projections.rowCount());
        Integer count=Integer.parseInt(countCriteria.uniqueResult().toString());
        
        return PageListUtil.getPageList(count, pageNo, items, pageSize);
    }
	
	
	public static int[] predict(TreeMap<String, Integer> yearsale){
		int[] predictlist = null;
		//3个销售周期的加权移动个平均，加权数分别为3,2,1
		int[] input = null;
		int i=0;
		for(Entry entry:yearsale.entrySet()){
			input[i]=(int) entry.getValue();
			i++;
		}
		if(input.length>2){
			int a=input[input.length-1];
			int b=input[input.length-2];
			int c=input[input.length-3];
			int pre1=(a*3+b*2+c*1)/6;
			predictlist[0]=pre1;
			int pre2=(pre1*3+a*2+b*1)/6;
			predictlist[1]=pre2;
		}
		else{
			predictlist[0]=input[input.length-1];
		}
		return predictlist;
	}

	public List<OrderdetailJson> listsalebyOrderdetail(String productId,String productName, int pageNo, int pageSize, IOrderdetailService orderdetailService)
	{
		Criteria countCriteria = entityDao.getCriteria();	
		Criteria listCriteria = entityDao.getCriteria();
		
		if(productId!=null && !productId.isEmpty()){
			countCriteria.add(Restrictions.eq("productId", productId)); 
    		listCriteria.add(Restrictions.eq("productId", productId)); 
		}
//		if(customerId!=null && !customerId.isEmpty()){
//			countCriteria.add(Restrictions.eq("customerId", customerId)); 
//    		listCriteria.add(Restrictions.eq("customerId", customerId)); 
//		}

        listCriteria.setFirstResult((pageNo-1)*pageSize);  
        listCriteria.setMaxResults(pageSize);
       
        List<Order> items = listCriteria.list();
        Calendar now=Calendar.getInstance();
		int year=now.YEAR;
		int month=now.MONTH;
		int day=now.DATE;
		int time=year*10000+month*100+day;
        
        List<Orderdetail> itemsPre = orderdetailService.listAll();
        
        List<OrderdetailJson> result=null;
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
        	int thistime=Integer.parseInt(item.getorderdetailId().substring(1,9));
        	int thisyear=thistime/10000;
        	int thismonth=(thistime-thisyear*10000)/100;
        	int thisday=thistime%100;
//        	int yue=(time-thistime)/100;
        	//计算与当前时间隔了多少个月
        	int chayear=year-thisyear;
        	int cha=chayear*12+thismonth-month;
   	
        	if(cha<12 && cha>=0){
        		chalist.add(cha);
        		if(sales.containsKey(String.valueOf(thistime/100))){
        			sales.put(String.valueOf(thistime/100), sales.get(String.valueOf(thistime/100))+item.getquantitySupplied());
        		}
        		else{
        			sales.put(String.valueOf(thistime/100), item.getquantitySupplied());
        		}
        	}
      
        }
//可能有的月份销量是0，因此要对其进行填充
        for(int m=0;m<12;m++){
        	if(!chalist.contains(m)){
        		int a=(month+1+m)/12;
        		int b=(month+1+m)%12;
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
            orderdetailjson.setProductName(productName);
            orderdetailjson.setCreateTime(map.getKey().toString());
            orderdetailjson.setSaleLevel(Integer.parseInt(map.getValue().toString()));
            result.add(orderdetailjson);
        }
        int m=1;
        for(int presale:this.predict(sales)){
        	 OrderdetailJson orderdetailjson=new OrderdetailJson();
             orderdetailjson.setProductId(productId);
             orderdetailjson.setProductName(productName);
             orderdetailjson.setCreateTime(String.valueOf((year+(month+m)/12)*100+(month+m)%12));
             orderdetailjson.setSaleLevel(presale);
             result.add(orderdetailjson);
             m++;
        }
        
/*        for(Order item:items){
        	int thisyear=item.getcreateTime().YEAR;
        	int thismonth=item.getcreateTime().MONTH;
//        	int thisday=item.getcreateTime().DATE;
        	int yearcha=year-thisyear;
        	
        	if(yearcha==0){
        	//本年每月销售
        		if(thismonth==(month-1)){
        			for(Orderdetail item2:itemsPre){
                		if(item.getorderId().equals(item2.getorderId())){
                			
                			orderdetailjson.setOrderdetailId(item2.getorderdetailId());
                			orderdetailjson.setOrderId(item2.getorderId());
                			orderdetailjson.setCreateTime(item.getcreateTime());
                			orderdetailjson.setProductId(productId);
                	//		orderdetailjson.setProductName(productName);
                			orderdetailjson.setSaleLevel(item2.getquantityDemand());
                			
                		}
                	}
        		}
        		else if(thismonth==(month-2)){
        			for(Orderdetail item2:itemsPre){
                		if(item.getorderId().equals(item2.getorderId())){
//                			item.setOrderId(item2.getorderId());
//                			item.setCustomerId(item2.getcustomerId());
                			orderdetailjson.setCreateTime(item.getcreateTime());
                			orderdetailjson.setProductId(productId);
                	//		orderdetailjson.setProductName(productName);
                			orderdetailjson.setSaleLevel(item2.getquantityDemand());
                			
                		}
                	}
        		}
        		
        	}
        	else if(year==1){
        		//去年的
        		
        	}
        		
        	
        }
        */
        countCriteria.setProjection(Projections.rowCount());
        Integer count=Integer.parseInt(countCriteria.uniqueResult().toString());
        
//        return PageListUtil.getPageList(count, pageNo, items, pageSize);
        return result;
	}
	
	@Override
    public int getpredict(String productId) throws IOException{

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
        for(int presale:this.predict(sales)){
        	return presale;
        }
		return 0;
    }

	

}
