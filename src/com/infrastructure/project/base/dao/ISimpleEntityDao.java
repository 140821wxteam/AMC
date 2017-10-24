package com.infrastructure.project.base.dao;

import com.infrastructure.project.base.model.impl.SimpleEntity;

public interface ISimpleEntityDao<PKType extends Number, EntityType extends SimpleEntity<PKType>> extends IEntityDao<PKType, EntityType> {
	
}
