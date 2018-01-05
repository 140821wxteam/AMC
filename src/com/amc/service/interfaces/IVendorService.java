package com.amc.service.interfaces;


import java.security.NoSuchAlgorithmException;
import java.util.List;

import com.amc.dao.IVendorDao;
import com.amc.model.models.Vendor;
import com.infrastructure.project.base.service.interfaces.IEnableEntityService;
import com.infrastructure.project.common.exception.EntityOperateException;
import com.infrastructure.project.common.exception.ValidatException;
import com.infrastructure.project.common.utilities.PageList;

public interface IVendorService extends IEnableEntityService<Integer, Vendor, IVendorDao> {

	/*public Page<Account> listPage(AccountSearch search, int pageNo, int pageSize);
	public void saveAuthorize(Integer AccountId, Integer[] authorityIds) throws ValidatException, EntityOperateException;*/
	public PageList<Vendor> listPage(String vendorId, String vendorName, int pageNo, int pageSize);
	public List<String> listvendorId();//列出所有供应商id
	public void saveVendor(Vendor vendor) throws NoSuchAlgorithmException, EntityOperateException, ValidatException;
	public void updateVendor(Vendor vendor) throws NoSuchAlgorithmException, EntityOperateException, ValidatException;
	//public void updateBind(Integer id, Integer roleId, Integer organizationId) throws ValidatException, EntityOperateException;

}