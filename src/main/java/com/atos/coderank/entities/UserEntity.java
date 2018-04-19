package com.atos.coderank.entities;

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
public class UserEntity {

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
	private byte[] photo;

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

	@JsonIgnore
	@ManyToMany(mappedBy = "users")
	private List<BadgesEntity> badges;

	@Transient
	private List<Map<String, String>> serializedGroups;

	@Transient
	private List<Map<String, Object>> serializedBadges;

	public UserEntity() {
		
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

	public byte[] getPhoto() {
		return photo;
	}

	public void setPhoto(byte[] photo) {
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

	public List<Map<String, String>> getSerializedGroups() {
		return serializedGroups;
	}

	public void setSerializedGroups(List<Map<String, String>> serialziedGroup) {
		this.serializedGroups = serialziedGroup;
	}

	public List<Map<String, Object>> getSerializedBadges() {
		return serializedBadges;
	}

	public void setSerializedBadges(List<Map<String, Object>> serializedBadges) {
		this.serializedBadges = serializedBadges;
	}

}
