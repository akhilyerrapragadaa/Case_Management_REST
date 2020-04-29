package com.project.human.resource.presentation;

import javax.validation.constraints.NotBlank;

public class OfficerCommentForm {

    @NotBlank(message = "Please add a comment!")
    private String comment;

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
}
