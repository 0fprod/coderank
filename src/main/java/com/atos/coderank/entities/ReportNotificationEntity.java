package com.atos.coderank.entities;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "NOTIFICATION")
public class ReportNotificationEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SQ_NOTIFICATION")
	@SequenceGenerator(name = "SQ_NOTIFICATION", sequenceName = "SQ_NOTIFICATION", allocationSize = 1)
	@Column(name = "NOTIFICATION_ID")
	private Long notificationId;

	@Column(name = "DOWNLOAD_LIST")
	private byte[] downloadList;

	@Column(name = "EMAILED_LIST")
	private byte[] emailedList;

	@Column(name = "NEXT_DATE")
	private Date nextDate;

	@Column(name = "LAST_DATE")
	private Date lastDate;

	@OneToOne
	private ProjectReportsEntity report;

	public ReportNotificationEntity() {

	}

	public ReportNotificationEntity(Long notificationId, byte[] downloadList, byte[] emailedList, Date nextDate,
			Date lastDate) {
		this.notificationId = notificationId;
		this.downloadList = downloadList;
		this.emailedList = emailedList;
		this.nextDate = nextDate;
		this.lastDate = lastDate;
	}

	@Override
	public String toString() {
		return "ReportNotificationEntity [notificationId=" + notificationId + ", nextDate=" + nextDate + ", lastDate="
				+ lastDate + "]";
	}

	public Long getNotificationId() {
		return notificationId;
	}

	public void setNotificationId(Long notificationId) {
		this.notificationId = notificationId;
	}

	public byte[] getDownloadList() {
		return downloadList;
	}

	public void setDownloadList(byte[] downloadList) {
		this.downloadList = downloadList;
	}

	public byte[] getEmailedList() {
		return emailedList;
	}

	public void setEmailedList(byte[] emailedList) {
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

	public ProjectReportsEntity getReport() {
		return report;
	}

	public void setReport(ProjectReportsEntity report) {
		this.report = report;
	}

}
