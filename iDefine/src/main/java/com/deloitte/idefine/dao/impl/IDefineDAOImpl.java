package com.deloitte.idefine.dao.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.deloitte.idefine.dao.IDefineDAO;
import com.deloitte.idefine.entity.IDefineMasterEntity;
import com.deloitte.idefine.utility.QueryConstants;

@Transactional
@Repository
public class IDefineDAOImpl implements IDefineDAO {

	@PersistenceContext
	private EntityManager entityManager;

	@SuppressWarnings("unchecked")
	@Override
	public List<String> getKeyWords() {
		Query query = entityManager.createNativeQuery(QueryConstants.GET_KEYWORD_QUERY);
		List<String> keywordList = query.getResultList();
		return keywordList;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<IDefineMasterEntity> getDefinition(String inputKeyword) {
		String sql = "SELECT * FROM idefine_master_table WHERE keyword like '%" + inputKeyword + "%'";
		Query query = entityManager.createNativeQuery(sql, IDefineMasterEntity.class);
		List<IDefineMasterEntity> definitionsList = query.getResultList();
		return definitionsList;
	}

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
		} catch (Exception e) {
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
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

}
