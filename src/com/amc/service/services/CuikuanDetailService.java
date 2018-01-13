package com.amc.service.services;

import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.amc.dao.ICuikuanDetailDao;
import com.amc.model.models.CuikuanDetail;
import com.amc.service.interfaces.ICuikuanDetailService;
import com.infrastructure.project.base.service.services.EnableEntityService;
import com.infrastructure.project.common.utilities.PageList;
import com.infrastructure.project.common.utilities.PageListUtil;;

@Service("CuikuanDetailService")
public class CuikuanDetailService extends EnableEntityService<Integer, CuikuanDetail, ICuikuanDetailDao> implements ICuikuanDetailService {
	
	@Autowired
	public CuikuanDetailService(@Qualifier("CuikuanDetailDao")ICuikuanDetailDao cuikuanDetailDao){	
		super(cuikuanDetailDao);
	}
	
	@Override
	@SuppressWarnings("unchecked")
	public PageList<CuikuanDetail> listPage(String cuikuanId,int pageNo, int pageSize) {		
		Criteria countCriteria = entityDao.getCriteria();	
		Criteria listCriteria = entityDao.getCriteria();

		if(cuikuanId!=null && !cuikuanId.isEmpty()){
			countCriteria.add(Restrictions.eq("cuikuanId", cuikuanId)); 
    		listCriteria.add(Restrictions.eq("cuikuanId", cuikuanId)); 
		}
		

        listCriteria.setFirstResult((pageNo-1)*pageSize);  
        listCriteria.setMaxResults(pageSize);
        List<CuikuanDetail> items = listCriteria.list();
        countCriteria.setProjection(Projections.rowCount());
        Integer count=Integer.parseInt(countCriteria.uniqueResult().toString());
        
        return PageListUtil.getPageList(count, pageNo, items, pageSize);
    }

}
