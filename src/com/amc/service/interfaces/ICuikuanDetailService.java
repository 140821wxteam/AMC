package com.amc.service.interfaces;

import com.amc.dao.ICuikuanDetailDao;
import com.amc.model.models.CuikuanDetail;
import com.infrastructure.project.base.service.interfaces.IEnableEntityService;
import com.infrastructure.project.common.utilities.PageList;

public interface ICuikuanDetailService extends IEnableEntityService<Integer, CuikuanDetail, ICuikuanDetailDao> {

	public PageList<CuikuanDetail> listPage(String cuikuanId,int pageNo, int pageSize);

}