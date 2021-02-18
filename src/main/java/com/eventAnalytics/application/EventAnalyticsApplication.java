package com.eventAnalytics.application;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EnableJpaRepositories(basePackages = "com.eventAnalytics.application.repository")
@SpringBootApplication
public class EventAnalyticsApplication {

	public static void main(String[] args) {
		SpringApplication.run(EventAnalyticsApplication.class, args);
		
	}

}
