package com.amc.service.services;

import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.amc.dao.IDeliverDetailDao;
import com.amc.model.models.DeliverDetail;
import com.amc.service.interfaces.IDeliverDetailService;
import com.infrastructure.project.base.service.services.EnableEntityService;
import com.infrastructure.project.common.utilities.PageList;
import com.infrastructure.project.common.utilities.PageListUtil;;

@Service("DeliverDetailService")
public class DeliverDetailService extends EnableEntityService<Integer, DeliverDetail, IDeliverDetailDao> implements IDeliverDetailService {
	
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
	public DeliverDetailService(@Qualifier("DeliverDetailDao")IDeliverDetailDao deliverDetailDao){	
		super(deliverDetailDao);
	}
	
	@Override
	@SuppressWarnings("unchecked")
	public PageList<DeliverDetail> listPage(String deliverId,int pageNo, int pageSize) {		
		Criteria countCriteria = entityDao.getCriteria();	
		Criteria listCriteria = entityDao.getCriteria();

		if(deliverId!=null && !deliverId.isEmpty()){
			countCriteria.add(Restrictions.eq("deliverId", deliverId)); 
    		listCriteria.add(Restrictions.eq("deliverId", deliverId)); 
		}
		

        listCriteria.setFirstResult((pageNo-1)*pageSize);  
        listCriteria.setMaxResults(pageSize);
        List<DeliverDetail> items = listCriteria.list();
        countCriteria.setProjection(Projections.rowCount());
        Integer count=Integer.parseInt(countCriteria.uniqueResult().toString());
        
        return PageListUtil.getPageList(count, pageNo, items, pageSize);
    }

}
