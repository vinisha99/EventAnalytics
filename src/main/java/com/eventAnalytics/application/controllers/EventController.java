package com.eventAnalytics.application.controllers;

import java.time.Instant;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.MediaType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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
	
	@GetMapping("/id/{ID}")
	public EventRecords getByID(@PathVariable("ID") Long ID) {
		return eventRecordsService.getByID(ID);
	}
	
	@GetMapping("/{epochTime}")
	public String getUniqueUsersByTimestamp(@PathVariable("epochTime") Long epochTime){
		
		System.out.println("Inside sp call controller");
		String eventStats = eventRecordsService.getUniqueUsersByTimestamp(epochTime);
		return eventStats;
	}

}
