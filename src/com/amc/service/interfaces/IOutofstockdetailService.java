package com.amc.service.interfaces;


import java.security.NoSuchAlgorithmException;
import java.util.List;

import com.amc.dao.IOutofstockDao;
import com.amc.dao.IOutofstockdetailDao;
import com.amc.model.models.Outofstock;
import com.amc.model.models.Outofstockdetail;
import com.infrastructure.project.base.service.interfaces.IEnableEntityService;
import com.infrastructure.project.common.exception.EntityOperateException;
import com.infrastructure.project.common.exception.ValidatException;
import com.infrastructure.project.common.utilities.PageList;

public interface IOutofstockdetailService extends IEnableEntityService<Integer, Outofstockdetail, IOutofstockdetailDao> {

	public PageList<Outofstockdetail> listPage(String outofstockId, String status, int pageNo, int pageSize);
	
	public void saveOutofstockdetail(Outofstockdetail outofstockdetail) throws NoSuchAlgorithmException, EntityOperateException, ValidatException;
	
	public void updateOutofstockdetail(Outofstockdetail outofstockdetail) throws NoSuchAlgorithmException, EntityOperateException, ValidatException;
	
	public List<Outofstockdetail> getoutofstockdetaillist(String outofstockId);
}