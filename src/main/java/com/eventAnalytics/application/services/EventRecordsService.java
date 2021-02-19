package com.eventAnalytics.application.services;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.StoredProcedureQuery;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.eventAnalytics.application.entity.EventRecords;
import com.eventAnalytics.application.repository.EventRecordsRepository;

@Service
@Transactional
public class EventRecordsService {

	@Autowired
	private EventRecordsRepository eventRecordsRepository;
	@Autowired
	private EntityManager em;
	
	public EventRecords saveEventRecord(EventRecords eventRecord) {
		System.out.println("Inside service layer - Post data");
		return eventRecordsRepository.save(eventRecord);
	}
	
	@SuppressWarnings("unchecked")
	public String getUniqueUsersByTimestamp(Long epochTime){
		System.out.println("Inside service layer - getUniqueUsersByTimestamp");
		
        long epochLowerBound = setEpochTime(epochTime, 0);
        long epochUpperBound = setEpochTime(epochTime, 1);
        
        System.out.println("Received Epochtime");
		
        return executeGetEventStatsSP(epochLowerBound, epochUpperBound);
	}
	
	public EventRecords getByID(Long ID) {
		System.out.println("Inside service layer - get by ID");
		return eventRecordsRepository.findById(ID).get();
	}
	
	public long setEpochTime(long currepoch, int hoursToAdd) {
		long epochBound = 0;
		try {
			
			Instant instant = Instant.ofEpochSecond(currepoch);
			LocalDateTime currentDate = LocalDateTime.ofInstant(instant,ZoneId.of("GMT"));
			LocalDateTime cDate = LocalDateTime.parse(currentDate.toString());
			epochBound = cDate.plusHours(hoursToAdd).withMinute(0).withSecond(0).withNano(0).toEpochSecond(ZoneOffset.of("+00:00"));
		
		}catch (Exception e) {
			System.out.println("error occured in fining lower bound and upper bound");
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return epochBound;
	}
	
	public String executeGetEventStatsSP(Long epochLowerBound, Long epochUpperBound) {
		StoredProcedureQuery store = em.createNamedStoredProcedureQuery("EventRecords.getEventStats");
        store.setParameter("epochLowerBound", epochLowerBound); 
        store.setParameter("epochUpperBound", epochUpperBound); 
        store.execute();
        
        int uniqueUsers = (int) store.getOutputParameterValue("uniqueUsers");
        int totalClicks = (int) store.getOutputParameterValue("totalClicks");
        int totalImpressions = (int) store.getOutputParameterValue("totalImpressions");

        String res = "TotalUsers: "+uniqueUsers + "\nTotalclicks: "+totalClicks+"\nTotalimpressions: "+totalImpressions;
        return res;
	}
	
}
