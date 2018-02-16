package com.deloitte.idefine.bo.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.deloitte.idefine.bo.IDefineBO;
import com.deloitte.idefine.dao.IDefineDAO;
import com.deloitte.idefine.entity.IDefineMasterEntity;

@Service
public class IDefineBOImpl implements IDefineBO {

	@Autowired
	private IDefineDAO iDefineDAO;

	public IDefineMasterEntity searchDefinition(IDefineMasterEntity iDefineMasterEntity) {
		try {
			iDefineMasterEntity = iDefineDAO.searchDefinition(iDefineMasterEntity);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return iDefineMasterEntity;
	}
	
	@Override
	public boolean addDefinition(IDefineMasterEntity iDefineMasterEntity) {
		boolean result = false;
		try {
			result = iDefineDAO.addDefinition(iDefineMasterEntity);
		}catch(Exception e) {
			e.printStackTrace();
		}
		return result;
	}

}
