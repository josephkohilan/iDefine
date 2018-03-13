package com.deloitte.idefine.dao.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.deloitte.idefine.dao.IDefineDAO;
import com.deloitte.idefine.entity.IDefineMasterEntity;
import com.deloitte.idefine.entity.IDefineMasterInsertEntity;
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
	public boolean addDefinition(IDefineMasterInsertEntity iDefineMasterInsertEntity) {
		boolean result = false;
		try {
			entityManager.persist(iDefineMasterInsertEntity);
			result = true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	@Override
	public boolean updateVote(String definitionId, int upVote, int downVote) {
		boolean result = false;
		try {
			String sql = "UPDATE idefine_master_table SET up_votes = up_votes + (" + upVote
					+ "), down_votes = down_votes + (" + downVote + ") WHERE definition_id = '" + definitionId + "'";
			Query query = entityManager.createNativeQuery(sql);
			query.executeUpdate();
			result = true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

}
