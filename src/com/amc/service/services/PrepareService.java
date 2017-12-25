package com.amc.service.services;

import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.amc.dao.IPrepareDao;
import com.amc.model.models.Prepare;
import com.amc.service.interfaces.IPrepareService;
import com.infrastructure.project.base.service.services.EnableEntityService;
import com.infrastructure.project.common.exception.EntityOperateException;
import com.infrastructure.project.common.exception.ValidatException;
import com.infrastructure.project.common.utilities.PageList;
import com.infrastructure.project.common.utilities.PageListUtil;;

@Service("PrepareService")
public class PrepareService extends EnableEntityService<Integer, Prepare, IPrepareDao> implements IPrepareService {
	
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
	public PrepareService(@Qualifier("PrepareDao")IPrepareDao prepareDao){	
		super(prepareDao);
	}
	
	@Override
	@SuppressWarnings("unchecked")
	public PageList<Prepare> listPage(String prepareId, String customerId, int pageNo, int pageSize) {		
		Criteria countCriteria = entityDao.getCriteria();	
		Criteria listCriteria = entityDao.getCriteria();
		
		if(prepareId!=null && !prepareId.isEmpty()){
			countCriteria.add(Restrictions.eq("prepareId", prepareId)); 
    		listCriteria.add(Restrictions.eq("prepareId", prepareId)); 
		}
		if(customerId!=null && !customerId.isEmpty()){
			countCriteria.add(Restrictions.eq("customerId", customerId)); 
    		listCriteria.add(Restrictions.eq("customerId", customerId)); 
		}

        listCriteria.setFirstResult((pageNo-1)*pageSize);  
        listCriteria.setMaxResults(pageSize);
        List<Prepare> items = listCriteria.list();
        countCriteria.setProjection(Projections.rowCount());
        Integer count=Integer.parseInt(countCriteria.uniqueResult().toString());
        
        return PageListUtil.getPageList(count, pageNo, items, pageSize);
    }
	
	@Override
	public void savePrepare(Prepare prepare) throws ValidatException, EntityOperateException{
		super.save(prepare);
	}
	
	@Override
	public void updatePrepare(Prepare prepare) throws ValidatException, EntityOperateException{
		Prepare dbModel=super.get(prepare.getId());
		dbModel.setprepareId(prepare.getprepareId());
		dbModel.setcreateTime(prepare.getcreateTime());
		dbModel.setcustomerId(prepare.getcustomerId());
		dbModel.setorderNum(prepare.getorderNum());
		dbModel.setfitNum(prepare.getfitNum());
		dbModel.setpartfitNum(prepare.getpartfitNum());
		dbModel.setoutofstockNum(prepare.getoutofstockNum());
		dbModel.setstatus(prepare.getstatus());
		dbModel.setnote(prepare.getnote());
		super.update(dbModel);
	}

	
	/*@SuppressWarnings("null")
	@Override
	public double Preparefigure(String prepareId) throws NoSuchAlgorithmException, EntityOperateException, ValidatException {
		IPreparedetailService preparedetailservice = null;
		List<Preparedetail> list = new ArrayList<>();
		list = preparedetailservice.listAll();
		double totalPrice=0;
		for(Preparedetail od:list) {
			if(od.getprepareId().equals(prepareId)) totalPrice+=od.gettotalPrice();
		}
		return totalPrice;
		
		
	}*/
}
