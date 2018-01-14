package com.amc.service.services;

import java.util.ArrayList;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.amc.dao.IOrderdetailDao;
import com.amc.dao.IPurchaseDetailDao;
import com.amc.model.models.Inventory;
import com.amc.model.models.Orderdetail;
import com.amc.model.models.PurchaseDetail;
import com.amc.service.interfaces.IOrderdetailService;
import com.amc.service.interfaces.IPurchaseDetailService;
import com.infrastructure.project.base.service.services.EnableEntityService;
import com.infrastructure.project.common.exception.EntityOperateException;
import com.infrastructure.project.common.exception.ValidatException;
import com.infrastructure.project.common.utilities.PageList;
import com.infrastructure.project.common.utilities.PageListUtil;;

@Service("PurchaseDetailService")
public class PurchaseDetailService extends EnableEntityService<Integer, PurchaseDetail, IPurchaseDetailDao> implements IPurchaseDetailService {
	
	@Autowired
	public PurchaseDetailService(@Qualifier("PurchaseDetailDao")IPurchaseDetailDao purchaseDetailDao){	
		super(purchaseDetailDao);
	}
	
	@Override
	@SuppressWarnings("unchecked")
	public PageList<PurchaseDetail> listPage(String orderId, int pageNo, int pageSize) {		
		Criteria countCriteria = entityDao.getCriteria();	
		Criteria listCriteria = entityDao.getCriteria();
		
		if(orderId!=null && !orderId.isEmpty()){
			countCriteria.add(Restrictions.eq("orderId", orderId)); 
    		listCriteria.add(Restrictions.eq("orderId", orderId)); 
		}

        listCriteria.setFirstResult((pageNo-1)*pageSize);  
        listCriteria.setMaxResults(pageSize);
        List<PurchaseDetail> items = listCriteria.list();
        countCriteria.setProjection(Projections.rowCount());
        Integer count=Integer.parseInt(countCriteria.uniqueResult().toString());
        
        return PageListUtil.getPageList(count, pageNo, items, pageSize);
    }
	
	
	
	@Override
	public void savePurchaseDetail(PurchaseDetail purchasedetail) throws ValidatException, EntityOperateException{
		super.save(purchasedetail);
	}
	
	@Override
	public void updatePurchaseDetail(PurchaseDetail purchasedetail) throws ValidatException, EntityOperateException{
		PurchaseDetail dbModel=super.get(purchasedetail.getId());
		dbModel.setorderdetailId(purchasedetail.getorderdetailId());
		dbModel.setorderId(purchasedetail.getorderId());
		dbModel.setproductId(purchasedetail.getproductId());
		dbModel.setproductName(purchasedetail.getproductName());
		dbModel.setquantity(purchasedetail.getquantity());
		dbModel.setunitPrice(purchasedetail.getunitPrice());
		dbModel.settotalPrice(purchasedetail.gettotalPrice());
		dbModel.setnote(purchasedetail.getnote());
		super.update(dbModel);
	}
	@Override
	@SuppressWarnings("unchecked")
	public PageList<PurchaseDetail> listAllPage(String productId, String productName, int pageNo, int pageSize) {		
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
        List<PurchaseDetail> items = listCriteria.list();
        
        
        countCriteria.setProjection(Projections.rowCount());
        Integer count=Integer.parseInt(countCriteria.uniqueResult().toString());
        
        return PageListUtil.getPageList(count, pageNo, items, pageSize);
    }
	
	
	
}
