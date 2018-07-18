package com.deloitte.idefine.repository;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.deloitte.idefine.entity.DefinitionEntity;
import com.deloitte.idefine.utility.QueryConstants;

@Transactional
@Repository
public class DefinitionRepositoryImpl implements DefinitionRepository {

	@PersistenceContext
	private EntityManager entityManager;

	@SuppressWarnings("unchecked")
	@Override
	public List<String> getKeyWords() {
		Query query = entityManager.createNativeQuery(QueryConstants.GET_KEYWORD_QUERY);
		return query.getResultList();
	}

	@SuppressWarnings("unchecked")
	@Override
	public ArrayList<DefinitionEntity> getDefinition(String inputKeyword) {
		String sql = "SELECT * FROM idefine_master_table WHERE keyword like '%" + inputKeyword
				+ "%' ORDER BY up_votes DESC";
		Query query = entityManager.createNativeQuery(sql, DefinitionEntity.class);
		return (ArrayList<DefinitionEntity>) query.getResultList();
	}

	@Override
	public boolean addDefinition(DefinitionEntity iDefineMasterEntity) {
		entityManager.persist(iDefineMasterEntity);
		return true;
	}

	@Override
	public boolean updateVote(String definitionId, int upVote, int downVote) {
		String sql = "UPDATE idefine_master_table SET up_votes = '" + upVote + "', down_votes = '" + downVote
				+ "' WHERE definition_id = '" + definitionId + "'";
		Query query = entityManager.createNativeQuery(sql);
		query.executeUpdate();
		return true;
	}

}
