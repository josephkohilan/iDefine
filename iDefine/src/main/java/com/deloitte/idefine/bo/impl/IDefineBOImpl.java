package com.deloitte.idefine.bo.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.TreeSet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.deloitte.idefine.bo.IDefineBO;
import com.deloitte.idefine.dao.IDefineDAO;
import com.deloitte.idefine.entity.IDefineMasterEntity;
import com.deloitte.idefine.entity.IDefineMasterInsertEntity;
import com.deloitte.idefine.utility.UtilMethods;

@Service
public class IDefineBOImpl implements IDefineBO {

	@Autowired
	private IDefineDAO iDefineDAO;
	
	@Override
	public boolean addDefinition(IDefineMasterInsertEntity iDefineMasterInsertEntity) {
		boolean result = false;
		try {
			result = iDefineDAO.addDefinition(iDefineMasterInsertEntity);
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
						keyWordsSet.add(keyword.trim());
					}
				}
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		return keyWordsSet;
	}

	@Override
	public List<IDefineMasterEntity> getDefinition(String inputKeyword) {
		List<IDefineMasterEntity> definitionsList = new ArrayList<IDefineMasterEntity>();
		try {
			definitionsList = iDefineDAO.getDefinition(inputKeyword);
		}catch(Exception e) {
			e.printStackTrace();
		}
		return definitionsList;
	}

	@Override
	public boolean updateVote(String definitionId, int upVote, int downVote) {
		boolean result = false;
		try {
			result = iDefineDAO.updateVote(definitionId, upVote, downVote);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

}
