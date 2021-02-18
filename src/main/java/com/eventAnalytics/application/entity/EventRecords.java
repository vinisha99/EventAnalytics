package com.eventAnalytics.application.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedStoredProcedureQueries;
import javax.persistence.NamedStoredProcedureQuery;
import javax.persistence.ParameterMode;
import javax.persistence.StoredProcedureParameter;
import javax.persistence.Table;

@Entity
@Table(name = "event_records")
@NamedStoredProcedureQuery(
		name = "EventRecords.getEventStats",
		procedureName="getUniqueUsersByTimestamp",
		parameters= {
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "epochTime", type = Long.class),
				@StoredProcedureParameter(mode = ParameterMode.OUT, name = "uniqueUsers", type = Long.class),
				@StoredProcedureParameter(mode = ParameterMode.OUT, name = "totalClicks", type = Long.class),
				@StoredProcedureParameter(mode = ParameterMode.OUT, name = "totalImpressions", type = Long.class)
				})
public class EventRecords {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "ID")
	private Long ID;
	
	@Column(name = "userID")
	private String userID;
	
	@Column(name = "eventType")
	private int eventType;
	
	@Column(name = "epochTime")
	private Long epochTime;
	
	public EventRecords() {
		super();
	}

	public EventRecords(String userID, int eventType, Long epochTime) {
		super();
		this.userID = userID;
		this.eventType = eventType;
		this.epochTime = epochTime;
	}

	public EventRecords(String userID, int eventType) {
		super();
		this.userID = userID;
		this.eventType = eventType;
	}

	public Long getID() {
		return ID;
	}

	public String getUserID() {
		return userID;
	}

	public void setUserID(String userID) {
		this.userID = userID;
	}

	public int getEventType() {
		return eventType;
	}

	public void setEventType(int eventType) {
		this.eventType = eventType;
	}

	public Long getEpochTime() {
		return epochTime;
	}

	public void setEpochTime(Long epochTime) {
		this.epochTime = epochTime;
	}
	
}
