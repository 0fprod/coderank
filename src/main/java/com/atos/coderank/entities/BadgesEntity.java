package com.atos.coderank.entities;

import java.util.Arrays;
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
			pe.addBadge(this);
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
		return "BadgesEntity [badgeId=" + badgeId + ", name=" + name + ", user=" + users + ", project=" + projects
				+ "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((badgeId == null) ? 0 : badgeId.hashCode());
		result = prime * result + Arrays.hashCode(image);
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((projects == null) ? 0 : projects.hashCode());
		result = prime * result + ((users == null) ? 0 : users.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		BadgesEntity other = (BadgesEntity) obj;
		if (badgeId == null) {
			if (other.badgeId != null)
				return false;
		} else if (!badgeId.equals(other.badgeId))
			return false;
		if (!Arrays.equals(image, other.image))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (projects == null) {
			if (other.projects != null)
				return false;
		} else if (!projects.equals(other.projects))
			return false;
		if (users == null) {
			if (other.users != null)
				return false;
		} else if (!users.equals(other.users))
			return false;
		return true;
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

	public List<ProjectEntity> getProject() {
		return projects;
	}

	public void setProject(List<ProjectEntity> projects) {
		this.projects = projects;
	}

	public byte[] getImage() {
		return image;
	}

	public void setImage(byte[] image) {
		this.image = image;
	}

}
