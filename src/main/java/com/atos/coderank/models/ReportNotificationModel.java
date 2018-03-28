package com.atos.coderank.models;

import java.util.Date;
import java.util.List;

public class ReportNotificationModel {

	private Long notificationId;
	private List<String> downloadList;
	private List<String> emailedList;
	private Date nextDate;
	private Date lastDate;
	private ProjectReportsModel report;

	public ReportNotificationModel() {

	}

	public Long getNotificationId() {
		return notificationId;
	}

	public void setNotificationId(Long notificationId) {
		this.notificationId = notificationId;
	}

	public List<String> getDownloadList() {
		return downloadList;
	}

	public void setDownloadList(List<String> downloadList) {
		this.downloadList = downloadList;
	}

	public List<String> getEmailedList() {
		return emailedList;
	}

	public void setEmailedList(List<String> emailedList) {
		this.emailedList = emailedList;
	}

	public Date getNextDate() {
		return nextDate;
	}

	public void setNextDate(Date nextDate) {
		this.nextDate = nextDate;
	}

	public Date getLastDate() {
		return lastDate;
	}

	public void setLastDate(Date lastDate) {
		this.lastDate = lastDate;
	}

	public ProjectReportsModel getReport() {
		return report;
	}

	public void setReport(ProjectReportsModel report) {
		this.report = report;
	}

}
