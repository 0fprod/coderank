package com.atos.coderank.models;

public class RoleModel {

	private Long roleId;
	private String name;

	public RoleModel() {

	}
	
	public RoleModel(String name) {
		this.name = name;
	}

	public Long getRoleId() {
		return roleId;
	}

	public void setRoleId(Long roleId) {
		this.roleId = roleId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "RoleModel [roleId=" + roleId + ", name=" + name + "]";
	}



}
