package com.amc.dao.impl;

import org.springframework.stereotype.Repository;

import com.amc.dao.IOrganizationDao;
import com.amc.model.models.Organization;
import com.infrastructure.project.base.dao.impl.ChainEntityDao;
import com.infrastructure.project.common.exception.EntityOperateException;
import com.infrastructure.project.common.exception.ValidatException;

@Repository("OrganizationDao")
public class OrganizationDao extends ChainEntityDao<Integer, Organization> implements IOrganizationDao {

	@Override
	public void delete(Organization organization) throws EntityOperateException, ValidatException{
		super.checkNull(organization);
		super.checkUpdatable(organization);
		if(organization.getChildren()!=null && !organization.getChildren().isEmpty())
    		throw new ValidatException("The entity has children can't be delete!");
    	else
    		super.getSession().delete(organization);
	}

}
