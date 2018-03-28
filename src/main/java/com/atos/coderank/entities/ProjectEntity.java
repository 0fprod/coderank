package com.atos.coderank.entities;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "PROJECTS")
public class ProjectEntity {

	@Id
	@Column(name = "PROJECT_ID")
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
	private boolean locked;

	@Column(name = "CREATED_DATE")
	private Date createdDate;

	@Column(name = "LOCKED_DATE")
	private Date lockedDate;

	@OneToOne(mappedBy = "project", fetch = FetchType.LAZY)
	private GroupEntity group;

	@OneToMany(mappedBy = "project", cascade = CascadeType.ALL)
	private List<ProjectMetricsEntity> metrics;

	@OneToMany(mappedBy = "project", cascade = CascadeType.ALL)
	private List<ProjectReportsEntity> reports;

	@ManyToMany(mappedBy = "projects")
	private List<BadgesEntity> badges;

	@OneToOne(mappedBy = "project")
	private RankingEntity ranking;

	public ProjectEntity() {

	}

	public ProjectEntity(String projectId, String key, String name, String url, byte[] logo, boolean locked,
			Date createddate, Date lockeddate) {
		super();
		this.projectId = projectId;
		this.key = key;
		this.name = name;
		this.url = url;
		this.logo = logo;
		this.locked = locked;
		this.createdDate = createddate;
		this.lockedDate = lockeddate;
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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((badges == null) ? 0 : badges.hashCode());
		result = prime * result + ((createdDate == null) ? 0 : createdDate.hashCode());
		result = prime * result + ((group == null) ? 0 : group.hashCode());
		result = prime * result + ((key == null) ? 0 : key.hashCode());
		result = prime * result + (locked ? 1231 : 1237);
		result = prime * result + ((lockedDate == null) ? 0 : lockedDate.hashCode());
		result = prime * result + Arrays.hashCode(logo);
		result = prime * result + ((metrics == null) ? 0 : metrics.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((projectId == null) ? 0 : projectId.hashCode());
		result = prime * result + ((ranking == null) ? 0 : ranking.hashCode());
		result = prime * result + ((reports == null) ? 0 : reports.hashCode());
		result = prime * result + ((url == null) ? 0 : url.hashCode());
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
		ProjectEntity other = (ProjectEntity) obj;
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
		if (group == null) {
			if (other.group != null)
				return false;
		} else if (!group.equals(other.group))
			return false;
		if (key == null) {
			if (other.key != null)
				return false;
		} else if (!key.equals(other.key))
			return false;
		if (locked != other.locked)
			return false;
		if (lockedDate == null) {
			if (other.lockedDate != null)
				return false;
		} else if (!lockedDate.equals(other.lockedDate))
			return false;
		if (!Arrays.equals(logo, other.logo))
			return false;
		if (metrics == null) {
			if (other.metrics != null)
				return false;
		} else if (!metrics.equals(other.metrics))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (projectId == null) {
			if (other.projectId != null)
				return false;
		} else if (!projectId.equals(other.projectId))
			return false;
		if (ranking == null) {
			if (other.ranking != null)
				return false;
		} else if (!ranking.equals(other.ranking))
			return false;
		if (reports == null) {
			if (other.reports != null)
				return false;
		} else if (!reports.equals(other.reports))
			return false;
		if (url == null) {
			if (other.url != null)
				return false;
		} else if (!url.equals(other.url))
			return false;
		return true;
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

	public Date getCreateddate() {
		return createdDate;
	}

	public void setCreateddate(Date createddate) {
		this.createdDate = createddate;
	}

	public Date getLockeddate() {
		return lockedDate;
	}

	public void setLockeddate(Date lockeddate) {
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

}
