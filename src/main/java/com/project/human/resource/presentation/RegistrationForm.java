package com.project.human.resource.presentation;

import javax.validation.constraints.*;

public class RegistrationForm {

    @NotBlank(message = "Name is required!")
    private String name;

    @NotBlank(message = "Password is required!")
    private String password;

    @NotBlank(message = "Role is required!")
    private String role;

    public RegistrationForm(String name, String role, String password) {
		this.name =name;
		this.password = password;
		this.role=role;
	}
    
    public RegistrationForm() {	}

	public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
