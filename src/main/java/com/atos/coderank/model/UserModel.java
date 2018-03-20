package com.atos.coderank.model;

import java.util.Set;

public class UserModel {

	private String das;
	private String password;
	private String email;
	private String name;
	private String lastname;
	private Set<GroupModel> group;

	public UserModel() {
		
	}
	
	public UserModel(String das, String password, String email, String name, String lastname, Set<GroupModel> group) {
		super();
		this.das = das;
		this.password = password;
		this.email = email;
		this.name = name;
		this.lastname = lastname;
		this.group = group;
	}

	public String getDas() {
		return das;
	}

	public void setDas(String das) {
		this.das = das;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public Set<GroupModel> getGroup() {
		return group;
	}

	public void setGroup(Set<GroupModel> group) {
		this.group = group;
	}
}
