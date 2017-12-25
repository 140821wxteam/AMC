package com.amc.service.interfaces;


import java.security.NoSuchAlgorithmException;

import com.amc.dao.IPrepareDao;
import com.amc.model.models.Prepare;
import com.infrastructure.project.base.service.interfaces.IEnableEntityService;
import com.infrastructure.project.common.exception.EntityOperateException;
import com.infrastructure.project.common.exception.ValidatException;
import com.infrastructure.project.common.utilities.PageList;

public interface IPrepareService extends IEnableEntityService<Integer, Prepare, IPrepareDao> {

	public PageList<Prepare> listPage(String prepareId, String customerId, int pageNo, int pageSize);
	
	public void savePrepare(Prepare prepare) throws NoSuchAlgorithmException, EntityOperateException, ValidatException;
	
	public void updatePrepare(Prepare prepare) throws NoSuchAlgorithmException, EntityOperateException, ValidatException;
	
	//public double Preparefigure(String prepareId) throws NoSuchAlgorithmException, EntityOperateException, ValidatException;

}