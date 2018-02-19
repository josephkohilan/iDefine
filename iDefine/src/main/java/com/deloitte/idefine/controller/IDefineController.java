package com.deloitte.idefine.controller;

import java.util.Set;
import java.util.TreeSet;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.deloitte.idefine.bo.IDefineBO;
import com.deloitte.idefine.entity.IDefineMasterEntity;
import com.deloitte.idefine.exception.IDefineException;
import com.deloitte.idefine.utility.ExceptionConstants;
import com.deloitte.idefine.utility.UtilConstants;
import com.deloitte.idefine.utility.UtilMethods;

@Controller
public class IDefineController {
	
	@Autowired
	private IDefineMasterEntity iDefineMasterEntity;
	
	@Autowired
	private IDefineBO iDefineBO;
	
	@RequestMapping("iEvolve")
	public ModelAndView homePage() {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("index");
		return modelAndView;
	}
	
	@PostMapping("getKeyWords")
	public @ResponseBody Set<String> getKeyWords(){
		TreeSet<String> keyWordsSet = iDefineBO.getKeyWords();
		return keyWordsSet;
	}

	@RequestMapping(value="searchdefinition",method = RequestMethod.POST)
	public @ResponseBody IDefineMasterEntity searchDefinition(HttpEntity<String> httpEntity) throws IDefineException{
		String inputKeyword = httpEntity.getBody();
		if(!UtilMethods.isNullOrEmpty(inputKeyword)) {
			iDefineMasterEntity.setKeyword(inputKeyword);
			iDefineMasterEntity = iDefineBO.searchDefinition(iDefineMasterEntity);
		}else {
			throw new IDefineException(ExceptionConstants.INVALID_INPUT_EXCEPTION);
		}
		return iDefineMasterEntity;
	}
	
	@PostMapping("addDefinition")
	public @ResponseBody boolean addDefinition(HttpEntity<String> httpEntity) throws IDefineException {
		boolean result = false;
		String inputDefinitionData = httpEntity.getBody();
		if(!UtilMethods.isNullOrEmpty(inputDefinitionData)) {
			try {
				JSONObject inputDefinitionDataJson = new JSONObject(inputDefinitionData);
				iDefineMasterEntity.setKeyword((String)inputDefinitionDataJson.get(UtilConstants.KEYWORD_KEY));
				iDefineMasterEntity.setDefinition((String)inputDefinitionDataJson.get(UtilConstants.DEFINITION_KEY));
				iDefineMasterEntity.setRelatedKeys((String)inputDefinitionDataJson.get(UtilConstants.RELATED_KEYS_KEY));
				result = iDefineBO.addDefinition(iDefineMasterEntity);
			}catch(Exception e) {
				throw new IDefineException(ExceptionConstants.INVALID_INPUT_EXCEPTION);
			}
		}
		return result;
	}
	
	@PostMapping("modifyDefinition")
	public @ResponseBody boolean modifyDefinition(HttpEntity<String> httpEntity) throws IDefineException {
		boolean result = false;
		String inputDefinitionData = httpEntity.getBody();
		if(!UtilMethods.isNullOrEmpty(inputDefinitionData)) {
			try {
				JSONObject inputDefinitionDataJson = new JSONObject(inputDefinitionData);
				iDefineMasterEntity.setKeyword((String)inputDefinitionDataJson.get(UtilConstants.KEYWORD_KEY));
				iDefineMasterEntity.setDefinition((String)inputDefinitionDataJson.get(UtilConstants.DEFINITION_KEY));
				iDefineMasterEntity.setRelatedKeys((String)inputDefinitionDataJson.get(UtilConstants.RELATED_KEYS_KEY));
				result = iDefineBO.modifyDefinition(iDefineMasterEntity);
			}catch(Exception e) {
				throw new IDefineException(ExceptionConstants.INVALID_INPUT_EXCEPTION);
			}
		}
		return result;
	}

}
