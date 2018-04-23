package com.atos.coderank.entities;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "BADGES")
public class BadgesEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SQ_BADGES")
	@SequenceGenerator(name = "SQ_BADGES", sequenceName = "SQ_BADGES", allocationSize = 1)
	@Column(name = "BADGE_ID")
	private Long badgeId;

	@Column(name = "NAME")
	private String name;

	@Column(name = "DOMAIN")
	private String domain;

	@Column(name = "BADGE_CLASS")
	private String badgeClass;

	@Column(name = "IMAGE")
	private byte[] image;

	@ManyToMany
	@JoinTable(name = "BADGE_USER", joinColumns = { @JoinColumn(name = "BADGE_ID") }, inverseJoinColumns = {
			@JoinColumn(name = "USER_DAS") })
	private List<UserEntity> users;

	@ManyToMany
	@JoinTable(name = "BADGE_PROJECT", joinColumns = { @JoinColumn(name = "BADGE_ID") }, inverseJoinColumns = {
			@JoinColumn(name = "PROJECT_ID") })
	private List<ProjectEntity> projects;

	public BadgesEntity(String name, byte[] image) {
		this.name = name;
		this.setImage(image);
	}

	public BadgesEntity() {

	}

	public void addProject(ProjectEntity pe) {
		if (!projects.contains(pe)) {
			projects.add(pe);
		}
	}

	public void removeProject(ProjectEntity pe) {
		if (projects.contains(pe)) {
			projects.remove(pe);
		}
	}

	public void addUser(UserEntity ue) {
		if (!users.contains(ue)) {
			users.add(ue);
			ue.addBadge(this);
		}
	}

	public void removeUser(UserEntity ue) {
		if (users.contains(ue)) {
			users.remove(ue);
		}
	}

	@Override
	public String toString() {
		return "BadgesEntity [badgeId=" + badgeId + ", name=" + name + ", domain=" + domain + ", badgeClass="
				+ badgeClass + "]";
	}

	public Long getBadgeId() {
		return badgeId;
	}

	public void setBadgeId(Long badgeId) {
		this.badgeId = badgeId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<UserEntity> getUsers() {
		return users;
	}

	public void setUsers(List<UserEntity> users) {
		this.users = users;
	}

	public byte[] getImage() {
		return image;
	}

	public void setImage(byte[] image) {
		this.image = image;
	}

	public String getDomain() {
		return domain;
	}

	public void setDomain(String domain) {
		this.domain = domain;
	}

	public String getBadgeClass() {
		return badgeClass;
	}

	public void setBadgeClass(String badgeClass) {
		this.badgeClass = badgeClass;
	}

	public List<ProjectEntity> getProjects() {
		return projects;
	}

	public void setProjects(List<ProjectEntity> projects) {
		this.projects = projects;
	}

}
