package com.infrastructure.project.base.model.impl;

import com.infrastructure.project.base.model.dao.IEnableEntity;

public class EnableEntity<PKType extends Number> extends SimpleEntity<PKType> implements IEnableEntity {
	
	private boolean enable;
	
    public boolean getEnable(){
		return this.enable;
	}
    public void setEnable(boolean enable){
		this.enable=enable;
	}	

}
