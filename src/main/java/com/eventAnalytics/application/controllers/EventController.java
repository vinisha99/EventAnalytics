package com.eventAnalytics.application.controllers;

import org.springframework.http.HttpStatus;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.eventAnalytics.application.entity.EventRecords;
import com.eventAnalytics.application.services.EventRecordsService;

@RestController
@RequestMapping("/analytics")
public class EventController {
	final Logger logger = Logger.getLogger(EventController.class);
	
	@Autowired
	private EventRecordsService eventRecordsService;
	
	@PostMapping("")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public EventRecords saveEventRecord(@RequestParam("timestamp") long epoch_time, @RequestParam("user") String user_id, @RequestParam("event") String event) throws Exception {
		logger.info("Inside post controller: Save event record");
		int event_id;
		if(event.toLowerCase().equals("click")) {
			event_id = 1;
		}else if(event.toLowerCase().equals("impression")) {
			event_id = 0;
		}else {
			logger.error("Invalid Event Type : "+ event);
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
		}
		EventRecords eventRecord = new EventRecords(user_id, event_id, epoch_time);
		logger.info("Event record inserted with userID: "+ user_id);
		return eventRecordsService.saveEventRecord(eventRecord);
	}
	
	@GetMapping("/id/{ID}")
	public EventRecords getByID(@PathVariable("ID") Long ID) {
		logger.info("Inside get controller : get record by ID");
		return eventRecordsService.getByID(ID);
	}
	
	@GetMapping("")
	public String getEventStatsByTimestamp(@RequestParam("timestamp") Long epochTime){
		logger.info("Inside get controller : get UniqueUsers By Timestamp");
		String eventStats = eventRecordsService.getEventStatsByTimestamp(epochTime);
		logger.info("Inside get controller : Received Event stats");
		return eventStats;
	}

}
