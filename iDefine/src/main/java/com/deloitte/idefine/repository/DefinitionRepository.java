package com.deloitte.idefine.repository;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.deloitte.idefine.entity.DefinitionEntity;

@Repository
public interface DefinitionRepository {

	public List<String> getKeyWords();

	public ArrayList<DefinitionEntity> getDefinition(String inputKeyword);

	public boolean addDefinition(DefinitionEntity iDefineMasterInsertEntity);
	
	public boolean updateVote(String definitionId, int upVote, int downVote);

	public boolean deleteDefinition(String definitionId);

	public boolean updateDefinition(DefinitionEntity iDefineMasterEntity);

}