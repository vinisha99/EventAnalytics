package com.eventAnalytics.application.controllers;

import java.time.Instant;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.MediaType;
import org.apache.log4j.Logger;
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
	final Logger logger = Logger.getLogger(EventController.class);
	
	@Autowired
	private EventRecordsService eventRecordsService;
	
	@PostMapping("/")
	public EventRecords saveEventRecord(@RequestBody EventRecords eventRecord) {
		logger.info("Inside post controller: Save event record");
		long currentEpocTime = Instant.now().toEpochMilli();
		eventRecord.setEpochTime(currentEpocTime);
		logger.info("Event record inserted with userID: "+ eventRecord.getUserID());
		return eventRecordsService.saveEventRecord(eventRecord);
	}
	
	@GetMapping("/id/{ID}")
	public EventRecords getByID(@PathVariable("ID") Long ID) {
		logger.info("Inside get controller : get record by ID");
		return eventRecordsService.getByID(ID);
	}
	
	@GetMapping("/{epochTime}")
	public String getEventStatsByTimestamp(@PathVariable("epochTime") Long epochTime){
		logger.info("Inside get controller : get UniqueUsers By Timestamp");
		String eventStats = eventRecordsService.getEventStatsByTimestamp(epochTime);
		logger.info("Inside get controller : Received Event stats");
		return eventStats;
	}

}
