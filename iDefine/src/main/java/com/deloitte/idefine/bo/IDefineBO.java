package com.deloitte.idefine.bo;

import java.util.List;
import java.util.TreeSet;

import org.springframework.stereotype.Service;

import com.deloitte.idefine.entity.IDefineMasterEntity;

@Service
public interface IDefineBO {

	public IDefineMasterEntity searchDefinition(IDefineMasterEntity iDefineMasterEntity);
	
	public boolean addDefinition(IDefineMasterEntity iDefineMasterEntity);

	public boolean modifyDefinition(IDefineMasterEntity iDefineMasterEntity);

	public TreeSet<String> getKeyWords();

	public List<IDefineMasterEntity> getDefinition(String inputKeyword);
	
}
