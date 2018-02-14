package com.deloitte.idefine.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.stereotype.Component;

@Component
@Entity
@Table(name = "idefine_master_table")
public class IDefineMasterEntity {
	
	@Id
	@Column(name = "keyword")
	private String keyword;
	
	@Column(name = "definition")
	private String definition;

	@Column(name = "related_keys")
	private String relatedKeys;

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

	public String getRelatedKeys() {
		return relatedKeys;
	}

	public void setRelatedKeys(String relatedKeys) {
		this.relatedKeys = relatedKeys;
	}

}