package com.deloitte.ievolve.controller;

import java.sql.Date;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.deloitte.ievolve.dto.DefinitionDto;
import com.deloitte.ievolve.entity.DefinitionEntity;
import com.deloitte.ievolve.exception.IDefineException;
import com.deloitte.ievolve.service.DefinitionService;
import com.deloitte.ievolve.utility.UtilConstants;

@Controller
public class DefinitionController {

	@Autowired
	private DefinitionEntity iDefineMasterEntity;

	@Autowired
	private DefinitionService iDefineBO;
	
	@GetMapping("/")
	@ResponseBody
	public String getHome() {
		return "iEvolve is high and healthy! click <a href='/iEvolve'>here</a> to launch!";
	}

	@GetMapping("/iEvolve")
	public ModelAndView homePage() {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("index");
		return modelAndView;
	}

	@GetMapping("keyWords")
	public @ResponseBody Set<String> getKeyWords() {
		return iDefineBO.getKeyWords();
	}

	@GetMapping("definition/{keyword}")
	public @ResponseBody List<DefinitionEntity> getDefinition(@PathVariable String keyword)
			throws IDefineException {
		String category = null;
		if(keyword.contains(":")) {
			category = keyword.substring(0, keyword.indexOf(":")).trim();
			keyword = keyword.substring(keyword.indexOf(":")+1).trim();
		}
		return iDefineBO.getDefinition(keyword, category);
	}

	@PostMapping("definition")
	public @ResponseBody boolean addDefinition(@RequestBody DefinitionDto definitionDto) throws IDefineException {
		String keyword = definitionDto.getKeyword().toLowerCase().trim().replaceAll("\"", "\\\"");
		String category = null;
		if(keyword.contains(":")) {
			category = keyword.substring(0, keyword.indexOf(":")).trim();
			category = category.equals("")?null:category;
			keyword = keyword.substring(keyword.indexOf(":")+1).trim();
		}
		iDefineMasterEntity.setDefinitionId(null);
		iDefineMasterEntity.setKeyword(keyword);
		iDefineMasterEntity.setCategory(category);
		iDefineMasterEntity.setDefinition(definitionDto.getDefinition().trim().replaceAll("\"", "\\\""));
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
	
	@DeleteMapping("definition/{definitionId}")
	public @ResponseBody boolean deleteDefinition(@PathVariable String definitionId) {
		return iDefineBO.deleteDefinition(definitionId);
	}
	
	@PutMapping("definition")
	public @ResponseBody boolean updateDefinition(@RequestBody DefinitionDto definitionDto) {
		iDefineMasterEntity.setDefinitionId(definitionDto.getDefinitionId());
		iDefineMasterEntity.setKeyword(null);
		iDefineMasterEntity.setDefinition(definitionDto.getDefinition());
		iDefineMasterEntity.setApprovalStatus(false);
		iDefineMasterEntity.setUpVotes(UtilConstants.VOTE_COUNT_ZERO);
		iDefineMasterEntity.setDownVotes(UtilConstants.VOTE_COUNT_ZERO);
		iDefineMasterEntity.setCreatedDate(new Date(System.currentTimeMillis()));
		return iDefineBO.updateDefinition(iDefineMasterEntity);
	}

}
