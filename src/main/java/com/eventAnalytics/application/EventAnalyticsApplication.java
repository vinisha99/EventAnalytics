package com.eventAnalytics.application;

import org.apache.log4j.Logger;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EnableJpaRepositories(basePackages = "com.eventAnalytics.application.repository")
@SpringBootApplication
public class EventAnalyticsApplication {

	public static void main(String[] args) {
		final Logger logger = Logger.getLogger(EventAnalyticsApplication.class);
		
		logger.info("Event Analytics Application started");
		SpringApplication.run(EventAnalyticsApplication.class, args);
		
	}

}
