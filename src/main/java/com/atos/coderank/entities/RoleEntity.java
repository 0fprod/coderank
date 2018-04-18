package com.atos.coderank.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "ROLES")
public class RoleEntity implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SQ_ROLES")
	@SequenceGenerator(name = "SQ_ROLES", sequenceName = "SQ_ROLES", allocationSize = 1)
	@Column(name = "ROLE_ID")
	private Long roleId;

	@Column(name = "name")
	private String name;

	public RoleEntity(String name) {
		this.name = name;
	}

	public RoleEntity() {

	}

	@Override
	public String toString() {
		return "RoleEntity [roleId=" + roleId + ", name=" + name + "]";
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

}
