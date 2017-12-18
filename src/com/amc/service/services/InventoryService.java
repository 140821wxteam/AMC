package com.amc.service.services;

import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.amc.dao.IInventoryDao;
import com.amc.model.models.Inventory;
import com.amc.service.interfaces.IInventoryService;
import com.infrastructure.project.base.service.services.EnableEntityService;
import com.infrastructure.project.common.exception.EntityOperateException;
import com.infrastructure.project.common.exception.ValidatException;
import com.infrastructure.project.common.utilities.PageList;
import com.infrastructure.project.common.utilities.PageListUtil;;

@Service("InventoryService")
public class InventoryService extends EnableEntityService<Integer, Inventory, IInventoryDao> implements IInventoryService {
	
	/*@Autowired
    @Qualifier("AuthorityService")
	protected IAuthorityService authorityService;*/
	
	/*@Autowired
    @Qualifier("RoleService")
	protected IRoleService roleService;
	
	@Autowired
    @Qualifier("OrganizationService")
	protected IOrganizationService organizationService;
	*/
	@Autowired
	public InventoryService(@Qualifier("InventoryDao")IInventoryDao inventoryDao){	
		super(inventoryDao);
	}
	
	@Override
	@SuppressWarnings("unchecked")
	public PageList<Inventory> listPage(String productId, String productName, int pageNo, int pageSize) {		
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
        List<Inventory> items = listCriteria.list();
        List<Inventory> items_final = new ArrayList<>();
        //日期比较,取出库存记录中每一个产品的最新库存状况
        List<String>	productIds=new ArrayList<>();
        for(Inventory i:items) {
        		if(!productIds.contains(i.getproductId())) productIds.add(i.getproductId());
        }
        System.out.println(productIds);
        for(String pi:productIds) {
        		Calendar c = Calendar.getInstance();
        		c.set(Calendar.YEAR, -1000);
        		Inventory inventory=new Inventory();
	        	for(Inventory i:items) {
	        		if(i.getproductId().equals(pi)) {
	        			if(i.getcreateTime().compareTo(c)>0) { c=i.getcreateTime();inventory=i;}
	        		}
	        }
	        items_final.add(inventory);
        }
        
        countCriteria.setProjection(Projections.rowCount());
        Integer count=items_final.size();
        
        return PageListUtil.getPageList(count, pageNo, items_final, pageSize);
    }
	
	//上一个方法中的部分返回值，返回最新库存状况的实体集合
	@SuppressWarnings("unchecked")
	public Inventory listinventory(String productId){
		if(productId!=null&&!productId.isEmpty()) {
			Criteria listCriteria = entityDao.getCriteria();
			
			if(productId!=null && !productId.isEmpty()){
	    		listCriteria.add(Restrictions.eq("productId", productId)); 
			}
	
	        List<Inventory> items = listCriteria.list();
	        System.out.println("库存记录条数："+items.size());
	        //日期比较,取出库存记录中产品的最新库存状况
	        		Calendar c = Calendar.getInstance();
	        		c.set(Calendar.YEAR, -1000);
	        		Inventory inventory=new Inventory();
		        	for(Inventory i:items) {
		        		
		        			if(i.getcreateTime().compareTo(c)>0) { c=i.getcreateTime();inventory=i;}
		        		
	        }        
	        return inventory;
		}
		else return null;
	}
	
	@Override
	@SuppressWarnings("unchecked")
	public PageList<Inventory> listAllPage(String productId, String productName, int pageNo, int pageSize) {		
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
        List<Inventory> items = listCriteria.list();
        
        
        countCriteria.setProjection(Projections.rowCount());
        Integer count=Integer.parseInt(countCriteria.uniqueResult().toString());
        
        return PageListUtil.getPageList(count, pageNo, items, pageSize);
    }
	
	@Override
	public void saveInventory(Inventory inventory) throws ValidatException, EntityOperateException{
		super.save(inventory);
	}
	
	@Override
	public void updateInventory(Inventory inventory) throws ValidatException, EntityOperateException{
		Inventory dbModel=super.get(inventory.getId());
		dbModel.setproductId(inventory.getproductId());
		dbModel.setproductName(inventory.getproductName());
		dbModel.setinventoryLevel(inventory.getinventoryLevel());		
		dbModel.setstatus(inventory.getstatus());
		dbModel.setnote(inventory.getnote());
		super.update(dbModel);
	}
}
