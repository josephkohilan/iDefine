package com.deloitte.idefine.bo;

import java.util.List;
import java.util.TreeSet;

import org.springframework.stereotype.Service;

import com.deloitte.idefine.entity.IDefineMasterEntity;
import com.deloitte.idefine.entity.IDefineMasterInsertEntity;

@Service
public interface IDefineBO {

	public boolean addDefinition(IDefineMasterInsertEntity iDefineMasterInsertEntity);

	public TreeSet<String> getKeyWords();

	public List<IDefineMasterEntity> getDefinition(String inputKeyword);

	public boolean updateVote(String definitionId, int upVote, int downVote);
	
}
