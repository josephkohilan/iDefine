package com.deloitte.idefine.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.deloitte.idefine.entity.IDefineMasterEntity;

@Repository
public interface IDefineDAO {

	public IDefineMasterEntity searchDefinition(IDefineMasterEntity iDefineMasterEntity);
	
	public boolean addDefinition(IDefineMasterEntity iDefineMasterEntity);

	public boolean modifyDefinition(IDefineMasterEntity iDefineMasterEntity);
	
	public List<String> getKeyWords();

	public List<IDefineMasterEntity> getDefinition(String inputKeyword);

}