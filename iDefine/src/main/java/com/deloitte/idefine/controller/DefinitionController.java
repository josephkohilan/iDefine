package com.deloitte.idefine.controller;

import java.sql.Date;
import java.util.List;
import java.util.Set;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.deloitte.idefine.dto.DefinitionDto;
import com.deloitte.idefine.entity.DefinitionEntity;
import com.deloitte.idefine.exception.IDefineException;
import com.deloitte.idefine.service.DefinitionService;
import com.deloitte.idefine.utility.ExceptionConstants;
import com.deloitte.idefine.utility.UtilConstants;
import com.deloitte.idefine.utility.UtilMethods;

@Controller
public class DefinitionController {

	@Autowired
	private DefinitionEntity iDefineMasterEntity;

	@Autowired
	private DefinitionService iDefineBO;

	@GetMapping("iEvolve")
	public ModelAndView homePage() {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("index");
		return modelAndView;
	}

	@GetMapping("iEvolve/keyWords")
	public @ResponseBody Set<String> getKeyWords() {
		return iDefineBO.getKeyWords();
	}

	@GetMapping("iEvolve/definition/{inputKeyword}")
	public @ResponseBody List<DefinitionEntity> getDefinition(@PathVariable String inputKeyword)
			throws IDefineException {
		return iDefineBO.getDefinition(inputKeyword);
	}

	@PostMapping("definition")
	public @ResponseBody boolean addDefinition(@RequestBody DefinitionDto definitionDto) throws IDefineException {
		iDefineMasterEntity.setKeyword(definitionDto.getKeyword().toLowerCase().trim());
		iDefineMasterEntity.setDefinition(definitionDto.getDefinition().trim());
		iDefineMasterEntity.setApprovalStatus(false);
		iDefineMasterEntity.setUpVotes(UtilConstants.VOTE_COUNT_ZERO);
		iDefineMasterEntity.setDownVotes(UtilConstants.VOTE_COUNT_ZERO);
		iDefineMasterEntity.setCreatedDate(new Date(System.currentTimeMillis()));
		return iDefineBO.addDefinition(iDefineMasterEntity);
	}

	@PutMapping("vote")
	public @ResponseBody boolean updateVote(@RequestBody DefinitionDto definitionDto) throws IDefineException {
		return iDefineBO.updateVote(definitionDto.getDefinitionId(), definitionDto.getUpVote(),
				definitionDto.getDownVote());
	}

}
