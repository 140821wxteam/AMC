package com.amc.service.services;

import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.amc.dao.IDeliverDao;
import com.amc.dao.IInvoiceDao;
import com.amc.model.models.Invoice;
import com.amc.service.interfaces.IInvoiceService;
import com.infrastructure.project.base.service.services.EnableEntityService;
import com.infrastructure.project.common.exception.EntityOperateException;
import com.infrastructure.project.common.exception.ValidatException;
import com.infrastructure.project.common.utilities.PageList;
import com.infrastructure.project.common.utilities.PageListUtil;;

@Service("InvoiceService")
public class InvoiceService extends EnableEntityService<Integer, Invoice, IInvoiceDao> implements IInvoiceService {
	
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
	public InvoiceService(@Qualifier("InvoiceDao")IInvoiceDao invoiceDao){	
		super(invoiceDao);
	}
	
	@Override
	@SuppressWarnings("unchecked")
	public PageList<Invoice> listPage(String invoiceId, String orderId, int pageNo, int pageSize) {		
		Criteria countCriteria = entityDao.getCriteria();	
		Criteria listCriteria = entityDao.getCriteria();
		
		if(invoiceId!=null && !invoiceId.isEmpty()){
			countCriteria.add(Restrictions.eq("invoiceId", invoiceId)); 
    		listCriteria.add(Restrictions.eq("invoiceId", invoiceId)); 
		}
		if(orderId!=null && !orderId.isEmpty()){
			countCriteria.add(Restrictions.eq("orderId", orderId)); 
    		listCriteria.add(Restrictions.eq("orderId", orderId)); 
		}

        listCriteria.setFirstResult((pageNo-1)*pageSize);  
        listCriteria.setMaxResults(pageSize);
        List<Invoice> items = listCriteria.list();
        countCriteria.setProjection(Projections.rowCount());
        Integer count=Integer.parseInt(countCriteria.uniqueResult().toString());
        
        return PageListUtil.getPageList(count, pageNo, items, pageSize);
    }
	
	@Override
	public void updateInvoice(Invoice invoice) throws ValidatException, EntityOperateException{
		Invoice dbModel=super.get(invoice.getId());
		dbModel.setInvoiceId(invoice.getInvoiceId());
		dbModel.setObjection(invoice.getObjection());
		dbModel.setOrderId(invoice.getOrderId());
		dbModel.setOrderReceiveDate(invoice.getOrderReceiveDate());
		dbModel.setAmountMoney(invoice.getAmountMoney());
		dbModel.setCreateTime(invoice.getCreateTime());
		dbModel.setRemark(invoice.getRemark());
		dbModel.setStatus(invoice.getStatus());
		
		super.update(dbModel);
	}

}
