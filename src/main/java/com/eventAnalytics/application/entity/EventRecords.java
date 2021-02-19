package com.eventAnalytics.application.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedStoredProcedureQuery;
import javax.persistence.ParameterMode;
import javax.persistence.StoredProcedureParameter;
import javax.persistence.Table;

@Entity
@Table(name = "event_records")
@NamedStoredProcedureQuery(
		name = "EventRecords.getEventStats",
		procedureName="getEventStatsByTimestamp",
		parameters= {
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "epochLowerBound", type = Long.class),
				@StoredProcedureParameter(mode = ParameterMode.IN, name = "epochUpperBound", type = Long.class),
				@StoredProcedureParameter(mode = ParameterMode.OUT, name = "uniqueUsers", type = Integer.class),
				@StoredProcedureParameter(mode = ParameterMode.OUT, name = "totalClicks", type = Integer.class),
				@StoredProcedureParameter(mode = ParameterMode.OUT, name = "totalImpressions", type = Integer.class)
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
