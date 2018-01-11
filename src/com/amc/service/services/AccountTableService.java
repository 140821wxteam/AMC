package com.amc.service.services;

import java.util.ArrayList;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.sql.JoinType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.amc.dao.IAccountTableDao;
import com.amc.model.models.AccountTable;
import com.amc.service.interfaces.IAccountTableService;
import com.infrastructure.project.base.service.services.EnableEntityService;
import com.infrastructure.project.common.exception.EntityOperateException;
import com.infrastructure.project.common.exception.ValidatException;
import com.infrastructure.project.common.utilities.PageList;
import com.infrastructure.project.common.utilities.PageListUtil;;

@Service("AccountTableService")
public class AccountTableService extends EnableEntityService<Integer, AccountTable, IAccountTableDao> implements IAccountTableService {
	
	@Autowired
	public AccountTableService(@Qualifier("AccountTableDao")IAccountTableDao accountTableDao){	
		super(accountTableDao);
	}
	
	@Override
	@SuppressWarnings("unchecked")
	public PageList<AccountTable> listPage(String orderId, String customerId, int pageNo, int pageSize) {		
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

        listCriteria.setFirstResult((pageNo-1)*pageSize);  
        listCriteria.setMaxResults(pageSize);
        List<AccountTable> items = listCriteria.list();

        countCriteria.setProjection(Projections.rowCount());
        Integer count=Integer.parseInt(countCriteria.uniqueResult().toString());
        
        return PageListUtil.getPageList(count, pageNo, items, pageSize);
    }

}
