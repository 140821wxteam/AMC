package com.amc.service.services;

import java.util.ArrayList;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.amc.dao.IPurchaseOrderDao;
import com.amc.dao.IVendorDao;
import com.amc.dao.impl.VendorDao;
import com.amc.model.models.PurchaseOrder;
import com.amc.model.models.Vendor;
import com.amc.service.interfaces.IPurchaseOrderService;
import com.infrastructure.project.base.service.services.EnableEntityService;
import com.infrastructure.project.common.exception.EntityOperateException;
import com.infrastructure.project.common.exception.ValidatException;
import com.infrastructure.project.common.utilities.PageList;
import com.infrastructure.project.common.utilities.PageListUtil;;

@Service("PurchaseOrderService")
public class PurchaseOrderService extends EnableEntityService<Integer, PurchaseOrder, IPurchaseOrderDao> implements IPurchaseOrderService {
	
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
	@Qualifier("VendorDao")
	private IVendorDao vendorDao;
	
	@Autowired
	public PurchaseOrderService(@Qualifier("PurchaseOrderDao")IPurchaseOrderDao purchaseOrderDao){	
		super(purchaseOrderDao);
	}
	
	
	@Override
	@SuppressWarnings("unchecked")
	public PageList<PurchaseOrder> listPage(String orderId, String vendorName, String status, int pageNo, int pageSize) {		
		Criteria countCriteria = entityDao.getCriteria();	
		Criteria listCriteria = entityDao.getCriteria();
		
		if(orderId!=null && !orderId.isEmpty()){
			countCriteria.add(Restrictions.eq("orderId", orderId)); 
    		listCriteria.add(Restrictions.eq("orderId", orderId)); 
		}
		if(vendorName!=null && !vendorName.isEmpty()){
			countCriteria.add(Restrictions.eq("vendorName", vendorName)); 
    		listCriteria.add(Restrictions.eq("vendorName", vendorName)); 
		}
		if(status!=null && !status.isEmpty()){
			countCriteria.add(Restrictions.eq("status", status)); 
    		listCriteria.add(Restrictions.eq("status", status)); 
		}

        listCriteria.setFirstResult((pageNo-1)*pageSize);  
        listCriteria.setMaxResults(pageSize);
        List<PurchaseOrder> items = listCriteria.list();
        countCriteria.setProjection(Projections.rowCount());
        Integer count=Integer.parseInt(countCriteria.uniqueResult().toString());
        
        return PageListUtil.getPageList(count, pageNo, items, pageSize);
    }
	
	@Override
	public List<String> listVendorNames() {
		List<Vendor> items = vendorDao.listAll();
		ArrayList<String> names = new ArrayList<>();
		if (items == null) return names;
		for (Vendor v: items) {
			names.add(v.getvendorName());
		}
		return names;
	}
	
	@Override
	public String getVendorId(String vendorName){
		List<Vendor> items = vendorDao.listAll();
		String vendorId = null;
		for (Vendor v: items){
			if (v.getvendorName().equals(vendorName)) {
				vendorId = v.getvendorId();
			}
		}
		return vendorId;
	}
	
	@Override
	public void saveOrder(PurchaseOrder purchaseOrder) throws ValidatException, EntityOperateException{
		super.save(purchaseOrder);
	}
	
	@Override
	public void updateOrder(PurchaseOrder purchaseOrder) throws ValidatException, EntityOperateException{
		/*PurchasingOrder dbModel=super.get(order.getId());
		dbModel.setorderId(order.getorderId());
		dbModel.setcreateTime(order.getcreateTime());
		dbModel.setcustomerId(order.getcustomerId());
		dbModel.settotalPrice(order.gettotalPrice());
		dbModel.setstatus(order.getstatus());
		dbModel.setnote(order.getnote());
		super.update(dbModel); */
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
