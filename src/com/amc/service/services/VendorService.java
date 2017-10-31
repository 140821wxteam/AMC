package com.amc.service.services;

import java.security.NoSuchAlgorithmException;
import java.util.Calendar;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.amc.dao.IVendorDao;
import com.amc.model.models.Vendor;
import com.amc.model.models.Account;
import com.amc.model.models.Organization;
import com.amc.model.models.Role;
import com.amc.service.interfaces.IVendorService;
import com.infrastructure.project.base.service.services.EnableEntityService;
import com.infrastructure.project.common.exception.EntityOperateException;
import com.infrastructure.project.common.exception.ValidatException;
import com.infrastructure.project.common.extension.StringHelper;
import com.infrastructure.project.common.utilities.PageList;
import com.infrastructure.project.common.utilities.PageListUtil;;

@Service("VendorService")
public class VendorService extends EnableEntityService<Integer, Vendor, IVendorDao> implements IVendorService {
	
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
	public VendorService(@Qualifier("VendorDao")IVendorDao vendorDao){	
		super(vendorDao);
	}
	
	@Override
	@SuppressWarnings("unchecked")
	public PageList<Vendor> listPage(String vendorId, String vendorName, int pageNo, int pageSize) {		
		Criteria countCriteria = entityDao.getCriteria();	
		Criteria listCriteria = entityDao.getCriteria();
		
		if(vendorId!=null && !vendorId.isEmpty()){
			countCriteria.add(Restrictions.eq("vendorId", vendorId)); 
    		listCriteria.add(Restrictions.eq("vendorId", vendorId)); 
		}
		if(vendorName!=null && !vendorName.isEmpty()){
			countCriteria.add(Restrictions.eq("vendorName", vendorName)); 
    		listCriteria.add(Restrictions.eq("vendorName", vendorName)); 
		}

        listCriteria.setFirstResult((pageNo-1)*pageSize);  
        listCriteria.setMaxResults(pageSize);
        List<Vendor> items = listCriteria.list();
        countCriteria.setProjection(Projections.rowCount());
        Integer count=Integer.parseInt(countCriteria.uniqueResult().toString());
        
        return PageListUtil.getPageList(count, pageNo, items, pageSize);
    }
	
	@Override
	public void saveVendor(Vendor vendor) throws ValidatException, EntityOperateException{
		super.save(vendor);
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

	/*@Override
	public void updateBind(Integer id, Integer roleId, Integer organizationId) throws ValidatException, EntityOperateException{
		Account dbAccount=super.get(id);
		if(roleId!=null && roleId>0){
			Role dbRole=roleService.get(roleId);
			dbAccount.setRole(dbRole);
		}
		else
			dbAccount.setRole(null);
		if(organizationId!=null && organizationId>0){
			Organization dbOrganization=organizationService.get(organizationId);
			dbAccount.setOrganization(dbOrganization);
		}
		else
			dbAccount.setOrganization(null);
		super.update(dbAccount);
	*/

}
