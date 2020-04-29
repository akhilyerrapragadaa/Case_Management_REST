package com.project.human.resource.models;

import javax.persistence.*;
import javax.validation.constraints.Size;

@Entity
@Table(name="User")
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private long id;

	@Size(min = 5, max = 10, message= "Length should be between 5 and 10")
	private String name;

	private String role;

	private String password;

	public User() {	}

	public User(@Size(min = 5, max = 10, message = "Length should be between 5 and 10") String name, String role, String password) {
		this.name = name;
		this.role = role;
		this.password = password;
	}

	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}

	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
}
