package com.atos.coderank.entity;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name = "users")
public class User {

	@Id
	@Column(name = "das")
	private String das;

	@Column(name = "password")
	private String password;

	@Column(name = "email")
	private String email;

	@Column(name = "name")
	private String name;

	@Column(name = "lastname")
	private String lastname;

	@ManyToMany(mappedBy = "user")
	@Column(name = "group")
	private Set<Group> group;

	public User() {
	}

	public User(String das, String password, String email, String name, String lastname, Set<Group> group) {
		super();
		this.das = das;
		this.password = password;
		this.name = name;
		this.lastname = lastname;
		this.email = email;
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

	public Set<Group> getGroup() {
		return group;
	}

	public void setGroup(Set<Group> group) {
		this.group = group;
	}

}
