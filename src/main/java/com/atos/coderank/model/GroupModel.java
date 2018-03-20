package com.atos.coderank.model;

import java.util.Set;

public class GroupModel {

	private int id;
	private String name;
	private String description;
	private Set<UserModel> user;

	public GroupModel() {

	}

	public GroupModel(int id, String name, String description, Set<UserModel> user) {
		super();
		this.id = id;
		this.name = name;
		this.description = description;
		this.user = user;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Set<UserModel> getUser() {
		return user;
	}

	public void setUser(Set<UserModel> user) {
		this.user = user;
	}

}
