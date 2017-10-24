package com.infrastructure.project.base.dao;

import com.infrastructure.project.base.model.impl.ChainEntity;

public interface IChainEntityDao<PKType extends Number, EntityType extends ChainEntity<PKType, EntityType>> extends IEnableEntityDao<PKType, EntityType> {
	
}
