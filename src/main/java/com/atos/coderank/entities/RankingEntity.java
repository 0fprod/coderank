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
	public String toString() {
		return "RankingEntity [calification=" + calification + ", project=" + project + "]";
	}

}
