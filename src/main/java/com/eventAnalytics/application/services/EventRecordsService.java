package com.eventAnalytics.application.services;

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
		
		StoredProcedureQuery store = em.createNamedStoredProcedureQuery("EventRecords.getEventStats");
        store.setParameter("epochTime", epochTime); 
        
        store.execute();
        
        Long uniqueUsers = (Long) store.getOutputParameterValue("uniqueUsers");
        Long totalClicks = (Long) store.getOutputParameterValue("totalClicks");
        Long totalImpressions = (Long) store.getOutputParameterValue("totalImpressions");

        String res = "TotUsers: "+uniqueUsers + " clicks: "+totalClicks+" impressions: "+totalImpressions;

        return res;
	}
	
	public EventRecords getByID(Long ID) {
		System.out.println("Inside service layer - get by ID");
		return eventRecordsRepository.findById(ID).get();
	}
	
}
