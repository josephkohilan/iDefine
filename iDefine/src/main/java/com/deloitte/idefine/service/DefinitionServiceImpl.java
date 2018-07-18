package com.deloitte.idefine.service;

import java.util.ArrayList;
import java.util.List;
import java.util.TreeSet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.deloitte.idefine.entity.DefinitionEntity;
import com.deloitte.idefine.repository.DefinitionRepository;
import com.deloitte.idefine.utility.UtilMethods;

@Service
public class DefinitionServiceImpl implements DefinitionService {

	@Autowired
	private DefinitionRepository iDefineDAO;
	
	@Override
	public boolean addDefinition(DefinitionEntity iDefineMasterEntity) {
		return iDefineDAO.addDefinition(iDefineMasterEntity);
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
						keyWordsSet.add(keyword.trim().toLowerCase());
					}
				}
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		return keyWordsSet;
	}

	@Override
	public ArrayList<DefinitionEntity> getDefinition(String inputKeyword) {
		return iDefineDAO.getDefinition(inputKeyword);
	}

	@Override
	public boolean updateVote(String definitionId, int upVote, int downVote) {
		return iDefineDAO.updateVote(definitionId, upVote, downVote);
	}

}
