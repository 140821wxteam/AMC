package com.amc.service.services;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;

//import com.amc.dao.IAccountDao;
import com.amc.model.models.Customer;
import com.amc.service.interfaces.ICustomerService;
import com.infrastructure.project.base.service.services.EnableEntityService;
import com.infrastructure.project.common.utilities.PageList;
import com.infrastructure.project.common.utilities.PageListUtil;

public class CustomerService extends EnableEntityService<Integer, Account, IAccountDao> implements ICustomerService{

	@Override
	@SuppressWarnings("unchecked")
	public PageList<Customer> listPage(String englishName, String chineseName, int pageNo, int pageSize) {		
		Criteria countCriteria = entityDao.getCriteria();	
		Criteria listCriteria = entityDao.getCriteria();
		
		if(name!=null && !name.isEmpty()){
			countCriteria.add(Restrictions.eq("name", name)); 
    		listCriteria.add(Restrictions.eq("name", name)); 
		}
		if(username!=null && !username.isEmpty()){
			countCriteria.add(Restrictions.eq("username", username)); 
    		listCriteria.add(Restrictions.eq("username", username)); 
		}

        listCriteria.setFirstResult((pageNo-1)*pageSize);  
        listCriteria.setMaxResults(pageSize);
        List<Account> items = listCriteria.list();
        countCriteria.setProjection(Projections.rowCount());
        Integer count=Integer.parseInt(countCriteria.uniqueResult().toString());
        
        return PageListUtil.getPageList(count, pageNo, items, pageSize);
    }
}
