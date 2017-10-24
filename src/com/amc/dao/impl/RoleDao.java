package com.amc.dao.impl;

import org.springframework.stereotype.Repository;

import com.amc.dao.IRoleDao;
import com.amc.model.models.Role;
import com.infrastructure.project.base.dao.impl.EnableEntityDao;

@Repository("RoleDao")
public class RoleDao extends EnableEntityDao<Integer, Role> implements IRoleDao {


}
