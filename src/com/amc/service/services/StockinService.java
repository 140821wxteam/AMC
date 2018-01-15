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

import com.amc.dao.IStockinDao;
import com.amc.dao.IVendorDao;
import com.amc.model.models.Vendor;
import com.amc.model.models.Account;
import com.amc.model.models.Authority;
import com.amc.model.models.Organization;
import com.amc.model.models.Role;
import com.amc.model.models.Stockin;
import com.amc.service.interfaces.IStockinService;
import com.amc.service.interfaces.IVendorService;
import com.infrastructure.project.base.service.services.EnableEntityService;
import com.infrastructure.project.common.exception.EntityOperateException;
import com.infrastructure.project.common.exception.ValidatException;
import com.infrastructure.project.common.extension.StringHelper;
import com.infrastructure.project.common.utilities.PageList;
import com.infrastructure.project.common.utilities.PageListUtil;;

@Service("StockinService")
public class StockinService extends EnableEntityService<Integer, Stockin, IStockinDao> implements IStockinService {
	
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
	public StockinService(@Qualifier("StockinDao")IStockinDao stockinDao){	
		super(stockinDao);
	}
	
	@Override
	@SuppressWarnings("unchecked")
	public PageList<Stockin> listPage(String stockinId, String status, int pageNo, int pageSize) {		
		Criteria countCriteria = entityDao.getCriteria();	
		Criteria listCriteria = entityDao.getCriteria();
		
		if(stockinId!=null && !stockinId.isEmpty()){
			countCriteria.add(Restrictions.eq("stockinId", stockinId)); 
    		listCriteria.add(Restrictions.eq("stockinId", stockinId)); 
		}
		if(status!=null && !status.isEmpty()){
			countCriteria.add(Restrictions.eq("status", status)); 
    		listCriteria.add(Restrictions.eq("status", status)); 
		}

        listCriteria.setFirstResult((pageNo-1)*pageSize);  
        listCriteria.setMaxResults(pageSize);
        List<Stockin> items = listCriteria.list();
        countCriteria.setProjection(Projections.rowCount());
        Integer count=Integer.parseInt(countCriteria.uniqueResult().toString());
        
        return PageListUtil.getPageList(count, pageNo, items, pageSize);
    }
	
	@Override
	public void saveStockin(Stockin stockin) throws ValidatException, EntityOperateException{
		super.save(stockin);
	}
	
	@Override
	public void updateStockin(Stockin stockin) throws ValidatException, EntityOperateException{
		Stockin dbModel=super.get(stockin.getId());
		dbModel.setStockinId(stockin.getStockinId());
		dbModel.setProductId(stockin.getProductId());
		dbModel.setAmount(stockin.getAmount());
		dbModel.setVendorId(stockin.getVendorId());
		dbModel.setCreateTime(stockin.getCreateTime());
		dbModel.setStatus(stockin.getStatus());
		dbModel.setNote(stockin.getNote());
		super.update(dbModel);
	}
	

}
