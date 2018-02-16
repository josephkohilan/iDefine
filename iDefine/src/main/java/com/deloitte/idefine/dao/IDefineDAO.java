package com.deloitte.idefine.dao;

import org.springframework.stereotype.Repository;

import com.deloitte.idefine.entity.IDefineMasterEntity;

@Repository
public interface IDefineDAO {

	public IDefineMasterEntity searchDefinition(IDefineMasterEntity iDefineMasterEntity);
	
	public boolean addDefinition(IDefineMasterEntity iDefineMasterEntity);

}