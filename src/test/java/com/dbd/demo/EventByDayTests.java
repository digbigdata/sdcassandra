package com.dbd.demo;

import com.dbd.demo.domain.Event;
import com.dbd.demo.domain.SimpleEvent;
import com.dbd.demo.service.cassandra.CassandraEventsService;
import java.util.List;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.data.cassandra.core.CassandraOperations;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Date;
import java.util.UUID;

import static org.junit.Assert.assertEquals;
import static com.dbd.demo.service.cassandra.TableConstants.*;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = AppStart.class)
public class EventByDayTests {

    @Autowired
    CassandraEventsService cassandraEventsService;
    @Autowired
    CassandraOperations cassandraOperations;

    @Before
    public void setUp() {
        cassandraOperations.truncate(EVENT_BY_DAY_TABLE_NAME);
        cassandraOperations.truncate(EVENT_BY_TAG_TABLE_NAME);
    }

    @Test
    public void saveEvent() {
        Event event = new SimpleEvent();
        event.setEid(1001);
        event.setTag("an_get_query");
        event.setValue("OK");

        Event result = this.cassandraEventsService.addEvent(event);
        Integer eid = result.getEid();
        UUID eventId = result.getEventId();
        Event readBack = this.cassandraEventsService.findEvent(eid, eventId);

        assertEquals(event.getValue(), readBack.getValue());
    }

    @Test
    public void getEventsByDay() {
        cassandraOperations.truncate(EVENT_BY_DAY_TABLE_NAME);

        Event event = new SimpleEvent();
        event.setEid(1001);
        event.setTag("an_get_query");
        event.setValue("OK");

        this.cassandraEventsService.addEvent(event);

        List<Event> result = this.cassandraEventsService.findEventsByDate(1001, new Date());

        assertEquals(1, result.size());

        assertEquals(result.get(0).getValue(), "OK");
    }

}
