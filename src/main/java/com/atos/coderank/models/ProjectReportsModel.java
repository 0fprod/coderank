package com.atos.coderank.models;

import java.util.Date;

public class ProjectReportsModel {

	private Long reportId;
	private String name;
	private byte[] document;
	private Date createdDate;
	private String method;
	private String format;
	private ProjectModel project;
	private ReportNotificationModel notification;

	public ProjectReportsModel() {

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

	public ProjectModel getProject() {
		return project;
	}

	public void setProject(ProjectModel project) {
		this.project = project;
	}

	public ReportNotificationModel getNotification() {
		return notification;
	}

	public void setNotification(ReportNotificationModel notification) {
		this.notification = notification;
	}

}
