package com.amc.model.models;

import java.util.List;
import com.infrastructure.project.base.model.dao.ICUDEable;
import com.infrastructure.project.base.model.dao.IEnableable;
import com.infrastructure.project.base.model.impl.EnableEntity;

public class Role extends EnableEntity<Integer> implements ICUDEable,IEnableable {

	private List<Authority> authorities;
	
	public void setAuthorities(List<Authority> authorities){
		this.authorities=authorities;
	}
	
	public List<Authority> getAuthorities(){
		return this.authorities;
	}
	
}
