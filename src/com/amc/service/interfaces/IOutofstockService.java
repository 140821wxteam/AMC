package com.amc.service.interfaces;


import java.security.NoSuchAlgorithmException;

import com.amc.dao.IOutofstockDao;
import com.amc.model.models.Outofstock;
import com.infrastructure.project.base.service.interfaces.IEnableEntityService;
import com.infrastructure.project.common.exception.EntityOperateException;
import com.infrastructure.project.common.exception.ValidatException;
import com.infrastructure.project.common.utilities.PageList;

public interface IOutofstockService extends IEnableEntityService<Integer, Outofstock, IOutofstockDao> {

	public PageList<Outofstock> listPage(String outofstockId, String status, int pageNo, int pageSize);
	
	public void saveOutofstock(Outofstock outofstock) throws NoSuchAlgorithmException, EntityOperateException, ValidatException;
	
	public void updateOutofstock(Outofstock outofstock) throws NoSuchAlgorithmException, EntityOperateException, ValidatException;
}