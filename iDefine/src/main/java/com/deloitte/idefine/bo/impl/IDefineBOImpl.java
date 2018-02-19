package com.deloitte.idefine.bo.impl;

import java.util.List;
import java.util.TreeSet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.deloitte.idefine.bo.IDefineBO;
import com.deloitte.idefine.dao.IDefineDAO;
import com.deloitte.idefine.entity.IDefineMasterEntity;
import com.deloitte.idefine.utility.UtilMethods;

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
	
	@Override
	public boolean modifyDefinition(IDefineMasterEntity iDefineMasterEntity) {
		boolean result = false;
		try {
			result = iDefineDAO.modifyDefinition(iDefineMasterEntity);
		}catch(Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	@Override
	public TreeSet<String> getKeyWords() {
		TreeSet<String> keyWordsSet = new TreeSet<String>();
		try {
			List<String> keywordList = iDefineDAO.getKeyWords();
			for (String keywords : keywordList) {
				if(!UtilMethods.isNullOrEmpty(keywords)) {
					String[] keywordArray = keywords.split(",");
					for (String keyword : keywordArray) {
						keyWordsSet.add(keyword);
					}
				}
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		return keyWordsSet;
	}

}
