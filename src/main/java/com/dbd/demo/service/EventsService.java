package com.dbd.demo.service;

import com.dbd.demo.domain.Event;

import java.util.Date;
import java.util.List;
import java.util.UUID;

public interface EventsService {

    Event addEvent(Event event);

    Event findEvent(Integer eid, UUID event);

    List<Event> findEventsByDate(Integer eid, Date date);

    List<Event> findEventsByTag(Integer eid, String tag);

}