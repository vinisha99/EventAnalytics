package com.eventAnalytics.application.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.eventAnalytics.application.entity.EventRecords;

@Repository
public interface EventRecordsRepository extends JpaRepository<EventRecords, Long> {

}
