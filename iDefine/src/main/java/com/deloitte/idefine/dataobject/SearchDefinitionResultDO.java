package com.deloitte.idefine.dataobject;

import org.springframework.stereotype.Component;

@Component
public class SearchDefinitionResultDO {
	
	private String keyword;
	
	private String definition;
	
	private String relatedSearch;
	
	private String errorMessage;

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

	public String getRelatedSearch() {
		return relatedSearch;
	}

	public void setRelatedSearch(String relatedSearch) {
		this.relatedSearch = relatedSearch;
	}

	public String getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}
	
}
