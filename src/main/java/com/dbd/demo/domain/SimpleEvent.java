package com.dbd.demo.domain;

import java.util.UUID;

public class SimpleEvent extends AbstractEvent {

    protected Integer eid;
    protected UUID eventId;

    @Override
    public void setEid(Integer eid) {
        this.eid = eid;
    }

    @Override
    public Integer getEid() {
        return eid;
    }

    @Override
    public void setEventId(UUID eventId) {
        this.eventId = eventId;
    }

    @Override
    public UUID getEventId() {
        return this.eventId;
    }
}
