package com.atos.coderank.models;

import java.util.Date;
import java.util.List;

public class UserModel {

	private String das;
	private String name;
	private String email;
	private String password;
	private Byte[] photo;
	private Date createdDate;
	private Date lockedDate;
	private Boolean enabled;
	private Boolean locked;
	private List<GroupModel> groups;
	private RoleModel role;
	private List<BadgesModel> badges;

	public UserModel() {

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

	public List<GroupModel> getGroups() {
		return groups;
	}

	public void setGroups(List<GroupModel> groups) {
		this.groups = groups;
	}

	public RoleModel getRole() {
		return role;
	}

	public void setRole(RoleModel role) {
		this.role = role;
	}

	public List<BadgesModel> getBadges() {
		return badges;
	}

	public void setBadges(List<BadgesModel> badges) {
		this.badges = badges;
	}

	public Boolean getEnabled() {
		return enabled;
	}

	public void setEnabled(Boolean enabled) {
		this.enabled = enabled;
	}

	public Boolean getLocked() {
		return locked;
	}

	public void setLocked(Boolean locked) {
		this.locked = locked;
	}

	@Override
	public String toString() {
		return "UserModel [das=" + das + ", name=" + name + ", email=" + email + ", createdDate=" + createdDate
				+ ", lockedDate=" + lockedDate + ", enabled=" + enabled + ", locked=" + locked + ", role=" + role + "]";
	}

	
}
