package com.amc.service.interfaces;


import java.security.NoSuchAlgorithmException;

import com.amc.dao.IBackoderDao;
import com.amc.model.models.Backoder;
import com.amc.model.models.Vendor;
import com.infrastructure.project.base.service.interfaces.IEnableEntityService;
import com.infrastructure.project.common.exception.EntityOperateException;
import com.infrastructure.project.common.exception.ValidatException;
import com.infrastructure.project.common.utilities.PageList;

public interface IBackoderService extends IEnableEntityService<Integer, Backoder, IBackoderDao> {

	/*public Page<Account> listPage(AccountSearch search, int pageNo, int pageSize);
	public void saveAuthorize(Integer AccountId, Integer[] authorityIds) throws ValidatException, EntityOperateException;*/
	public PageList<Backoder> listPage(String backoderId, String backoderName, int pageNo, int pageSize);
	
	public void saveBackoder(Backoder backoder) throws NoSuchAlgorithmException, EntityOperateException, ValidatException;
	public void updateBackoder(Backoder backoder) throws NoSuchAlgorithmException, EntityOperateException, ValidatException;

	//public void updateBind(Integer id, Integer roleId, Integer organizationId) throws ValidatException, EntityOperateException;

}