package com.amc.service.interfaces;


import java.security.NoSuchAlgorithmException;
import java.util.List;

import com.amc.dao.IProductDao;
import com.amc.model.models.Product;
import com.infrastructure.project.base.service.interfaces.IEnableEntityService;
import com.infrastructure.project.common.exception.EntityOperateException;
import com.infrastructure.project.common.exception.ValidatException;
import com.infrastructure.project.common.utilities.PageList;

public interface IProductService extends IEnableEntityService<Integer, Product, IProductDao> {

	/*public Page<Account> listPage(AccountSearch search, int pageNo, int pageSize);
	public void saveAuthorize(Integer AccountId, Integer[] authorityIds) throws ValidatException, EntityOperateException;*/
	public PageList<Product> listPage(String productId, String productName, int pageNo, int pageSize);
	public List<String> listproductId();//列出所有产品id
	public List<Product> getproduct(String productId);//获取与某个productid对应的产品信息
	public void saveProduct(Product product) throws NoSuchAlgorithmException, EntityOperateException, ValidatException;
	public void updateProduct(Product product) throws NoSuchAlgorithmException, EntityOperateException, ValidatException;
	//public void updateBind(Integer id, Integer roleId, Integer organizationId) throws ValidatException, EntityOperateException;

}