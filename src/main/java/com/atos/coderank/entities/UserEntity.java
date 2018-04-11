package com.atos.coderank.entities;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "USERS")
public class UserEntity implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "DAS")
	private String das;

	@Column(name = "NAME")
	private String name;

	@Column(name = "EMAIL")
	private String email;

	@JsonIgnore
	@Column(name = "PASSWORD", nullable = false, length = 60)
	private String password;

	@Column(name = "PHOTO")
	private Byte[] photo;

	@Column(name = "CREATED_DATE")
	private Date createdDate;

	@Column(name = "LOCKED_DATE")
	private Date lockedDate;

	@Column(name = "ENABLED")
	private Boolean enabled;

	@Column(name = "LOCKED")
	private Boolean locked;

	@JsonIgnore
	@ManyToMany(mappedBy = "users")
	private List<GroupEntity> groups;

	@OneToOne
	private RoleEntity role;

	@ManyToMany(mappedBy = "users")
	private List<BadgesEntity> badges;

	@Transient
	private List<Map<String, String>> serialziedGroup;

	public UserEntity() {
	}

	public UserEntity(String das, String name, String surname, String email, String password, Byte[] photo,
			Date createdDate, Date lockedDate, Boolean enabled, Boolean locked, RoleEntity role) {
		this.das = das;
		this.name = name;
		this.email = email;
		this.password = password;
		this.photo = photo;
		this.createdDate = createdDate;
		this.lockedDate = lockedDate;
		this.enabled = enabled;
		this.locked = locked;
		this.role = role;
	}

	public void addGroup(GroupEntity group) {
		if (!groups.contains(group)) {
			groups.add(group);
			group.addUser(this);
		}
	}

	public void addBadge(BadgesEntity be) {
		if (!badges.contains(be))
			badges.add(be);
	}

	public void removeBadge(BadgesEntity be) {
		if (badges.contains(be))
			badges.remove(be);
	}

	@Override
	public String toString() {
		return "UserEntity [das=" + das + ", name=" + name + ", email=" + email + ", password=" + password + ", photo="
				+ Arrays.toString(photo) + ", createdDate=" + createdDate + ", lockedDate=" + lockedDate + ", enabled="
				+ enabled + ", locked=" + locked + ", groups=" + groups + ", role=" + role + ", badges=" + badges + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((badges == null) ? 0 : badges.hashCode());
		result = prime * result + ((createdDate == null) ? 0 : createdDate.hashCode());
		result = prime * result + ((das == null) ? 0 : das.hashCode());
		result = prime * result + ((email == null) ? 0 : email.hashCode());
		result = prime * result + (enabled ? 1231 : 1237);
		result = prime * result + ((groups == null) ? 0 : groups.hashCode());
		result = prime * result + (locked ? 1231 : 1237);
		result = prime * result + ((lockedDate == null) ? 0 : lockedDate.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((password == null) ? 0 : password.hashCode());
		result = prime * result + Arrays.hashCode(photo);
		result = prime * result + ((role == null) ? 0 : role.hashCode());
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
		UserEntity other = (UserEntity) obj;
		if (badges == null) {
			if (other.badges != null)
				return false;
		} else if (!badges.equals(other.badges))
			return false;
		if (createdDate == null) {
			if (other.createdDate != null)
				return false;
		} else if (!createdDate.equals(other.createdDate))
			return false;
		if (das == null) {
			if (other.das != null)
				return false;
		} else if (!das.equals(other.das))
			return false;
		if (email == null) {
			if (other.email != null)
				return false;
		} else if (!email.equals(other.email))
			return false;
		if (enabled != other.enabled)
			return false;
		if (groups == null) {
			if (other.groups != null)
				return false;
		} else if (!groups.equals(other.groups))
			return false;
		if (locked != other.locked)
			return false;
		if (lockedDate == null) {
			if (other.lockedDate != null)
				return false;
		} else if (!lockedDate.equals(other.lockedDate))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (password == null) {
			if (other.password != null)
				return false;
		} else if (!password.equals(other.password))
			return false;
		if (!Arrays.equals(photo, other.photo))
			return false;
		if (role == null) {
			if (other.role != null)
				return false;
		} else if (!role.equals(other.role))
			return false;
		return true;
	}

	public String getDas() {
		return das;
	}

	public void setDas(String das) {
		this.das = das;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Byte[] getPhoto() {
		return photo;
	}

	public void setPhoto(Byte[] photo) {
		this.photo = photo;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public Date getLockedDate() {
		return lockedDate;
	}

	public void setLockedDate(Date lockedDate) {
		this.lockedDate = lockedDate;
	}

	public Boolean isEnabled() {
		return enabled;
	}

	public void setEnabled(Boolean enabled) {
		this.enabled = enabled;
	}

	public Boolean isLocked() {
		return locked;
	}

	public void setLocked(Boolean locked) {
		this.locked = locked;
	}

	public List<GroupEntity> getGroups() {
		return groups;
	}

	public void setGroups(List<GroupEntity> groups) {
		this.groups = groups;
	}

	public RoleEntity getRole() {
		return role;
	}

	public void setRole(RoleEntity role) {
		this.role = role;
	}

	public List<BadgesEntity> getBadges() {
		return badges;
	}

	public void setBadges(List<BadgesEntity> badges) {
		this.badges = badges;
	}

	public List<Map<String, String>> getSerialziedGroup() {
		return serialziedGroup;
	}

	public void setSerialziedGroup(List<Map<String, String>> serialziedGroup) {
		this.serialziedGroup = serialziedGroup;
	}

}
