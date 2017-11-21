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
import com.amc.model.models.Orderdetail;
import com.amc.service.interfaces.IOrderdetailService;
import com.infrastructure.project.base.service.services.EnableEntityService;
import com.infrastructure.project.common.exception.EntityOperateException;
import com.infrastructure.project.common.exception.ValidatException;
import com.infrastructure.project.common.utilities.PageList;
import com.infrastructure.project.common.utilities.PageListUtil;;

@Service("OrderdetailService")
public class OrderdetailService extends EnableEntityService<Integer, Orderdetail, IOrderdetailDao> implements IOrderdetailService {
	
	@Autowired
	public OrderdetailService(@Qualifier("OrderdetailDao")IOrderdetailDao orderdetailDao){	
		super(orderdetailDao);
	}
	
	@Override
	@SuppressWarnings("unchecked")
	public PageList<Orderdetail> listPage(String orderId, int pageNo, int pageSize) {		
		Criteria countCriteria = entityDao.getCriteria();	
		Criteria listCriteria = entityDao.getCriteria();
		
		if(orderId!=null && !orderId.isEmpty()){
			countCriteria.add(Restrictions.eq("orderId", orderId)); 
    		listCriteria.add(Restrictions.eq("orderId", orderId)); 
		}

        listCriteria.setFirstResult((pageNo-1)*pageSize);  
        listCriteria.setMaxResults(pageSize);
        List<Orderdetail> items = listCriteria.list();
        countCriteria.setProjection(Projections.rowCount());
        Integer count=Integer.parseInt(countCriteria.uniqueResult().toString());
        
        return PageListUtil.getPageList(count, pageNo, items, pageSize);
    }
	
	
	
	@Override
	public void saveOrderdetail(Orderdetail orderdetail) throws ValidatException, EntityOperateException{
		super.save(orderdetail);
	}
	
	@Override
	public void updateOrderdetail(Orderdetail orderdetail) throws ValidatException, EntityOperateException{
		Orderdetail dbModel=super.get(orderdetail.getId());
		dbModel.setorderdetailId(orderdetail.getorderdetailId());
		dbModel.setorderId(orderdetail.getorderId());
		dbModel.setproductId(orderdetail.getproductId());
		dbModel.setproductName(orderdetail.getproductName());
		dbModel.setquantityDemand(orderdetail.getquantityDemand());
		dbModel.setquantitySupplied(orderdetail.getquantitySupplied());
		dbModel.setunitPrice(orderdetail.getunitPrice());
		dbModel.settotalPrice(orderdetail.gettotalPrice());
		dbModel.setstatus(orderdetail.getstatus());
		dbModel.setnote(orderdetail.getnote());
		super.update(dbModel);
	}
}
