package com.amc.service.services;

import java.util.ArrayList;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.amc.dao.IPreparedetailDao;
import com.amc.model.models.Preparedetail;
import com.amc.service.interfaces.IPreparedetailService;
import com.infrastructure.project.base.service.services.EnableEntityService;
import com.infrastructure.project.common.exception.EntityOperateException;
import com.infrastructure.project.common.exception.ValidatException;
import com.infrastructure.project.common.utilities.PageList;
import com.infrastructure.project.common.utilities.PageListUtil;;

@Service("PreparedetailService")
public class PreparedetailService extends EnableEntityService<Integer, Preparedetail, IPreparedetailDao> implements IPreparedetailService {
	
	@Autowired
	public PreparedetailService(@Qualifier("PreparedetailDao")IPreparedetailDao preparedetailDao){	
		super(preparedetailDao);
	}
	
	@Override
	@SuppressWarnings("unchecked")
	public PageList<Preparedetail> listPage(String prepareId, int pageNo, int pageSize) {		
		Criteria countCriteria = entityDao.getCriteria();	
		Criteria listCriteria = entityDao.getCriteria();
		
		if(prepareId!=null && !prepareId.isEmpty()){
			countCriteria.add(Restrictions.eq("prepareId", prepareId)); 
    		listCriteria.add(Restrictions.eq("prepareId", prepareId)); 
		}

        listCriteria.setFirstResult((pageNo-1)*pageSize);  
        listCriteria.setMaxResults(pageSize);
        List<Preparedetail> items = listCriteria.list();
        countCriteria.setProjection(Projections.rowCount());
        Integer count=Integer.parseInt(countCriteria.uniqueResult().toString());
        
        return PageListUtil.getPageList(count, pageNo, items, pageSize);
    }
	
	
	
	@Override
	public void savePreparedetail(Preparedetail preparedetail) throws ValidatException, EntityOperateException{
		super.save(preparedetail);
	}
	
	@Override
	public void updatePreparedetail(Preparedetail preparedetail) throws ValidatException, EntityOperateException{
		Preparedetail dbModel=super.get(preparedetail.getId());
		dbModel.setpreparedetailId(preparedetail.getpreparedetailId());
		dbModel.setprepareId(preparedetail.getprepareId());
		dbModel.setfactoryId(preparedetail.getfactoryId());
		dbModel.setproductId(preparedetail.getproductId());
		dbModel.setproductName(preparedetail.getproductName());
		dbModel.setamount(preparedetail.getamount());
		dbModel.setsize(preparedetail.getsize());
		dbModel.setpreparePers(preparedetail.getpreparePers());
		dbModel.setstatus(preparedetail.getstatus());
		dbModel.setnote(preparedetail.getnote());
		super.update(dbModel);
	}
}
