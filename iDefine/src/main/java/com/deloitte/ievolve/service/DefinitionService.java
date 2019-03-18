package com.deloitte.ievolve.service;

import java.util.ArrayList;
import java.util.TreeSet;

import org.springframework.stereotype.Service;

import com.deloitte.ievolve.entity.DefinitionEntity;

@Service
public interface DefinitionService {

	public boolean addDefinition(DefinitionEntity iDefineMasterInsertEntity);

	public TreeSet<String> getKeyWords();

	public ArrayList<DefinitionEntity> getDefinition(String inputKeyword, String category);

	public boolean updateVote(String definitionId, int upVote, int downVote);

	public boolean deleteDefinition(String definitionId);

	public boolean updateDefinition(DefinitionEntity iDefineMasterEntity);
	
}
