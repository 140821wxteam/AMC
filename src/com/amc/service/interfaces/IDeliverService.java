package com.amc.service.interfaces;


import java.security.NoSuchAlgorithmException;

import com.amc.dao.IDeliverDao;
import com.amc.model.models.Deliver;
import com.infrastructure.project.base.service.interfaces.IEnableEntityService;
import com.infrastructure.project.common.exception.EntityOperateException;
import com.infrastructure.project.common.exception.ValidatException;
import com.infrastructure.project.common.utilities.PageList;

public interface IDeliverService extends IEnableEntityService<Integer, Deliver, IDeliverDao> {

	public PageList<Deliver> listPage(String prepareId, String customerId, int pageNo, int pageSize);
	public PageList<Deliver> listPageByPrepare(String prepareId, String customerId, int pageNo, int pageSize, IPrepareService prepareService);
	
	public void saveDeliver(Deliver prepare) throws NoSuchAlgorithmException, EntityOperateException, ValidatException;
	
	public void updateDeliver(Deliver prepare) throws NoSuchAlgorithmException, EntityOperateException, ValidatException;
	
	//public double Preparefigure(String prepareId) throws NoSuchAlgorithmException, EntityOperateException, ValidatException;

}