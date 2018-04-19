package com.atos.coderank.entities;

import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "PROJECTS")
public class ProjectEntity {

	@Id
	@Column(name = "PROJECT_ID", nullable = false)
	private String projectId;

	@Column(name = "KEY")
	private String key;

	@Column(name = "NAME")
	private String name;

	@Column(name = "URL")
	private String url;

	@Column(name = "LOGO")
	private byte[] logo;

	@Column(name = "LOCKED")
	private Boolean locked;

	@Column(name = "CREATED_DATE")
	private Date createdDate;

	@Column(name = "LOCKED_DATE")
	private Date lockedDate;

	@JsonIgnore
	@OneToOne(mappedBy = "project", fetch = FetchType.LAZY)
	private GroupEntity group;

	@OneToMany(mappedBy = "project", cascade = CascadeType.ALL)
	private List<ProjectMetricsEntity> metrics;

	@OneToMany(mappedBy = "project", cascade = CascadeType.ALL)
	private List<ProjectReportsEntity> reports;

	@JsonIgnore
	@ManyToMany(mappedBy = "projects")
	private List<BadgesEntity> badges;

	@OneToOne(mappedBy = "project")
	private RankingEntity ranking;

	@Transient
	private Map<String, String> serializedGroup;

	@Transient
	private List<Map<String, Object>> serializedBadges;

	public ProjectEntity() {

	}

	public void addMetrics(ProjectMetricsEntity pme) {
		if (!metrics.contains(pme)) {
			metrics.add(pme);
			pme.setProject(this);
		}
	}

	public void addReports(ProjectReportsEntity pre) {
		if (!reports.contains(pre)) {
			reports.add(pre);
			pre.setProject(this);
		}

	}

	public void addBadge(BadgesEntity be) {
		if (!badges.contains(be)) {
			badges.add(be);
		}
	}

	public void removeBadge(BadgesEntity be) {
		if (badges.contains(be)) {
			badges.remove(be);
		}
	}

	@Override
	public String toString() {
		return "ProjectEntity [projectId=" + projectId + ", key=" + key + ", name=" + name + ", url=" + url + ", logo="
				+ Arrays.toString(logo) + ", locked=" + locked + ", createddate=" + createdDate + ", lockeddate="
				+ lockedDate + "]";
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

	public Boolean isLocked() {
		return locked;
	}

	public void setLocked(Boolean locked) {
		this.locked = locked;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createddate) {
		this.createdDate = createddate;
	}

	public Date getLockedDate() {
		return lockedDate;
	}

	public void setLockedDate(Date lockeddate) {
		this.lockedDate = lockeddate;
	}

	public GroupEntity getGroup() {
		return group;
	}

	public void setGroup(GroupEntity group) {
		this.group = group;
	}

	public List<ProjectMetricsEntity> getMetrics() {
		return metrics;
	}

	public void setMetrics(List<ProjectMetricsEntity> metrics) {
		this.metrics = metrics;
	}

	public List<ProjectReportsEntity> getReports() {
		return reports;
	}

	public void setReports(List<ProjectReportsEntity> reports) {
		this.reports = reports;
	}

	public List<BadgesEntity> getBadges() {
		return badges;
	}

	public void setBadges(List<BadgesEntity> badges) {
		this.badges = badges;
	}

	public RankingEntity getRanking() {
		return ranking;
	}

	public void setRanking(RankingEntity ranking) {
		this.ranking = ranking;
	}

	public Map<String, String> getSerializedGroup() {
		return serializedGroup;
	}

	public void setSerializedGroup(Map<String, String> serializedGroup) {
		this.serializedGroup = serializedGroup;
	}

	public List<Map<String, Object>> getSerializedBadges() {
		return serializedBadges;
	}

	public void setSerializedBadges(List<Map<String, Object>> serializedBadges) {
		this.serializedBadges = serializedBadges;
	}

}
