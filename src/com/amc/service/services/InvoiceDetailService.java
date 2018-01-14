package com.amc.service.services;

import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.amc.dao.IInvoiceDetailDao;
import com.amc.model.models.InvoiceDetail;
import com.amc.service.interfaces.IInvoiceDetailService;
import com.infrastructure.project.base.service.services.EnableEntityService;
import com.infrastructure.project.common.utilities.PageList;
import com.infrastructure.project.common.utilities.PageListUtil;;

@Service("InvoiceDetailService")
public class InvoiceDetailService extends EnableEntityService<Integer, InvoiceDetail, IInvoiceDetailDao> implements IInvoiceDetailService {
	
	@Autowired
	public InvoiceDetailService(@Qualifier("InvoiceDetailDao")IInvoiceDetailDao invoiceDetailDao){	
		super(invoiceDetailDao);
	}
	
	@Override
	@SuppressWarnings("unchecked")
	public PageList<InvoiceDetail> listPage(String invoiceId,int pageNo, int pageSize) {		
		Criteria countCriteria = entityDao.getCriteria();	
		Criteria listCriteria = entityDao.getCriteria();

		if(invoiceId!=null && !invoiceId.isEmpty()){
			countCriteria.add(Restrictions.eq("invoiceId", invoiceId)); 
    		listCriteria.add(Restrictions.eq("invoiceId", invoiceId)); 
		}
		

        listCriteria.setFirstResult((pageNo-1)*pageSize);  
        listCriteria.setMaxResults(pageSize);
        List<InvoiceDetail> items = listCriteria.list();
        countCriteria.setProjection(Projections.rowCount());
        Integer count=Integer.parseInt(countCriteria.uniqueResult().toString());
        
        return PageListUtil.getPageList(count, pageNo, items, pageSize);
    }

}
