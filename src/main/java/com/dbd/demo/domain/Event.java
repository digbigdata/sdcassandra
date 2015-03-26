package com.dbd.demo.domain;

import java.io.Serializable;
import java.util.UUID;

public interface Event extends Serializable {

    Integer getEid();
    void setEid(Integer eid);
    void setEventId(UUID eventId);
    UUID getEventId();
    void setTag(String eventType);
    String getTag();
    void setValue(String value);
    String getValue();
}
