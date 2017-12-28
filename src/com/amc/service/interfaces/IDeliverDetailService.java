package com.amc.service.interfaces;


import java.security.NoSuchAlgorithmException;

import com.amc.dao.IDeliverDetailDao;
import com.amc.model.models.DeliverDetail;
import com.infrastructure.project.base.service.interfaces.IEnableEntityService;
import com.infrastructure.project.common.exception.EntityOperateException;
import com.infrastructure.project.common.exception.ValidatException;
import com.infrastructure.project.common.utilities.PageList;

public interface IDeliverDetailService extends IEnableEntityService<Integer, DeliverDetail, IDeliverDetailDao> {

	public PageList<DeliverDetail> listPage(String deliverId,int pageNo, int pageSize);
	//public PageList<DeliverDetail> listPageByPrepare(String prepareId, String customerId, int pageNo, int pageSize, IPrepareService prepareService);
	
	//public void saveDeliver(DeliverDetail prepare) throws NoSuchAlgorithmException, EntityOperateException, ValidatException;
	
	//public void updateDeliver(DeliverDetail prepare) throws NoSuchAlgorithmException, EntityOperateException, ValidatException;
	
	//public double Preparefigure(String prepareId) throws NoSuchAlgorithmException, EntityOperateException, ValidatException;

}