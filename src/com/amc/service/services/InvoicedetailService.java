package com.amc.service.services;

import java.util.ArrayList;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.amc.dao.IInvoicedetailDao;
import com.amc.model.models.Invoicedetail;
import com.amc.service.interfaces.IInvoicedetailService;
import com.infrastructure.project.base.service.services.EnableEntityService;
import com.infrastructure.project.common.exception.EntityOperateException;
import com.infrastructure.project.common.exception.ValidatException;
import com.infrastructure.project.common.utilities.PageList;
import com.infrastructure.project.common.utilities.PageListUtil;;

@Service("InvoicedetailService")
public class InvoicedetailService extends EnableEntityService<Integer, Invoicedetail, IInvoicedetailDao> implements IInvoicedetailService {
	
	@Autowired
	public InvoicedetailService(@Qualifier("InvoicedetailDao")IInvoicedetailDao invoicedetailDao){	
		super(invoicedetailDao);
	}
	
	@Override
	@SuppressWarnings("unchecked")
	public PageList<Invoicedetail> listPage(String invoiceId, int pageNo, int pageSize) {		
		Criteria countCriteria = entityDao.getCriteria();	
		Criteria listCriteria = entityDao.getCriteria();
		
		if(invoiceId!=null && !invoiceId.isEmpty()){
			countCriteria.add(Restrictions.eq("invoiceId", invoiceId)); 
    		listCriteria.add(Restrictions.eq("invoiceId", invoiceId)); 
		}

        listCriteria.setFirstResult((pageNo-1)*pageSize);  
        listCriteria.setMaxResults(pageSize);
        List<Invoicedetail> items = listCriteria.list();
        countCriteria.setProjection(Projections.rowCount());
        Integer count=Integer.parseInt(countCriteria.uniqueResult().toString());
        
        return PageListUtil.getPageList(count, pageNo, items, pageSize);
    }
	
	
	
	@Override
	public void saveInvoicedetail(Invoicedetail invoicedetail) throws ValidatException, EntityOperateException{
		super.save(invoicedetail);
	}
	
	@Override
	public void updateInvoicedetail(Invoicedetail invoicedetail) throws ValidatException, EntityOperateException{
		Invoicedetail dbModel=super.get(invoicedetail.getId());
		dbModel.setinvoicedetailId(invoicedetail.getinvoicedetailId());
		dbModel.setinvoiceId(invoicedetail.getinvoiceId());
		dbModel.setproductId(invoicedetail.getproductId());
		dbModel.setproductName(invoicedetail.getproductName());
		dbModel.setamount(invoicedetail.getamount());
		dbModel.setunitPrice(invoicedetail.getunitPrice());
		dbModel.settotalPrice(invoicedetail.gettotalPrice());
		dbModel.setstatus(invoicedetail.getstatus());
		dbModel.setnote(invoicedetail.getnote());
		super.update(dbModel);
	}
}
