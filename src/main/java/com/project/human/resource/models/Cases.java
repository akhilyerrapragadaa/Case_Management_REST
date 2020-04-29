package com.project.human.resource.models;

import java.util.Date;

import javax.validation.constraints.Size;

import org.springframework.data.annotation.CreatedDate;
import javax.persistence.*;


@Entity
@Table(name="CASES")
public class Cases {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private long id;

	@Column(name = "REQUESTED_BY")
	private String requestedBy;

	@Column(name = "TYPE")
	private String type;

	@Column(name = "STATE")
	private String state;

	@Column(name = "ASSIGNED_TO")
	private String assignedTo;

	@Column(name = "ISSUE")
	private String issue;

	@Column(name = "ACCESSORIES")
	private String accessories;

	@Column(name = "COMMENT")
	private String comment;

	@Column(name = "OFFICER_COMMENT")
	private String officerComment;

	@Temporal(TemporalType.TIMESTAMP)
	@CreatedDate
	@Column(name = "CREATED_AT")
	private Date createdAt= new Date();

	public Cases() {
	}

	public Cases(String requestedBy, String type, String state, String assignedTo, String issue, String accessories, String comment) {
		this.requestedBy = requestedBy;
		this.type = type;
		this.state = state;
		this.assignedTo = assignedTo;
		this.issue = issue;
		this.accessories = accessories;
		this.comment = comment;
	}

	public String getStringId() {
		return Long.toString(id);
	}

	public String getRequestedBy() {
		return requestedBy;
	}

	public String getType() {
		return type;
	}

	public String getState() {
		return state;
	}

	public String getAssignedTo() {
		return assignedTo;
	}

	public String getIssue() {
		return issue;
	}

	public String getAccessories() {
		return accessories;
	}

	public String getComment() {
		return comment;
	}

	public String getOfficerComment() {
		return officerComment;
	}

	public void setState(String state) {
		this.state = state;
	}

	public void setAssignedTo(String assignedTo) {
		this.assignedTo = assignedTo;
	}

	public void setOfficerComment(String officerComment) {
		this.officerComment = officerComment;
	}
}
