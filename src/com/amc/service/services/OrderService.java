package com.amc.service.services;

import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.amc.dao.IOrderDao;
import com.amc.model.models.Order;
import com.amc.service.interfaces.IOrderService;
import com.infrastructure.project.base.service.services.EnableEntityService;
import com.infrastructure.project.common.exception.EntityOperateException;
import com.infrastructure.project.common.exception.ValidatException;
import com.infrastructure.project.common.utilities.PageList;
import com.infrastructure.project.common.utilities.PageListUtil;;

@Service("OrderService")
public class OrderService extends EnableEntityService<Integer, Order, IOrderDao> implements IOrderService {
	
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
	public OrderService(@Qualifier("OrderDao")IOrderDao orderDao){	
		super(orderDao);
	}
	
	@Override
	@SuppressWarnings("unchecked")
	public PageList<Order> listPage(String orderId, String customerId, String status, int pageNo, int pageSize) {		
		Criteria countCriteria = entityDao.getCriteria();	
		Criteria listCriteria = entityDao.getCriteria();
		
		if(orderId!=null && !orderId.isEmpty()){
			countCriteria.add(Restrictions.eq("orderId", orderId)); 
    		listCriteria.add(Restrictions.eq("orderId", orderId)); 
		}
		if(customerId!=null && !customerId.isEmpty()){
			countCriteria.add(Restrictions.eq("customerId", customerId)); 
    		listCriteria.add(Restrictions.eq("customerId", customerId)); 
		}
		if(status!=null && !status.isEmpty()){
			countCriteria.add(Restrictions.eq("status", status)); 
    		listCriteria.add(Restrictions.eq("status", status)); 
		}

        listCriteria.setFirstResult((pageNo-1)*pageSize);  
        listCriteria.setMaxResults(pageSize);
        List<Order> items = listCriteria.list();
        countCriteria.setProjection(Projections.rowCount());
        Integer count=Integer.parseInt(countCriteria.uniqueResult().toString());
        
        return PageListUtil.getPageList(count, pageNo, items, pageSize);
    }
	
	@Override
	public void saveOrder(Order order) throws ValidatException, EntityOperateException{
		super.save(order);
	}
	
	@Override
	public void updateOrder(Order order) throws ValidatException, EntityOperateException{
		Order dbModel=super.get(order.getId());
		dbModel.setorderId(order.getorderId());
		dbModel.setcreateTime(order.getcreateTime());
		dbModel.setcustomerId(order.getcustomerId());
		dbModel.settotalPrice(order.gettotalPrice());
		dbModel.setstatus(order.getstatus());
		dbModel.setnote(order.getnote());
		super.update(dbModel);
	}

	
	/*@SuppressWarnings("null")
	@Override
	public double Orderfigure(String orderId) throws NoSuchAlgorithmException, EntityOperateException, ValidatException {
		IOrderdetailService orderdetailservice = null;
		List<Orderdetail> list = new ArrayList<>();
		list = orderdetailservice.listAll();
		double totalPrice=0;
		for(Orderdetail od:list) {
			if(od.getorderId().equals(orderId)) totalPrice+=od.gettotalPrice();
		}
		return totalPrice;
		
		
	}*/
}
