package com.project.human.resource.presentation;

import com.project.human.resource.models.Cases;

import javax.validation.constraints.NotBlank;

public class CaseRequestForm {

    
	private String requestedBy;

    private String type;

    @NotBlank(message = "You must specify the accessories!")
    private String accessories;

    @NotBlank(message = "Select the issue you want to submit!")
    private String issue;

    @NotBlank(message = "Please add a comment!")
    private String comment;

    public CaseRequestForm(String requestedBy, String type) {
        this.requestedBy = requestedBy;
        this.type = type;
    }
  
    public CaseRequestForm() {
		super();
		reset();
	}
    
    public void reset() {
         this.accessories = "";
 		this.issue = "";
 		this.comment = "";
    }

	public CaseRequestForm(String requestedBy, String type,
			 String accessories,
			 String issue,
			String comment) {
		this.requestedBy = requestedBy;
		this.type = type;
		this.accessories = accessories;
		this.issue = issue;
		this.comment = comment;
	}



	public Cases getUserNewCase() {
        return new Cases(requestedBy, type, "in_progress", "officer", issue, "", comment);
    }

    public Cases getOfficeNewCase() {
        return new Cases(requestedBy, type, "in_progress", "manager", "", accessories, comment);
    }

    public String getRequestedBy() {
        return requestedBy;
    }

    public void setRequestedBy(String requestedBy) {
        this.requestedBy = requestedBy;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getAccessories() {
        return accessories;
    }

    public void setAccessories(String accessories) {
        this.accessories = accessories;
    }

    public String getIssue() {
        return issue;
    }

    public void setIssue(String issue) {
        this.issue = issue;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
}
