package com.eventAnalytics.application.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.eventAnalytics.application.entity.EventRecords;
import com.eventAnalytics.application.repository.EventRecordsRepository;

@Service
@Transactional
public class EventRecordsService {

	@Autowired
	private EventRecordsRepository eventRecordsRepository;
	
	public EventRecords saveEventRecord(EventRecords eventRecord) {
		System.out.println("Inside service layer");
		return eventRecordsRepository.save(eventRecord);
	}
}
