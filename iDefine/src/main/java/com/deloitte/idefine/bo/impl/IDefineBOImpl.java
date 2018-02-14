package com.deloitte.idefine.bo.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.deloitte.idefine.bo.IDefineBO;
import com.deloitte.idefine.dao.IDefineDAO;
import com.deloitte.idefine.dataobject.SearchDefinitionResultDO;
import com.deloitte.idefine.entity.IDefineMasterEntity;

@Service
public class IDefineBOImpl implements IDefineBO {

	@Autowired
	private IDefineDAO iDefineDAO;
	
	@Autowired
	private IDefineMasterEntity iDefineMasterEntity;

	public SearchDefinitionResultDO searchDefinition(SearchDefinitionResultDO searchDefinitionResultDO) {
		try {
			iDefineMasterEntity = iDefineDAO.searchDefinition(searchDefinitionResultDO);
			searchDefinitionResultDO.setDefinition(iDefineMasterEntity.getDefinition());
			searchDefinitionResultDO.setKeyword(iDefineMasterEntity.getKeyword());
			searchDefinitionResultDO.setRelatedSearch(iDefineMasterEntity.getRelatedKeys());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return searchDefinitionResultDO;
	}

}
