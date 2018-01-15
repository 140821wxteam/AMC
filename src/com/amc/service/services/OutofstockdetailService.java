package com.amc.service.services;

import java.util.ArrayList;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.amc.dao.IOutofstockDao;
import com.amc.dao.IOutofstockdetailDao;
import com.amc.model.models.Outofstockdetail;
import com.amc.model.models.Preparedetail;
import com.amc.service.interfaces.IOutofstockService;
import com.amc.service.interfaces.IOutofstockdetailService;
import com.infrastructure.project.base.service.services.EnableEntityService;
import com.infrastructure.project.common.exception.EntityOperateException;
import com.infrastructure.project.common.exception.ValidatException;
import com.infrastructure.project.common.utilities.PageList;
import com.infrastructure.project.common.utilities.PageListUtil;;

@Service("OutofstockdetailService")
public class OutofstockdetailService extends EnableEntityService<Integer, Outofstockdetail, IOutofstockdetailDao> implements IOutofstockdetailService {
	
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
	public OutofstockdetailService(@Qualifier("OutofstockdetailDao")IOutofstockdetailDao outofstockdetailDao){	
		super(outofstockdetailDao);
	}
	
	@Override
	@SuppressWarnings("unchecked")
	public PageList<Outofstockdetail> listPage(String outofstockId, String status, int pageNo, int pageSize) {		
		Criteria countCriteria = entityDao.getCriteria();	
		Criteria listCriteria = entityDao.getCriteria();
		
		if(outofstockId!=null && !outofstockId.isEmpty()){
			countCriteria.add(Restrictions.eq("outofstockId", outofstockId)); 
    		listCriteria.add(Restrictions.eq("outofstockId", outofstockId)); 
		}
		if(status!=null && !status.isEmpty()){
			countCriteria.add(Restrictions.eq("status", status)); 
    		listCriteria.add(Restrictions.eq("status", status)); 
		}

        listCriteria.setFirstResult((pageNo-1)*pageSize);  
        listCriteria.setMaxResults(pageSize);
        List<Outofstockdetail> items = listCriteria.list();
        countCriteria.setProjection(Projections.rowCount());
        Integer count=Integer.parseInt(countCriteria.uniqueResult().toString());
        
        return PageListUtil.getPageList(count, pageNo, items, pageSize);
    }
	
	@Override
	public void saveOutofstockdetail(Outofstockdetail outofstockdetail) throws ValidatException, EntityOperateException{
		super.save(outofstockdetail);
	}
	
	@Override
	public void updateOutofstockdetail(Outofstockdetail outofstockdetail) throws ValidatException, EntityOperateException{
		Outofstockdetail dbModel=super.get(outofstockdetail.getId());
		dbModel.setoutofstockdetailId(outofstockdetail.getoutofstockdetailId());
		dbModel.setoutofstockId(outofstockdetail.getoutofstockId());
		dbModel.setproductId(outofstockdetail.getproductId());
		dbModel.setquantityDemand(outofstockdetail.getquantityDemand());
		dbModel.setquantitySupplied(outofstockdetail.getquantitySupplied());
		dbModel.setquantityNeeded(outofstockdetail.getquantityNeeded());
		dbModel.setoperatorName(outofstockdetail.getoperatorName());
		dbModel.setoperateTime(outofstockdetail.getoperateTime());
		dbModel.setstatus(outofstockdetail.getstatus());
		dbModel.setnote(outofstockdetail.getnote());
		super.update(dbModel);
	}
	
	@Override
	public List<Outofstockdetail> getoutofstockdetaillist(String outofstockId) {
		List<Outofstockdetail> list = super.listAll();
		List<Outofstockdetail> details = new ArrayList<>();
		for(Outofstockdetail od:list) {
			if(od.getoutofstockId().equals(outofstockId)) {
				details.add(od);
			}
		}
		return details;
	}
}
