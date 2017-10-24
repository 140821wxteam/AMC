package com.infrastructure.project.base.service.interfaces;

import java.util.List;

import com.infrastructure.project.base.dao.IChainEntityDao;
import com.infrastructure.project.base.model.impl.ChainEntity;

public interface IChainEntityService<PKType extends Number, EntityType extends ChainEntity<PKType, EntityType>, IDaoType extends IChainEntityDao<PKType, EntityType>> extends IEnableEntityService<PKType, EntityType, IDaoType> {
	
	public List<EntityType> listChain();
	
}
