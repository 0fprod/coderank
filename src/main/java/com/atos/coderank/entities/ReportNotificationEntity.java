package com.atos.coderank.entities;

import java.util.Arrays;
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
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + Arrays.hashCode(downloadList);
		result = prime * result + Arrays.hashCode(emailedList);
		result = prime * result + ((lastDate == null) ? 0 : lastDate.hashCode());
		result = prime * result + ((nextDate == null) ? 0 : nextDate.hashCode());
		result = prime * result + ((notificationId == null) ? 0 : notificationId.hashCode());
		result = prime * result + ((report == null) ? 0 : report.hashCode());
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
		ReportNotificationEntity other = (ReportNotificationEntity) obj;
		if (!Arrays.equals(downloadList, other.downloadList))
			return false;
		if (!Arrays.equals(emailedList, other.emailedList))
			return false;
		if (lastDate == null) {
			if (other.lastDate != null)
				return false;
		} else if (!lastDate.equals(other.lastDate))
			return false;
		if (nextDate == null) {
			if (other.nextDate != null)
				return false;
		} else if (!nextDate.equals(other.nextDate))
			return false;
		if (notificationId == null) {
			if (other.notificationId != null)
				return false;
		} else if (!notificationId.equals(other.notificationId))
			return false;
		if (report == null) {
			if (other.report != null)
				return false;
		} else if (!report.equals(other.report))
			return false;
		return true;
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
