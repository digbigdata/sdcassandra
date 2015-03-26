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

import static com.dbd.demo.service.cassandra.TableConstants.EVENT_BY_DAY_TABLE_NAME;
import static org.junit.Assert.assertEquals;
import static com.dbd.demo.service.cassandra.TableConstants.*;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = AppStart.class)
public class EventByTagTests {

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
        String tag = result.getTag();

        List<Event> readBackList = this.cassandraEventsService.findEventsByTag(eid, tag);

        assertNotNull(readBackList);
        assertTrue((readBackList.size() > 0));

        System.out.println(readBackList.get(0).getClass().getName());
    }

}
