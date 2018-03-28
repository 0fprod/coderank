package com.atos.coderank.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.MapsId;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "RANKING")
public class RankingEntity {

	@Id
	private String projectId;

	@Column(name = "CALIFICATION")
	private Double calification;

	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "PROJECT_ID", unique = true)
	@MapsId
	private ProjectEntity project;

	public RankingEntity(Double calification) {
		this.calification = calification;
	}

	public RankingEntity() {

	}

	public Double getCalification() {
		return calification;
	}

	public void setCalification(Double calification) {
		this.calification = calification;
	}

	public ProjectEntity getProject() {
		return project;
	}

	public void setProject(ProjectEntity project) {
		this.project = project;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((calification == null) ? 0 : calification.hashCode());
		result = prime * result + ((project == null) ? 0 : project.hashCode());
		result = prime * result + ((projectId == null) ? 0 : projectId.hashCode());
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
		RankingEntity other = (RankingEntity) obj;
		if (calification == null) {
			if (other.calification != null)
				return false;
		} else if (!calification.equals(other.calification))
			return false;
		if (project == null) {
			if (other.project != null)
				return false;
		} else if (!project.equals(other.project))
			return false;
		if (projectId == null) {
			if (other.projectId != null)
				return false;
		} else if (!projectId.equals(other.projectId))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "RankingEntity [calification=" + calification + ", project=" + project + "]";
	}

}
