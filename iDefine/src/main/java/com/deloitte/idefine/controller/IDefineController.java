package com.deloitte.idefine.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.deloitte.idefine.bo.IDefineBO;
import com.deloitte.idefine.dataobject.SearchDefinitionResultDO;
import com.deloitte.idefine.utility.UtilMethods;

@Controller
public class IDefineController {
	
	@Autowired
	private SearchDefinitionResultDO searchDefinitionResultDO;
	
	@Autowired
	private IDefineBO iDefineBO;

	@RequestMapping(value="searchdefinition",method = RequestMethod.POST)
	public @ResponseBody SearchDefinitionResultDO searchDefinition(HttpEntity<String> httpEntity){
		String inputKeyword = httpEntity.getBody();
		if(!UtilMethods.isNullOrEmpty(inputKeyword)) {
			searchDefinitionResultDO.setKeyword(inputKeyword);
			iDefineBO.searchDefinition(searchDefinitionResultDO);
		}else {
			//Throws user defined exception
		}
		return searchDefinitionResultDO;
	}

}
