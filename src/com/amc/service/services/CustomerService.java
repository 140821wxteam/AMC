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

import com.amc.dao.ICustomerDao;
import com.amc.dao.IVendorDao;
import com.amc.model.models.Vendor;
import com.amc.model.models.Account;
import com.amc.model.models.Customers;
import com.amc.model.models.Organization;
import com.amc.model.models.Role;
import com.amc.service.interfaces.ICustomersService;
import com.amc.service.interfaces.IVendorService;
import com.infrastructure.project.base.service.services.EnableEntityService;
import com.infrastructure.project.common.exception.EntityOperateException;
import com.infrastructure.project.common.exception.ValidatException;
import com.infrastructure.project.common.extension.StringHelper;
import com.infrastructure.project.common.utilities.PageList;
import com.infrastructure.project.common.utilities.PageListUtil;;

@Service("CustomerService")
public class CustomerService extends EnableEntityService<Integer, Customers, ICustomerDao> implements ICustomersService {
	
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
	public CustomerService(@Qualifier("CustomerDao")ICustomerDao customerDao){	
		super(customerDao);
	}
	
	@Override
	@SuppressWarnings("unchecked")
	public PageList<Customers> listPage(String customerId, String customerName, int pageNo, int pageSize) {		
		Criteria countCriteria = entityDao.getCriteria();	
		Criteria listCriteria = entityDao.getCriteria();
		
		if(customerId!=null && !customerId.isEmpty()){
			countCriteria.add(Restrictions.eq("customerId", customerId)); 
    		listCriteria.add(Restrictions.eq("customerId", customerId)); 
		}
		if(customerName!=null && !customerName.isEmpty()){
			countCriteria.add(Restrictions.eq("customerName", customerName)); 
    		listCriteria.add(Restrictions.eq("customerName", customerName)); 
		}

        listCriteria.setFirstResult((pageNo-1)*pageSize);  
        listCriteria.setMaxResults(pageSize);
        List<Customers> items = listCriteria.list();
        countCriteria.setProjection(Projections.rowCount());
        Integer count=Integer.parseInt(countCriteria.uniqueResult().toString());
        
        return PageListUtil.getPageList(count, pageNo, items, pageSize);
    }
	
	@Override
	public void saveCustomer(Customers customer) throws ValidatException, EntityOperateException{
		super.save(customer);
	}
	
	/*@Override
	public boolean vendorExist(String vendorId){
		Criteria criteria = entityDao.getCriteria();
		criteria.add(Restrictions.eq("vendorId", vendorId));
    	criteria.setProjection(Projections.rowCount());
    	Integer count = Integer.parseInt(criteria.uniqueResult().toString());
    	if(count!=null && count>0)
    		return true;
    	else
    		return false;
	}
	*/

	@Override
	public void updateCustomer(Customers customer) throws ValidatException, EntityOperateException{
		Customers dbModel=super.get(customer.getId());
		dbModel.setcustomerId(customer.getcustomerId());
		dbModel.setcustomerName(customer.getcustomerName());
		dbModel.setcustomerAddr(customer.getcustomerAddr());
		dbModel.setcontactPerson(customer.getcontactPerson());
		dbModel.setcustomerTele(customer.getcustomerTele());
		dbModel.setcustomerEmail(customer.getcustomerEmail());
		dbModel.setnote(customer.getnote());
		super.update(dbModel);
	}

	@Override
	public List<String> listcustomersId() {
		List<Customers> customers = super.listAll();
		List<String> customersId = new ArrayList<>();
		for(Customers c:customers) {
			if(!customersId.contains(c.getcustomerId())) {
				customersId.add(c.getcustomerId());
			}
		}
		
		return customersId;
	}

}
