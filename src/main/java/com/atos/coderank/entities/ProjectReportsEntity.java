package com.atos.coderank.entities;

import java.util.Date;
import java.util.Map;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "REPORTS")
public class ProjectReportsEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SQ_REPORTS")
	@SequenceGenerator(name = "SQ_REPORTS", sequenceName = "SQ_REPORTS", allocationSize = 1)
	@Column(name = "REPORT_ID")
	private Long reportId;

	@Column(name = "METRICS_ID")
	private Long metricsId;

	@Column(name = "NAME")
	private String name;

	@Column(name = "DOCUMENT")
	private byte[] document;

	@Column(name = "CREATED_DATE")
	private Date createdDate;

	@Column(name = "METHOD")
	private String method;

	@Column(name = "FORMAT")
	private String format;

	@JsonIgnore
	@ManyToOne(fetch = FetchType.LAZY)
	private ProjectEntity project;

	@OneToOne(mappedBy = "report", cascade = CascadeType.ALL)
	private ReportNotificationEntity notification;
	
	@Transient
	private Map<String, String> serializedProject;

	public ProjectReportsEntity() {
		// Comment Required by sonarlint
	}

	@Override
	public String toString() {
		return "ProjectReportsEntity [reportId=" + reportId + ", metricsId=" + metricsId + ", name=" + name
				+ ", createdDate=" + createdDate + ", method=" + method + ", format=" + format + "]";
	}

	public Long getReportId() {
		return reportId;
	}

	public void setReportId(Long reportId) {
		this.reportId = reportId;
	}

	public Long getMetricsId() {
		return metricsId;
	}

	public void setMetricsId(Long metricsId) {
		this.metricsId = metricsId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public byte[] getDocument() {
		return document;
	}

	public void setDocument(byte[] document) {
		this.document = document;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public String getMethod() {
		return method;
	}

	public void setMethod(String method) {
		this.method = method;
	}

	public String getFormat() {
		return format;
	}

	public void setFormat(String format) {
		this.format = format;
	}

	public ProjectEntity getProject() {
		return project;
	}

	public void setProject(ProjectEntity project) {
		this.project = project;
	}

	public ReportNotificationEntity getNotification() {
		return notification;
	}

	public void setNotification(ReportNotificationEntity notification) {
		this.notification = notification;
	}

	public Map<String, String> getSerializedProject() {
		return serializedProject;
	}

	public void setSerializedProject(Map<String, String> serializedProject) {
		this.serializedProject = serializedProject;
	}

}
