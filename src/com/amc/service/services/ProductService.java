package com.amc.service.services;

import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.amc.dao.IProductDao;
import com.amc.model.models.Customers;
import com.amc.model.models.Product;
import com.amc.service.interfaces.IProductService;
import com.infrastructure.project.base.service.services.EnableEntityService;
import com.infrastructure.project.common.exception.EntityOperateException;
import com.infrastructure.project.common.exception.ValidatException;
import com.infrastructure.project.common.utilities.PageList;
import com.infrastructure.project.common.utilities.PageListUtil;;

@Service("ProductService")
public class ProductService extends EnableEntityService<Integer, Product, IProductDao> implements IProductService {
	
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
	public ProductService(@Qualifier("ProductDao")IProductDao productDao){	
		super(productDao);
	}
	
	@Override
	@SuppressWarnings("unchecked")
	public PageList<Product> listPage(String productId, String productName, int pageNo, int pageSize) {		
		Criteria countCriteria = entityDao.getCriteria();	
		Criteria listCriteria = entityDao.getCriteria();
		
		if(productId!=null && !productId.isEmpty()){
			countCriteria.add(Restrictions.eq("productId", productId)); 
    		listCriteria.add(Restrictions.eq("productId", productId)); 
		}
		if(productName!=null && !productName.isEmpty()){
			countCriteria.add(Restrictions.eq("productName", productName)); 
    		listCriteria.add(Restrictions.eq("productName", productName)); 
		}

        listCriteria.setFirstResult((pageNo-1)*pageSize);  
        listCriteria.setMaxResults(pageSize);
        List<Product> items = listCriteria.list();
        countCriteria.setProjection(Projections.rowCount());
        Integer count=Integer.parseInt(countCriteria.uniqueResult().toString());
        
        return PageListUtil.getPageList(count, pageNo, items, pageSize);
    }
	
	@Override
	public void saveProduct(Product product) throws ValidatException, EntityOperateException{
		super.save(product);
	}
	
	@Override
	public void updateProduct(Product product) throws ValidatException, EntityOperateException{
		Product dbModel=super.get(product.getId());
		
		dbModel.setproductId(product.getproductId());
		dbModel.setproductName(product.getproductName());
		dbModel.setproductType(product.getproductType());
		dbModel.setproductSpecification(product.getproductSpecification());
		dbModel.setproductOrigin(product.getproductOrigin());
		dbModel.setproductUnit(product.getproductUnit());
		dbModel.setsafeStock(product.getsafeStock());		
		dbModel.setnote(product.getnote());
		dbModel.setimages(product.getimages());
		super.update(dbModel);
	}

	@Override
	public List<String> listproductId() {
		List<Product> products = super.listAll();
		List<String> productsId = new ArrayList<>();
		for(Product p:products) {
			if(!productsId.contains(p.getproductId())) {
				productsId.add(p.getproductId());
			}
		}
		
		return productsId;
	}

	@Override
	public List<Product> getproduct(String productId) {
		List<Product> products = super.listAll();
		List<Product> product = new ArrayList<>();
		for(Product p:products) {
			if(p.getproductId().equals(productId)) {
				product.add(p);
			}
		}
		return product;
	}
	
	@Override
	public int getsafestock(String productId) {
		List<Product> products = super.listAll();
		int safestock = 0;
		for(Product p:products) {
			if(p.getproductId().equals(productId)) {
				safestock = p.getsafeStock();
				break;
			}
		}
		return safestock;
	}
}
