package com.eventAnalytics.application.repository;

import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.eventAnalytics.application.entity.EventRecords;

@Repository
public interface EventRecordsRepository extends JpaRepository<EventRecords, Long> {

}
