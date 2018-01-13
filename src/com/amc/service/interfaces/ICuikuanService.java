package com.amc.service.interfaces;


import java.security.NoSuchAlgorithmException;

import com.amc.dao.ICuikuanDao;
import com.amc.model.models.Cuikuan;
import com.infrastructure.project.base.service.interfaces.IEnableEntityService;
import com.infrastructure.project.common.exception.EntityOperateException;
import com.infrastructure.project.common.exception.ValidatException;
import com.infrastructure.project.common.utilities.PageList;

public interface ICuikuanService extends IEnableEntityService<Integer, Cuikuan, ICuikuanDao> {

	public PageList<Cuikuan> listPage(String cuikuanId, String customerId, int pageNo, int pageSize);
	public void saveCuikuan(Cuikuan cuikuan) throws NoSuchAlgorithmException, EntityOperateException, ValidatException;
	public void updateCuikuan(Cuikuan cuikuan) throws NoSuchAlgorithmException, EntityOperateException, ValidatException;
	
	//public void updateInvoice(Invoice invoice) throws NoSuchAlgorithmException, EntityOperateException, ValidatException;
	
	//public double Invoicefigure(String invoiceId) throws NoSuchAlgorithmException, EntityOperateException, ValidatException;

}