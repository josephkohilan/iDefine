package com.deloitte.idefine.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown=true)
public class DefinitionDto {
	
	private String definitionId;
	
	private String keyword;
	
	private String definition;
	
	private Integer upVote;
	
	private Integer downVote;

	public String getDefinitionId() {
		return definitionId;
	}

	public void setDefinitionId(String definitionId) {
		this.definitionId = definitionId;
	}

	public String getKeyword() {
		return keyword;
	}

	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}

	public String getDefinition() {
		return definition;
	}

	public void setDefinition(String definition) {
		this.definition = definition;
	}

	public Integer getUpVote() {
		return upVote;
	}

	public void setUpVote(Integer upVote) {
		this.upVote = upVote;
	}

	public Integer getDownVote() {
		return downVote;
	}

	public void setDownVote(Integer downVote) {
		this.downVote = downVote;
	}
	
}
