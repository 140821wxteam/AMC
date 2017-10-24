package com.amc.service.interfaces;

import com.amc.dao.IOrganizationDao;
import com.amc.model.models.Organization;
import com.infrastructure.project.base.service.interfaces.IChainEntityService;
import com.infrastructure.project.common.utilities.PageList;

public interface IOrganizationService extends IChainEntityService<Integer, Organization, IOrganizationDao> {

	public PageList<Organization> listPage(String name, int pageNo, int pageSize);
	
}