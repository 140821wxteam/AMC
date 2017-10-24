package com.amc.web.models.extension;

import com.amc.model.models.Role;
import com.amc.service.models.RoleSearch;
import com.amc.web.models.RoleEditModel;
import com.amc.web.models.RoleSearchModel;

public class RoleModelExtension {
	public static RoleSearch toRoleSearch(RoleSearchModel searchModel){
		RoleSearch ret=new RoleSearch();
		ret.setName(searchModel.getName());
		
		return ret;
	}
	
	public static Role toRole(RoleEditModel editModel){
		Role role=new Role();
		role.setId(editModel.getId());
		role.setName(editModel.getName());
		return role;
	}
}
