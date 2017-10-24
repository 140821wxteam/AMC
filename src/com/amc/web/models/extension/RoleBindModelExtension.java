package com.amc.web.models.extension;

import com.amc.model.models.Role;
import com.amc.web.models.RoleBindModel;

public class RoleBindModelExtension {
	
	public static RoleBindModel toRoleBindModel(Role role){
		RoleBindModel ret=new RoleBindModel();
		ret.setName(role.getName());
		
		return ret;
	}
	
}
