package com.amc.service.services;

import java.security.NoSuchAlgorithmException;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.amc.dao.IBackoderDao;
import com.amc.dao.IVendorDao;
import com.amc.model.models.Vendor;
import com.amc.model.models.Account;
import com.amc.model.models.Backoder;
import com.amc.model.models.Organization;
import com.amc.model.models.Role;
import com.amc.service.interfaces.IBackoderService;
import com.amc.service.interfaces.IVendorService;
import com.infrastructure.project.base.service.services.EnableEntityService;
import com.infrastructure.project.common.exception.EntityOperateException;
import com.infrastructure.project.common.exception.ValidatException;
import com.infrastructure.project.common.extension.StringHelper;
import com.infrastructure.project.common.utilities.PageList;
import com.infrastructure.project.common.utilities.PageListUtil;;

@Service("BackoderService")
public class BackoderService extends EnableEntityService<Integer, Backoder, IBackoderDao> implements IBackoderService {
	
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
	public BackoderService(@Qualifier("BackoderDao")IBackoderDao backoderDao){	
		super(backoderDao);
	}
	
	@Override
	@SuppressWarnings("unchecked")
	public PageList<Backoder> listPage(String backoderId, String productId, int pageNo, int pageSize) {		
		Criteria countCriteria = entityDao.getCriteria();	
		Criteria listCriteria = entityDao.getCriteria();
		
		if(backoderId!=null && !backoderId.isEmpty()){
			countCriteria.add(Restrictions.eq("backoderId", backoderId)); 
    		listCriteria.add(Restrictions.eq("backoderId", backoderId)); 
		}
		if(productId!=null && !productId.isEmpty()){
			countCriteria.add(Restrictions.eq("productId", productId)); 
    		listCriteria.add(Restrictions.eq("productId", productId)); 
		}

        listCriteria.setFirstResult((pageNo-1)*pageSize);  
        listCriteria.setMaxResults(pageSize);
        List<Backoder> items = listCriteria.list();
        countCriteria.setProjection(Projections.rowCount());
        Integer count=Integer.parseInt(countCriteria.uniqueResult().toString());
        
        return PageListUtil.getPageList(count, pageNo, items, pageSize);
    }
	
	@Override
	public void saveBackoder(Backoder backoder) throws ValidatException, EntityOperateException{
		super.save(backoder);
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
//	private String backoderId;
//	private String productId;
//	private String productName;
//	private String factoryId;
//	private String size;
//	private String amount;
//	private Date date;
//	private String note;

	@Override
	public void updateBackoder(Backoder backoder) throws ValidatException, EntityOperateException{
		Backoder dbModel=super.get(backoder.getId());
		dbModel.setbackoderId(backoder.getbackoderId());
		dbModel.setproductName(backoder.getproductName());
		dbModel.setproductId(backoder.getproductId());
		dbModel.setfactoryId(backoder.getfactoryId());
		dbModel.setsize(backoder.getsize());
		dbModel.setamount(backoder.getamount());
		dbModel.setdate(backoder.getdate());
		//这里希望能够自动用系统当前的时间
		dbModel.setnote(backoder.getnote());
		super.update(dbModel);
	}

}
