package com.smartevent.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.smartevent.entity.Event;

public interface EventRepository extends JpaRepository<Event, Long> {

}