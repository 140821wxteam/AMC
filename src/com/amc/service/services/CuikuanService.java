package com.amc.service.services;

import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.amc.dao.ICuikuanDao;
import com.amc.model.models.Cuikuan;
import com.amc.service.interfaces.ICuikuanService;
import com.infrastructure.project.base.service.services.EnableEntityService;
import com.infrastructure.project.common.exception.EntityOperateException;
import com.infrastructure.project.common.exception.ValidatException;
import com.infrastructure.project.common.utilities.PageList;
import com.infrastructure.project.common.utilities.PageListUtil;;

@Service("CuikuanService")
public class CuikuanService extends EnableEntityService<Integer, Cuikuan, ICuikuanDao> implements ICuikuanService {
	
	@Autowired
	public CuikuanService(@Qualifier("CuikuanDao")ICuikuanDao cuikuanDao){	
		super(cuikuanDao);
	}
	
	@Override
	@SuppressWarnings("unchecked")
	public PageList<Cuikuan> listPage(String cuikuanId, String customerId, int pageNo, int pageSize) {		
		Criteria countCriteria = entityDao.getCriteria();	
		Criteria listCriteria = entityDao.getCriteria();
		
		if(cuikuanId!=null && !cuikuanId.isEmpty()){
			countCriteria.add(Restrictions.eq("cuikuanId", cuikuanId)); 
    		listCriteria.add(Restrictions.eq("cuikuanId", cuikuanId)); 
		}
		if(customerId!=null && !customerId.isEmpty()){
			countCriteria.add(Restrictions.eq("customerId", customerId)); 
    		listCriteria.add(Restrictions.eq("customerId", customerId)); 
		}

        listCriteria.setFirstResult((pageNo-1)*pageSize);  
        listCriteria.setMaxResults(pageSize);
        List<Cuikuan> items = listCriteria.list();
        countCriteria.setProjection(Projections.rowCount());
        Integer count=Integer.parseInt(countCriteria.uniqueResult().toString());
        
        return PageListUtil.getPageList(count, pageNo, items, pageSize);
    }
	
/*	@Override
	public void saveInvoice(Invoice invoice) throws ValidatException, EntityOperateException{
		super.save(invoice);
	}
	
	@Override
	public void updateInvoice(Invoice invoice) throws ValidatException, EntityOperateException{
		Invoice dbModel=super.get(invoice.getId());
		dbModel.setinvoiceId(invoice.getinvoiceId());
		dbModel.setcreateTime(invoice.getcreateTime());
		dbModel.setfactoryId(invoice.getfactoryId());
		dbModel.setsumPrice(invoice.getsumPrice());
		dbModel.setstatus(invoice.getstatus());
		dbModel.setnote(invoice.getnote());
		super.update(dbModel);
	}*/

	
}
