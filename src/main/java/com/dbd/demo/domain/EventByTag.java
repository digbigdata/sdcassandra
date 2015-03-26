package com.dbd.demo.domain;

import com.datastax.driver.core.utils.UUIDs;
import com.dbd.demo.util.DateUtil;
import org.springframework.cassandra.core.Ordering;
import org.springframework.cassandra.core.PrimaryKeyType;
import org.springframework.data.cassandra.mapping.PrimaryKeyColumn;
import org.springframework.data.cassandra.mapping.Table;

import java.util.UUID;

@Table("events_by_tag")
public class EventByTag extends AbstractEvent {

    @PrimaryKeyColumn(name = "eid", ordinal = 0, type = PrimaryKeyType.PARTITIONED)
    private Integer eid;

    @PrimaryKeyColumn(name = "tag", ordinal = 1, type = PrimaryKeyType.PARTITIONED)
    private String tag;

    @PrimaryKeyColumn(name = "event_id", ordinal = 2, type = PrimaryKeyType.CLUSTERED, ordering = Ordering.DESCENDING)
    private UUID eventId;

    public EventByTag() { }

    public EventByTag(Event event) {
        this.eid = event.getEid();
        this.tag = event.getTag();
        this.value = event.getValue();
        this.eventId = event.getEventId();
    }

    @Override
    public Integer getEid() {
        return eid;
    }

    @Override
    public void setEid(Integer eid) {
        this.eid = eid;
    }

    @Override
    public void setEventId(UUID eventId) {
        this.eventId = eventId;
    }

    @Override
    public UUID getEventId() {
        return eventId;
    }

    @Override
    public String getTag() {
        return this.tag;
    }

    @Override
    public void setTag(String tag) {
        this.tag = tag;
    }
}
