package com.amc.service.interfaces;

import com.amc.dao.IAuthorityDao;
import com.amc.model.models.Authority;
import com.amc.service.models.AuthoritySearch;
import com.infrastructure.project.base.service.interfaces.IChainEntityService;
import com.infrastructure.project.common.utilities.PageList;

public interface IAuthorityService extends IChainEntityService<Integer, Authority, IAuthorityDao> {

	public PageList<Authority> listPage(AuthoritySearch search, int pageNo, int pageSize);
	
}