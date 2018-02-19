package com.deloitte.idefine.dao.impl;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.deloitte.idefine.dao.IDefineDAO;
import com.deloitte.idefine.entity.IDefineMasterEntity;

@Transactional
@Repository
public class IDefineDAOImpl implements IDefineDAO {
	
	@PersistenceContext	
	private EntityManager entityManager;

	@Override
	public IDefineMasterEntity searchDefinition(IDefineMasterEntity iDefineMasterEntity) {
		return entityManager.find(IDefineMasterEntity.class, iDefineMasterEntity.getKeyword());
	}

	@Override
	public boolean addDefinition(IDefineMasterEntity iDefineMasterEntity) {
		boolean result = false;
		try {
			entityManager.persist(iDefineMasterEntity);
			result = true;
		}catch(Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	
	@Override
	public boolean modifyDefinition(IDefineMasterEntity iDefineMasterEntity) {
		boolean result = false;
		try {
			IDefineMasterEntity defineMasterEntity = searchDefinition(iDefineMasterEntity);
			defineMasterEntity.setDefinition(iDefineMasterEntity.getDefinition());
			defineMasterEntity.setRelatedKeys(iDefineMasterEntity.getRelatedKeys());
			entityManager.flush();
			result = true;
		}catch(Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	
}
