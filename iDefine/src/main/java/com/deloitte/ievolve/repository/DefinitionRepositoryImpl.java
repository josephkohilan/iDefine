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
		List<String> resultList = new ArrayList<>();
		Query keywordQuery = entityManager.createNativeQuery(QueryConstants.GET_KEYWORD_QUERY);
		resultList.addAll(keywordQuery.getResultList());
		Query categorykeywordQuery = entityManager.createNativeQuery(QueryConstants.GET_CATEGORY_KEYWORD_QUERY);
		resultList.addAll(categorykeywordQuery.getResultList());
		return resultList;
	}

	@SuppressWarnings("unchecked")
	@Override
	public ArrayList<DefinitionEntity> getDefinition(String inputKeyword, String category) {
		String sql = "SELECT * FROM ievolve_master_table WHERE ";
		if(null != inputKeyword && !inputKeyword.equals("")) {
			sql = sql + "keyword like '%" + inputKeyword +"%' ";
		}
		if(null != category && !category.equals("")) {
			if(null != inputKeyword && !inputKeyword.equals("")) {
				sql = sql + "AND ";
			}
			sql = sql + "category = '"+ category +"'";
		}
		sql = sql + "ORDER BY up_votes DESC, down_votes asc";
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
