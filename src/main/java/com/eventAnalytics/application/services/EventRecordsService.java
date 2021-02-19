package com.eventAnalytics.application.services;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;

import javax.persistence.EntityManager;
import javax.persistence.StoredProcedureQuery;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.eventAnalytics.application.entity.EventRecords;
import com.eventAnalytics.application.repository.EventRecordsRepository;

@Service
@Transactional
public class EventRecordsService {
	final Logger logger = Logger.getLogger(EventRecordsService.class);

	@Autowired
	private EventRecordsRepository eventRecordsRepository;
	@Autowired
	private EntityManager em;
	
	public EventRecords saveEventRecord(EventRecords eventRecord) {
		logger.info("Inside service layer : saveEventRecord");
		return eventRecordsRepository.save(eventRecord);
	}
	
	public String getEventStatsByTimestamp(Long epochTime){
		logger.info("Inside service layer : getUniqueUsersByTimestamp");
		
        long epochLowerBound = setEpochTime(epochTime, 0);
        logger.info("Inside service layer : getUniqueUsersByTimestamp - epochLowerBound Received: "+epochLowerBound);
        long epochUpperBound = setEpochTime(epochTime, 1);
        logger.info("Inside service layer : getUniqueUsersByTimestamp - epochUpperBound Received: "+epochUpperBound);
        logger.info("Inside service layer : getUniqueUsersByTimestamp - Received Epochtime");
		
        return executeGetEventStatsSP(epochLowerBound, epochUpperBound);
	}
	
	public EventRecords getByID(Long ID) {
		logger.info("Inside service layer : get by ID");
		return eventRecordsRepository.findById(ID).get();
	}
	
	public long setEpochTime(long currepoch, int hoursToAdd) {
		logger.info("Inside service layer : setEpochTime");
		long epochBound = 0;
		try {
			
			Instant instant = Instant.ofEpochMilli(currepoch);
			LocalDateTime currentDate = LocalDateTime.ofInstant(instant,ZoneId.of("GMT"));
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSS");
			LocalDateTime cDate = LocalDateTime.parse(currentDate.toString(), formatter);
			epochBound = cDate.plusHours(hoursToAdd).withMinute(0).withSecond(0).withNano(0).toInstant(ZoneOffset.of("+00:00")).toEpochMilli();
			logger.info("Inside service layer : setEpochTime - epoch Bound time in milliseconds generated: " + epochBound);
			
		}catch (Exception e) {
			logger.error("Inside service layer : setEpochTime - error occured in finding epoch time");
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return epochBound;
	}
	
	public String executeGetEventStatsSP(Long epochLowerBound, Long epochUpperBound) {
		logger.info("Inside service layer : executeGetEventStatsSP");
		StoredProcedureQuery store = em.createNamedStoredProcedureQuery("EventRecords.getEventStats");
        store.setParameter("epochLowerBound", epochLowerBound); 
        store.setParameter("epochUpperBound", epochUpperBound); 
        store.execute();
        logger.info("Inside service layer : executeGetEventStatsSP - executed SP");
        int uniqueUsers = (int) store.getOutputParameterValue("uniqueUsers");
        int totalClicks = (int) store.getOutputParameterValue("totalClicks");
        int totalImpressions = (int) store.getOutputParameterValue("totalImpressions");
        logger.info("Inside service layer : executeGetEventStatsSP - output received from SP ");
        String res = "TotalUsers: "+uniqueUsers + "\nTotalclicks: "+totalClicks+"\nTotalimpressions: "+totalImpressions;
        return res;
	}
	
}
