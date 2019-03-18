package com.deloitte.ievolve.service;

import java.util.ArrayList;
import java.util.List;
import java.util.TreeSet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.deloitte.ievolve.entity.DefinitionEntity;
import com.deloitte.ievolve.repository.DefinitionRepository;
import com.deloitte.ievolve.utility.UtilMethods;

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
				String category = "";
				if(keywords.contains(":") && !(keywords.indexOf(":") == keywords.length() -1)) {
					category = keywords.substring(0, keywords.indexOf(":"));
					keywords = keywords.substring(keywords.indexOf(":") + 1).trim();
				}
				if (!UtilMethods.isNullOrEmpty(keywords)) {
					String[] keywordArray = keywords.split(",");
					for (String keyword : keywordArray) {
						keyWordsSet.add(category.equals("") ? keyword.trim().toLowerCase()
								: (category.trim() + ":" + keyword.trim()).toLowerCase());
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return keyWordsSet;
	}

	@Override
	public ArrayList<DefinitionEntity> getDefinition(String inputKeyword, String category) {
		return iDefineDAO.getDefinition(inputKeyword, category);
	}

	@Override
	public boolean updateVote(String definitionId, int upVote, int downVote) {
		return iDefineDAO.updateVote(definitionId, upVote, downVote);
	}

	@Override
	public boolean deleteDefinition(String definitionId) {
		return iDefineDAO.deleteDefinition(definitionId);
	}

	@Override
	public boolean updateDefinition(DefinitionEntity iDefineMasterEntity) {
		return iDefineDAO.updateDefinition(iDefineMasterEntity);
	}

}
