package com.dbd.demo.domain;

import java.util.UUID;

public abstract class AbstractEvent implements Event {

    protected Integer eid;
    protected String tag;
    protected String value;


    @Override
    public abstract void setEid(Integer eid);

    @Override
    public abstract Integer getEid();

    @Override
    public abstract void setEventId(UUID eventId);

    @Override
    public abstract UUID getEventId();

    @Override
    public void setTag(String tag) {
        this.tag = tag;
    }

    @Override
    public String getTag() {
        return this.tag;
    }

    @Override
    public void setValue(String value) {
        this.value = value;
    }

    @Override
    public String getValue() {
        return this.value;
    }
}
