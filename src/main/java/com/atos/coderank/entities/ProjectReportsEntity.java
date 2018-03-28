package com.atos.coderank.entities;

import java.util.Arrays;
import java.util.Date;

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

@Entity
@Table(name = "REPORTS")
public class ProjectReportsEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SQ_REPORTS")
	@SequenceGenerator(name = "SQ_REPORTS", sequenceName = "SQ_REPORTS", allocationSize = 1)
	@Column(name = "REPORT_ID")
	private Long reportId;

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

	@ManyToOne(fetch = FetchType.LAZY)
	private ProjectEntity project;

	@OneToOne(mappedBy = "report", cascade = CascadeType.ALL)
	private ReportNotificationEntity notification;

	public ProjectReportsEntity() {

	}

	public ProjectReportsEntity(String name, byte[] document, Date createddate, String method, String format) {
		super();
		this.name = name;
		this.document = document;
		this.createdDate = createddate;
		this.method = method;
		this.format = format;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((createdDate == null) ? 0 : createdDate.hashCode());
		result = prime * result + Arrays.hashCode(document);
		result = prime * result + ((format == null) ? 0 : format.hashCode());
		result = prime * result + ((method == null) ? 0 : method.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((notification == null) ? 0 : notification.hashCode());
		result = prime * result + ((project == null) ? 0 : project.hashCode());
		result = prime * result + ((reportId == null) ? 0 : reportId.hashCode());
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
		ProjectReportsEntity other = (ProjectReportsEntity) obj;
		if (createdDate == null) {
			if (other.createdDate != null)
				return false;
		} else if (!createdDate.equals(other.createdDate))
			return false;
		if (!Arrays.equals(document, other.document))
			return false;
		if (format == null) {
			if (other.format != null)
				return false;
		} else if (!format.equals(other.format))
			return false;
		if (method == null) {
			if (other.method != null)
				return false;
		} else if (!method.equals(other.method))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (notification == null) {
			if (other.notification != null)
				return false;
		} else if (!notification.equals(other.notification))
			return false;
		if (project == null) {
			if (other.project != null)
				return false;
		} else if (!project.equals(other.project))
			return false;
		if (reportId == null) {
			if (other.reportId != null)
				return false;
		} else if (!reportId.equals(other.reportId))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "ProjectReportsEntity [reportId=" + reportId + ", name=" + name + ", document="
				+ Arrays.toString(document) + ", createddate=" + createdDate + ", method=" + method + ", format="
				+ format + "]";
	}

	public Long getReportId() {
		return reportId;
	}

	public void setReportId(Long reportId) {
		this.reportId = reportId;
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

	public Date getCreateddate() {
		return createdDate;
	}

	public void setCreateddate(Date createddate) {
		this.createdDate = createddate;
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

}
