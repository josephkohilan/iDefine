package com.deloitte.idefine.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.deloitte.idefine.bo.IDefineBO;
import com.deloitte.idefine.entity.IDefineMasterEntity;
import com.deloitte.idefine.entity.IDefineMasterInsertEntity;
import com.deloitte.idefine.exception.IDefineException;
import com.deloitte.idefine.utility.ExceptionConstants;
import com.deloitte.idefine.utility.UtilConstants;
import com.deloitte.idefine.utility.UtilMethods;

@Controller
public class IDefineController {

	@Autowired
	private IDefineMasterInsertEntity iDefineMasterInsertEntity;

	@Autowired
	private IDefineBO iDefineBO;

	@RequestMapping("iEvolve")
	public ModelAndView homePage() {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("index");
		return modelAndView;
	}

	@PostMapping("getKeyWords")
	public @ResponseBody Set<String> getKeyWords() {
		TreeSet<String> keyWordsSet = iDefineBO.getKeyWords();
		return keyWordsSet;
	}

	@PostMapping("getDefinition")
	public @ResponseBody List<IDefineMasterEntity> getDefinition(HttpEntity<String> httpEntity)
			throws IDefineException {
		List<IDefineMasterEntity> definitionsList = new ArrayList<IDefineMasterEntity>();
		try {
			String inputKeyword = new JSONObject(httpEntity.getBody()).getString(UtilConstants.KEYWORD_KEY);
			definitionsList = iDefineBO.getDefinition(inputKeyword);
		} catch (JSONException e) {
			throw new IDefineException(ExceptionConstants.INVALID_INPUT_EXCEPTION);
		}
		return definitionsList;
	}

	@PostMapping("addDefinition")
	public @ResponseBody boolean addDefinition(HttpEntity<String> httpEntity) throws IDefineException {
		boolean result = false;
		String inputDefinitionData = httpEntity.getBody();
		if (!UtilMethods.isNullOrEmpty(inputDefinitionData)) {
			try {
				JSONObject inputDefinitionDataJson = new JSONObject(inputDefinitionData);
				iDefineMasterInsertEntity.setKeyword((String) inputDefinitionDataJson.get(UtilConstants.KEYWORD_KEY));
				iDefineMasterInsertEntity
						.setDefinition((String) inputDefinitionDataJson.get(UtilConstants.DEFINITION_KEY));
				iDefineMasterInsertEntity.setApprovalStatus(false);
				iDefineMasterInsertEntity.setUpVotes(UtilConstants.VOTE_COUNT_ZERO);
				iDefineMasterInsertEntity.setDownVotes(UtilConstants.VOTE_COUNT_ZERO);
				;
				result = iDefineBO.addDefinition(iDefineMasterInsertEntity);
			} catch (Exception e) {
				throw new IDefineException(ExceptionConstants.INVALID_INPUT_EXCEPTION);
			}
		}
		return result;
	}

	@PostMapping("updateVote")
	public @ResponseBody boolean updateVote(HttpEntity<String> httpEntity) throws IDefineException {
		boolean result = false;
		String inputVoteData = httpEntity.getBody();
		if (!UtilMethods.isNullOrEmpty(inputVoteData)) {
			try {
				JSONObject voteJSON = new JSONObject(inputVoteData);
				result = iDefineBO.updateVote((String)voteJSON.get(UtilConstants.DEFINITION_ID_KEY),
						voteJSON.getInt(UtilConstants.UPVOTE_KEY), voteJSON.getInt(UtilConstants.DOWNVOTE_KEY));
			} catch (Exception e) {
				throw new IDefineException(ExceptionConstants.INVALID_INPUT_EXCEPTION);
			}
		}
		return result;
	}

}
