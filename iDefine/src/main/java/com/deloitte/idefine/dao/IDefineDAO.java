package com.deloitte.idefine.dao;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.deloitte.idefine.entity.IDefineMasterEntity;
import com.deloitte.idefine.entity.IDefineMasterInsertEntity;

@Repository
public interface IDefineDAO {

	public List<String> getKeyWords();

	public ArrayList<IDefineMasterEntity> getDefinition(String inputKeyword);

	public boolean addDefinition(IDefineMasterInsertEntity iDefineMasterInsertEntity);
	
	public boolean updateVote(String definitionId, int upVote, int downVote);

}