package com.deloitte.idefine.bo;

import org.springframework.stereotype.Service;

import com.deloitte.idefine.dataobject.SearchDefinitionResultDO;

@Service
public interface IDefineBO {

	public SearchDefinitionResultDO searchDefinition(SearchDefinitionResultDO searchDefinitionResultDO);

}
