package com.eventAnalytics.application.controllers;

import java.time.Instant;

import org.springframework.http.MediaType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.eventAnalytics.application.entity.EventRecords;
import com.eventAnalytics.application.services.EventRecordsService;

@RestController
@RequestMapping("/analytics")
public class EventController {
	
	@Autowired
	private EventRecordsService eventRecordsService;
	
	@PostMapping("/")
	public EventRecords saveEventRecord(@RequestBody EventRecords eventRecord) {
		System.out.println("Inside post controller");
		long currentEpocTime = Instant.now().toEpochMilli();
		eventRecord.setEpochTime(currentEpocTime);
		System.out.println("Event record created: "+ eventRecord.getUserID());
		return eventRecordsService.saveEventRecord(eventRecord);
	}

}
