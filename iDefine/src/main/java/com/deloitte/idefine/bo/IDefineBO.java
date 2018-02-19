package com.deloitte.idefine.bo;

import org.springframework.stereotype.Service;

import com.deloitte.idefine.entity.IDefineMasterEntity;

@Service
public interface IDefineBO {

	public IDefineMasterEntity searchDefinition(IDefineMasterEntity iDefineMasterEntity);
	
	public boolean addDefinition(IDefineMasterEntity iDefineMasterEntity);

	public boolean modifyDefinition(IDefineMasterEntity iDefineMasterEntity);
	
}
