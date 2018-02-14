package com.deloitte.idefine.dao.impl;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.deloitte.idefine.dao.IDefineDAO;
import com.deloitte.idefine.dataobject.SearchDefinitionResultDO;
import com.deloitte.idefine.entity.IDefineMasterEntity;

@Transactional
@Repository
public class IDefineDAOImpl implements IDefineDAO {
	
	@PersistenceContext	
	private EntityManager entityManager;

	@Override
	public IDefineMasterEntity searchDefinition(SearchDefinitionResultDO searchDefinitionResultDO) {
		return entityManager.find(IDefineMasterEntity.class, searchDefinitionResultDO.getKeyword());
	}

}
