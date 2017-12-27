package com.amc.service.services;

import java.util.ArrayList;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.criterion.Expression;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.sql.JoinType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.amc.dao.IDeliverDao;
import com.amc.model.models.Deliver;
import com.amc.model.models.Prepare;
import com.amc.service.interfaces.IDeliverService;
import com.amc.service.interfaces.IPrepareService;
import com.infrastructure.project.base.service.services.EnableEntityService;
import com.infrastructure.project.common.exception.EntityOperateException;
import com.infrastructure.project.common.exception.ValidatException;
import com.infrastructure.project.common.utilities.PageList;
import com.infrastructure.project.common.utilities.PageListUtil;;

@Service("DeliverService")
public class DeliverService extends EnableEntityService<Integer, Deliver, IDeliverDao> implements IDeliverService {
	
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
	public DeliverService(@Qualifier("DeliverDao")IDeliverDao deliverDao){	
		super(deliverDao);
	}
	
	@Override
	@SuppressWarnings("unchecked")
	public PageList<Deliver> listPage(String deliverId, String customerId, int pageNo, int pageSize) {		
		Criteria countCriteria = entityDao.getCriteria();	
		Criteria listCriteria = entityDao.getCriteria();
		
		if(deliverId!=null && !deliverId.isEmpty()){
			countCriteria.add(Restrictions.eq("deliverId", deliverId)); 
    		listCriteria.add(Restrictions.eq("deliverId", deliverId)); 
		}
		if(customerId!=null && !customerId.isEmpty()){
			countCriteria.add(Restrictions.eq("customerId", customerId)); 
    		listCriteria.add(Restrictions.eq("customerId", customerId)); 
		}

        listCriteria.setFirstResult((pageNo-1)*pageSize);  
        listCriteria.setMaxResults(pageSize);
        /*listCriteria.createAlias("Deliver", "deliver").createAlias("Prepare", "prepare") 
            .add( Restrictions.eqProperty("deliver.prepareId", "prepare.prepareId") ) 
            .list(); */
        //listCriteria.add(Restrictions.sqlRestriction("select * from t_deliver, t_prepare where t_deliver.prepareId=t_prepare.prepareID"));
        List<Deliver> items = listCriteria.list();

        countCriteria.setProjection(Projections.rowCount());
        Integer count=Integer.parseInt(countCriteria.uniqueResult().toString());
        
        return PageListUtil.getPageList(count, pageNo, items, pageSize);
    }
	
	@Override
	@SuppressWarnings("unchecked")
	public PageList<Deliver> listPageByPrepare(String deliverId, String customerId, int pageNo, int pageSize, IPrepareService prepareService) {		
		Criteria countCriteria = entityDao.getCriteria();	
		Criteria listCriteria = entityDao.getCriteria();
		
		if(deliverId!=null && !deliverId.isEmpty()){
			countCriteria.add(Restrictions.eq("deliverId", deliverId)); 
    		listCriteria.add(Restrictions.eq("deliverId", deliverId)); 
		}
		if(customerId!=null && !customerId.isEmpty()){
			countCriteria.add(Restrictions.eq("customerId", customerId)); 
    		listCriteria.add(Restrictions.eq("customerId", customerId)); 
		}

        listCriteria.setFirstResult((pageNo-1)*pageSize);  
        listCriteria.setMaxResults(pageSize);
       
        List<Deliver> items = listCriteria.list();
        
        List<Prepare> itemsPre = prepareService.listAll();
        for(Deliver item:items){
        	for(Prepare item2:itemsPre){
        		if(item.getPrepareId().equals(item2.getprepareId())){
        			item.setOrderId(item2.getorderId());
        			item.setCustomerId(item2.getcustomerId());
        			if(item2.getreceivePers()!=null)
        				item.setReceivePers(item2.getreceivePers());
        			if(item2.getreceiveAddr()!=null)
        			    item.setReceiveAddr(item2.getreceiveAddr());
        		}
        	}
        }
        countCriteria.setProjection(Projections.rowCount());
        Integer count=Integer.parseInt(countCriteria.uniqueResult().toString());
        
        return PageListUtil.getPageList(count, pageNo, items, pageSize);
    }
	
	@Override
	public void saveDeliver(Deliver deliver) throws ValidatException, EntityOperateException{
		super.save(deliver);
	}
	
	@Override
	public void updateDeliver(Deliver deliver) throws ValidatException, EntityOperateException{
		Deliver dbModel=super.get(deliver.getId());
		dbModel.setDeliverId(deliver.getDeliverId());
		dbModel.setPrepareId(deliver.getPrepareId());
		dbModel.setDeliverDetailId(deliver.getDeliverDetailId());
		dbModel.setCreateTime(deliver.getCreateTime());
		dbModel.setAmountMoney(deliver.getAmountMoney());
		dbModel.setRemark(deliver.getRemark());
		dbModel.setStatus(deliver.getStatus());
		super.update(dbModel);
	}

}
