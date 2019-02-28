package com.deloitte.ievolve.repository;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.deloitte.ievolve.entity.DefinitionEntity;
import com.deloitte.ievolve.utility.QueryConstants;

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
		String sql = "SELECT * FROM ievolve_master_table WHERE keyword like '%" + inputKeyword
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
		String sql = "UPDATE ievolve_master_table SET up_votes = '" + upVote + "', down_votes = '" + downVote
				+ "' WHERE definition_id = '" + definitionId + "'";
		Query query = entityManager.createNativeQuery(sql);
		query.executeUpdate();
		return true;
	}

	@Override
	public boolean deleteDefinition(String definitionId) {
		String sql = "DELETE from ievolve_master_table WHERE definition_id = '" + definitionId + "'";
		Query query = entityManager.createNativeQuery(sql);
		query.executeUpdate();
		return true;
	}

	@Override
	public boolean updateDefinition(DefinitionEntity iDefineMasterEntity) {
		String sql = "update ievolve_master_table set definition = '"+iDefineMasterEntity.getDefinition()+"', "
				+ "approval_status = "+ iDefineMasterEntity.getApprovalStatus() +", up_votes = '"+ iDefineMasterEntity.getUpVotes() +"', "
				+ "down_votes = '"+ iDefineMasterEntity.getDownVotes() +"', created_date = '"+ iDefineMasterEntity.getCreatedDate() +"' "
				+ "WHERE definition_id = '" + iDefineMasterEntity.getDefinitionId() + "'";
		Query query = entityManager.createNativeQuery(sql);
		query.executeUpdate();
		return true;
	}

}
