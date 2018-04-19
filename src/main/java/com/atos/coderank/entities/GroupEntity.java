package com.atos.coderank.entities;

import java.util.List;
import java.util.Map;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "GROUPS")
public class GroupEntity  {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SQ_GROUP")
	@SequenceGenerator(name = "SQ_GROUP", sequenceName = "SQ_GROUP", allocationSize = 1)
	@Column(name = "GROUP_ID")
	private Long groupId;

	@Column(name = "NAME")
	private String name;

	@Column(name = "DESCRIPTION")
	private String description;

	@ManyToMany
	@JoinTable(name = "GROUPS_USERS", joinColumns = { @JoinColumn(name = "GROUP_ID") }, inverseJoinColumns = {
			@JoinColumn(name = "USER_DAS") })
	private List<UserEntity> users;

	@JsonIgnore
	@OneToOne(fetch = FetchType.LAZY, orphanRemoval = false)
	@JoinTable(name = "GROUP_PROJECTS", joinColumns = { @JoinColumn(name = "GROUP_ID") }, inverseJoinColumns = {
			@JoinColumn(name = "PROJECT_ID") })
	private ProjectEntity project;

	@Transient
	private Map<String, String> serializedProject;

	public GroupEntity(String name, String description) {
		this.name = name;
		this.description = description;
	}

	public GroupEntity() {

	}

	public void addUser(UserEntity user) {
		if (!users.contains(user))
			users.add(user);

	}

	public void removeUser(UserEntity user) {
		if (users.contains(user))
			users.remove(user);
	}

	@Override
	public String toString() {
		return "GroupEntity [groupId=" + groupId + ", name=" + name + ", description=" + description + ", users="
				+ users + ", project=" + project + "]";
	}

	public Long getGroupId() {
		return groupId;
	}

	public void setGroupId(Long groupId) {
		this.groupId = groupId;
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

	public List<UserEntity> getUsers() {
		return users;
	}

	public void setUsers(List<UserEntity> users) {
		this.users = users;
	}

	public ProjectEntity getProject() {
		return project;
	}

	public void setProject(ProjectEntity project) {
		this.project = project;
	}

	public Map<String, String> getSerializedProject() {
		return serializedProject;
	}

	public void setSerializedProject(Map<String, String> serializedProject) {
		this.serializedProject = serializedProject;
	}

}
