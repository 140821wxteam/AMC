package com.amc.service.services;

import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.amc.dao.IOutofstockDao;
import com.amc.model.models.Outofstock;
import com.amc.service.interfaces.IOutofstockService;
import com.infrastructure.project.base.service.services.EnableEntityService;
import com.infrastructure.project.common.exception.EntityOperateException;
import com.infrastructure.project.common.exception.ValidatException;
import com.infrastructure.project.common.utilities.PageList;
import com.infrastructure.project.common.utilities.PageListUtil;;

@Service("OutofstockService")
public class OutofstockService extends EnableEntityService<Integer, Outofstock, IOutofstockDao> implements IOutofstockService {
	
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
	public OutofstockService(@Qualifier("OutofstockDao")IOutofstockDao outofstockDao){	
		super(outofstockDao);
	}
	
	@Override
	@SuppressWarnings("unchecked")
	public PageList<Outofstock> listPage(String outofstockId, String status, int pageNo, int pageSize) {		
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
        List<Outofstock> items = listCriteria.list();
        countCriteria.setProjection(Projections.rowCount());
        Integer count=Integer.parseInt(countCriteria.uniqueResult().toString());
        
        return PageListUtil.getPageList(count, pageNo, items, pageSize);
    }
	
	@Override
	public void saveOutofstock(Outofstock outofstock) throws ValidatException, EntityOperateException{
		super.save(outofstock);
	}
	
	@Override
	public void updateOutofstock(Outofstock outofstock) throws ValidatException, EntityOperateException{
		Outofstock dbModel=super.get(outofstock.getId());
		dbModel.setoutofstockId(outofstock.getoutofstockId());
		dbModel.setorderId(outofstock.getorderId());
		dbModel.setcustomerId(outofstock.getcustomerId());
		dbModel.setorderNum(outofstock.getorderNum());
		dbModel.setfitNum(outofstock.getfitNum());
		dbModel.setpartfitNum(outofstock.getpartfitNum());
		dbModel.setoutofstockNum(outofstock.getoutofstockNum());
		dbModel.setcreateTime(outofstock.getcreateTime());
		dbModel.setstatus(outofstock.getstatus());
		dbModel.setnote(outofstock.getnote());
		super.update(dbModel);
	}
}
