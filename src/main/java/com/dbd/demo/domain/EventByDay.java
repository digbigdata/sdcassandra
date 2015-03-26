package com.dbd.demo.domain;

import com.datastax.driver.core.utils.UUIDs;
import com.dbd.demo.util.DateUtil;
import org.springframework.cassandra.core.Ordering;
import org.springframework.cassandra.core.PrimaryKeyType;
import org.springframework.data.cassandra.mapping.PrimaryKeyColumn;
import org.springframework.data.cassandra.mapping.Table;

import java.util.Date;
import java.util.UUID;

@Table("events_by_day")
public class EventByDay extends AbstractEvent {

    @PrimaryKeyColumn(name = "eid", ordinal = 0, type = PrimaryKeyType.PARTITIONED)
    private Integer eid;

    @PrimaryKeyColumn(name = "day", ordinal = 1, type = PrimaryKeyType.PARTITIONED)
    private Integer day;

    @PrimaryKeyColumn(name = "event_id", ordinal = 2, type = PrimaryKeyType.CLUSTERED, ordering = Ordering.DESCENDING)
    private UUID eventId;

    public EventByDay() { }

    public EventByDay(Event event) {
        this.eid = event.getEid();
        this.tag = event.getTag();
        this.value = event.getValue();
        this.eventId = event.getEventId();
        final long timestamp = DateUtil.getTimeFromUUID(this.eventId);
        this.day = DateUtil.getDaysSinceEpochFromTimestamp(timestamp);
    }

    @Override
    public Integer getEid() {
        return eid;
    }

    @Override
    public void setEid(Integer eid) {
        this.eid = eid;
    }

    public Integer getDay() {
        return day;
    }

    public void setDay(Integer day) {
        this.day = day;
    }

    @Override
    public void setEventId(UUID eventId) {
        this.eventId = eventId;
    }

    @Override
    public UUID getEventId() {
        return eventId;
    }
}
