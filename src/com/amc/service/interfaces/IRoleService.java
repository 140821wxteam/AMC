package com.amc.service.interfaces;

import com.amc.dao.IRoleDao;
import com.amc.model.models.Role;
import com.amc.service.models.RoleSearch;
import com.infrastructure.project.base.service.interfaces.IEnableEntityService;
import com.infrastructure.project.common.exception.EntityOperateException;
import com.infrastructure.project.common.exception.ValidatException;
import com.infrastructure.project.common.utilities.PageList;

public interface IRoleService extends IEnableEntityService<Integer, Role, IRoleDao> {

	public PageList<Role> listPage(RoleSearch search, int pageNo, int pageSize);
	public void saveAuthorize(Integer roleId, Integer[] authorityIds) throws ValidatException, EntityOperateException;
	public void updateRole(Role role) throws ValidatException, EntityOperateException;
}