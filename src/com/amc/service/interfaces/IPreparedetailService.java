package com.amc.service.interfaces;


import java.security.NoSuchAlgorithmException;
import java.util.List;

import com.amc.dao.IPreparedetailDao;
import com.amc.model.models.Preparedetail;
import com.infrastructure.project.base.service.interfaces.IEnableEntityService;
import com.infrastructure.project.common.exception.EntityOperateException;
import com.infrastructure.project.common.exception.ValidatException;
import com.infrastructure.project.common.utilities.PageList;

public interface IPreparedetailService extends IEnableEntityService<Integer, Preparedetail, IPreparedetailDao> {

	public PageList<Preparedetail> listPage(String prepareId, int pageNo, int pageSize);
	//public List<Preparedetail> listEnable();
	public void savePreparedetail(Preparedetail preparedetail) throws NoSuchAlgorithmException, EntityOperateException, ValidatException;
	public void updatePreparedetail(Preparedetail preparedetail) throws NoSuchAlgorithmException, EntityOperateException, ValidatException;

}