package com.dbd.demo.service.cassandra;

import com.datastax.driver.core.utils.UUIDs;
import com.dbd.demo.cassandra.repositories.EventsByDayRepository;
import com.dbd.demo.cassandra.repositories.EventsByTagRepository;
import com.dbd.demo.domain.Event;
import com.dbd.demo.domain.EventByDay;
import com.dbd.demo.domain.EventByTag;
import com.dbd.demo.service.EventsService;
import com.dbd.demo.util.DateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.cassandra.repository.MapId;
import org.springframework.stereotype.Component;

import java.util.*;

import static org.springframework.data.cassandra.repository.support.BasicMapId.id;

@Component
public class CassandraEventsService implements EventsService {

    private final EventsByDayRepository eventsByDayRepository;
    private final EventsByTagRepository eventsByTagRepository;

    @Autowired
    CassandraEventsService(EventsByDayRepository eventsByDayRepository,
                           EventsByTagRepository eventsByTagRepository) {
        this.eventsByDayRepository = eventsByDayRepository;
        this.eventsByTagRepository = eventsByTagRepository;

    }

    @Override
    public Event addEvent(Event event) {
        if(event.getEventId() == null) {
            event.setEventId(UUIDs.timeBased());
        }

        EventByDay eventByMonth = new EventByDay(event);
        EventByTag eventByTag = new EventByTag(event);

        this.eventsByTagRepository.save(eventByTag);
        return this.eventsByDayRepository.save(eventByMonth);
    }

    @Override
    public Event findEvent(Integer eid, UUID eventId) {
        Integer day = DateUtil.getDaysSinceEpochFromUUID(eventId);
        MapId id = id().with("eid", eid).with("day", day).with("eventId", eventId);
        return this.eventsByDayRepository.findOne(id);
    }

    @Override
    public List<Event> findEventsByDate(Integer eid, Date date) {
        Integer day = DateUtil.getDaysSinceEpochFromTimestamp(date.getTime());
        return new ArrayList<Event>(this.eventsByDayRepository.findByEidAndDay(eid, day));
    }

    @Override
    public List<Event> findEventsByTag(Integer eid, String tag) {
        return new ArrayList<Event>(this.eventsByTagRepository.findByEidAndTag(eid, tag));
    }

}
