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
import com.amc.dao.IPurchaseAdviceDao;
import com.amc.model.models.Inventory;
import com.amc.model.models.PurchaseAdvice;
import com.amc.service.interfaces.IInventoryService;
import com.amc.service.interfaces.IProductService;
import com.amc.service.interfaces.IPurchaseAdviceService;
import com.infrastructure.project.base.service.services.EnableEntityService;
import com.infrastructure.project.common.exception.EntityOperateException;
import com.infrastructure.project.common.exception.ValidatException;
import com.infrastructure.project.common.utilities.PageList;
import com.infrastructure.project.common.utilities.PageListUtil;;

@Service("PurchaseAdviceService")
public class PurchaseAdviceService extends EnableEntityService<Integer, PurchaseAdvice, IPurchaseAdviceDao> implements IPurchaseAdviceService {
	
	
	/*@Autowired
    @Qualifier("RoleService")
	protected IRoleService roleService;
	
	@Autowired
    @Qualifier("ProductService")
	protected IOrganizationService organizationService;
	*/
	@Autowired
	public PurchaseAdviceService(@Qualifier("PurchaseAdviceDao")IPurchaseAdviceDao purchaseadviceDao){	
		super(purchaseadviceDao);
	}
	
	@Override
	@SuppressWarnings("unchecked")
	public PageList<PurchaseAdvice> listPage(/*IInventoryService inventoryService,*/int pageNo, int pageSize) {		
		Criteria countCriteria = entityDao.getCriteria();	
		Criteria listCriteria = entityDao.getCriteria();
		

        listCriteria.setFirstResult((pageNo-1)*pageSize);  
        listCriteria.setMaxResults(pageSize);
        List<PurchaseAdvice> items = listCriteria.list();
    /*    for(PurchaseAdvice pa:items) {
        		Inventory inventory = inventoryService.listinventory(pa.getproductId());
        		int inventoryLevel = inventory.getinventoryLevel();
       		String productName = inventory.getproductName();
       		pa.setproductName(productName);
       		pa.setinventoryLevel(inventoryLevel);
        }*/
        
        countCriteria.setProjection(Projections.rowCount());
        Integer count=items.size();
        
        return PageListUtil.getPageList(count, pageNo, items, pageSize);
    }
	

	@Override
	public void savePurchaseAdvice(PurchaseAdvice purchaseadvice) throws ValidatException, EntityOperateException{
		super.save(purchaseadvice);
		
	}
	
	@Override
	public void updatePurchaseAdvice(PurchaseAdvice purchaseadvice) throws ValidatException, EntityOperateException{
		PurchaseAdvice dbModel=super.get(purchaseadvice.getId());
		dbModel.setproductId(purchaseadvice.getproductId());
		dbModel.setproductName(purchaseadvice.getproductName());
		dbModel.setinventoryLevel(purchaseadvice.getinventoryLevel());	
		dbModel.setdemand(purchaseadvice.getdemand());		
		dbModel.setadvice(purchaseadvice.getadvice());
		super.update(dbModel);
	}
}
