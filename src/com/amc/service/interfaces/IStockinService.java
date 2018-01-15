package com.amc.service.interfaces;


import java.security.NoSuchAlgorithmException;

import com.amc.dao.IStockinDao;
import com.amc.model.models.Stockin;
import com.infrastructure.project.base.service.interfaces.IEnableEntityService;
import com.infrastructure.project.common.exception.EntityOperateException;
import com.infrastructure.project.common.exception.ValidatException;
import com.infrastructure.project.common.utilities.PageList;

public interface IStockinService extends IEnableEntityService<Integer, Stockin, IStockinDao> {

	/*public Page<Account> listPage(AccountSearch search, int pageNo, int pageSize);
	public void saveAuthorize(Integer AccountId, Integer[] authorityIds) throws ValidatException, EntityOperateException;*/
	public PageList<Stockin> listPage(String stockinId, String status, int pageNo, int pageSize);
	
	public void saveStockin(Stockin stockin) throws NoSuchAlgorithmException, EntityOperateException, ValidatException;
	public void updateStockin(Stockin stockin) throws NoSuchAlgorithmException, EntityOperateException, ValidatException;
	//public void updateBind(Integer id, Integer roleId, Integer organizationId) throws ValidatException, EntityOperateException;

}