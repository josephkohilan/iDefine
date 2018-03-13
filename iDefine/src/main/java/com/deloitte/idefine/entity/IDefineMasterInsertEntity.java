package com.deloitte.idefine.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.stereotype.Component;

@Component
@Entity
@Table(name = "idefine_master_table")
public class IDefineMasterInsertEntity {
	
	@Column(name = "keyword")
	private String keyword;
	
	@Id
	@Column(name = "definition")
	private String definition;

	@Column(name = "approval_status")
	private boolean approvalStatus;
	
	@Column(name = "up_votes")
	private int upVotes;
	
	@Column(name = "down_votes")
	private int downVotes;

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
	
	public boolean getApprovalStatus() {
		return approvalStatus;
	}

	public void setApprovalStatus(boolean approvalStatus) {
		this.approvalStatus = approvalStatus;
	}

	public int getUpVotes() {
		return upVotes;
	}

	public void setUpVotes(int upVotes) {
		this.upVotes = upVotes;
	}

	public int getDownVotes() {
		return downVotes;
	}

	public void setDownVotes(int downVotes) {
		this.downVotes = downVotes;
	}
	
}