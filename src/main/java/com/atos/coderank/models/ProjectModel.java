package com.atos.coderank.models;

import java.util.Date;
import java.util.List;

public class ProjectModel {

	private String projectId;
	private String key;
	private String name;
	private String url;
	private byte[] logo;
	private boolean locked;
	private Date createdDate;
	private Date lockedDate;
	private GroupModel group;
	private List<ProjectMetricsModel> metrics;
	private List<ProjectReportsModel> reports;
	private List<BadgesModel> badges;
	private RankingModel ranking;

	public ProjectModel() {

	}

	public String getProjectId() {
		return projectId;
	}

	public void setProjectId(String projectId) {
		this.projectId = projectId;
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public byte[] getLogo() {
		return logo;
	}

	public void setLogo(byte[] logo) {
		this.logo = logo;
	}

	public boolean isLocked() {
		return locked;
	}

	public void setLocked(boolean locked) {
		this.locked = locked;
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

	public GroupModel getGroup() {
		return group;
	}

	public void setGroup(GroupModel group) {
		this.group = group;
	}

	public List<ProjectMetricsModel> getMetrics() {
		return metrics;
	}

	public void setMetrics(List<ProjectMetricsModel> metrics) {
		this.metrics = metrics;
	}

	public List<ProjectReportsModel> getReports() {
		return reports;
	}

	public void setReports(List<ProjectReportsModel> reports) {
		this.reports = reports;
	}

	public List<BadgesModel> getBadges() {
		return badges;
	}

	public void setBadges(List<BadgesModel> badges) {
		this.badges = badges;
	}

	public RankingModel getRanking() {
		return ranking;
	}

	public void setRanking(RankingModel ranking) {
		this.ranking = ranking;
	}

}
