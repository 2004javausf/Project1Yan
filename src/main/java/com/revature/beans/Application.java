package com.revature.beans;

import java.io.Serializable;
import java.sql.Blob;
import java.sql.Date;
import java.sql.Timestamp;

public class Application implements Serializable{
	
	private static final long serialVersionUID = 821258876616616199L;
	private int appId;
	private int employeeId;
	private String employeeName;
	private Date eventDate;
	private String eventLocation;
	private String eventDescription;
	private Double eventCost;
	private String gradingFormat;
	private String typeofEvent;
	private String justification;
	private Blob attachment;
	private Blob approval;
	private String status;
	private String subStatus;
	private Double appAmount;
	private Timestamp appTime;
	private String grade;
	private String denyInfo;
	private String additionalInfo;
	private Double provedAmount;
	private String presentation;
	
	public Application() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Application(int appId, int employeeId, String employeeName, Date eventDate, String eventLocation,
			String eventDescription, Double eventCost, String gradingFormat, String typeofEvent, String justification,
			Blob attachment, Blob approval, String status, String subStatus, Double appAmount, Timestamp appTime,
			String grade, String denyInfo, String additionalInfo, Double provedAmount, String presentation) {
		super();
		this.appId = appId;
		this.employeeId = employeeId;
		this.employeeName = employeeName;
		this.eventDate = eventDate;
		this.eventLocation = eventLocation;
		this.eventDescription = eventDescription;
		this.eventCost = eventCost;
		this.gradingFormat = gradingFormat;
		this.typeofEvent = typeofEvent;
		this.justification = justification;
		this.attachment = attachment;
		this.approval = approval;
		this.status = status;
		this.subStatus = subStatus;
		this.appAmount = appAmount;
		this.appTime = appTime;
		this.grade = grade;
		this.denyInfo = denyInfo;
		this.additionalInfo = additionalInfo;
		this.provedAmount = provedAmount;
		this.presentation = presentation;
	}

	public int getAppId() {
		return appId;
	}

	public void setAppId(int appId) {
		this.appId = appId;
	}

	public int getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(int employeeId) {
		this.employeeId = employeeId;
	}

	public String getEmployeeName() {
		return employeeName;
	}

	public void setEmployeeName(String employeeName) {
		this.employeeName = employeeName;
	}

	public Date getEventDate() {
		return eventDate;
	}

	public void setEventDate(Date eventDate) {
		this.eventDate = eventDate;
	}

	public String getEventLocation() {
		return eventLocation;
	}

	public void setEventLocation(String eventLocation) {
		this.eventLocation = eventLocation;
	}

	public String getEventDescription() {
		return eventDescription;
	}

	public void setEventDescription(String eventDescription) {
		this.eventDescription = eventDescription;
	}

	public Double getEventCost() {
		return eventCost;
	}

	public void setEventCost(Double eventCost) {
		this.eventCost = eventCost;
	}

	public String getGradingFormat() {
		return gradingFormat;
	}

	public void setGradingFormat(String gradingFormat) {
		this.gradingFormat = gradingFormat;
	}

	public String getTypeofEvent() {
		return typeofEvent;
	}

	public void setTypeofEvent(String typeofEvent) {
		this.typeofEvent = typeofEvent;
	}

	public String getJustification() {
		return justification;
	}

	public void setJustification(String justification) {
		this.justification = justification;
	}

	public Blob getAttachment() {
		return attachment;
	}

	public void setAttachment(Blob attachment) {
		this.attachment = attachment;
	}

	public Blob getApproval() {
		return approval;
	}

	public void setApproval(Blob approval) {
		this.approval = approval;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getSubStatus() {
		return subStatus;
	}

	public void setSubStatus(String subStatus) {
		this.subStatus = subStatus;
	}

	public Double getAppAmount() {
		return appAmount;
	}

	public void setAppAmount(Double appAmount) {
		this.appAmount = appAmount;
	}

	public Timestamp getAppTime() {
		return appTime;
	}

	public void setAppTime(Timestamp appTime) {
		this.appTime = appTime;
	}

	public String getGrade() {
		return grade;
	}

	public void setGrade(String grade) {
		this.grade = grade;
	}

	public String getDenyInfo() {
		return denyInfo;
	}

	public void setDenyInfo(String denyInfo) {
		this.denyInfo = denyInfo;
	}

	public String getAdditionalInfo() {
		return additionalInfo;
	}

	public void setAdditionalInfo(String additionalInfo) {
		this.additionalInfo = additionalInfo;
	}

	public Double getProvedAmount() {
		return provedAmount;
	}

	public void setProvedAmount(Double provedAmount) {
		this.provedAmount = provedAmount;
	}

	public String getPresentation() {
		return presentation;
	}

	public void setPresentation(String presentation) {
		this.presentation = presentation;
	}

	@Override
	public String toString() {
		return "Application [appId=" + appId + ", employeeId=" + employeeId + ", employeeName=" + employeeName
				+ ", eventDate=" + eventDate + ", eventLocation=" + eventLocation + ", eventDescription="
				+ eventDescription + ", eventCost=" + eventCost + ", gradingFormat=" + gradingFormat + ", typeofEvent="
				+ typeofEvent + ", justification=" + justification + ", attachment=" + attachment + ", approval="
				+ approval + ", status=" + status + ", subStatus=" + subStatus + ", appAmount=" + appAmount
				+ ", appTime=" + appTime + ", grade=" + grade + ", denyInfo=" + denyInfo + ", additionalInfo="
				+ additionalInfo + ", provedAmount=" + provedAmount + ", presentation=" + presentation + "]";
	}

	
}
