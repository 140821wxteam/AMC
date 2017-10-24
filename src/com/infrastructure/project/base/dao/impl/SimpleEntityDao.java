package com.infrastructure.project.base.dao.impl;

import com.infrastructure.project.base.dao.ISimpleEntityDao;
import com.infrastructure.project.base.model.impl.SimpleEntity;

public abstract class SimpleEntityDao<PKType extends Number, EntityType extends SimpleEntity<PKType>> 
	extends EntityDao<PKType, EntityType> implements ISimpleEntityDao<PKType, EntityType> {

}
