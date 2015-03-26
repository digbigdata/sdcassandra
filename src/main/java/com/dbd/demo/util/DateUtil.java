package com.dbd.demo.util;

import org.joda.time.DateTime;
import org.joda.time.Days;
import org.joda.time.MutableDateTime;

import java.util.Date;
import java.util.UUID;


/**
 * Created by niallmilton on 04/03/15.
 */
public class DateUtil {

    static final long NUM_100NS_INTERVALS_SINCE_UUID_EPOCH = 0x01b21dd213814000L;
    public static long getTimeFromUUID(UUID uuid) {
        return (uuid.timestamp() - NUM_100NS_INTERVALS_SINCE_UUID_EPOCH) / 10000;
    }

    public static Integer getDaysSinceEpochFromTimestamp(long timestamp) {
        DateTime now = new DateTime(timestamp);
        DateTime epoch = new DateTime(0);
        Days days = Days.daysBetween(epoch, now);
        return days.getDays();
    }

    public static Integer getDaysSinceEpochFromUUID(UUID uuid) {
        long timestamp = getTimeFromUUID(uuid);
        DateTime now = new DateTime(timestamp);
        DateTime epoch = new DateTime(0);
        Days days = Days.daysBetween(epoch, now);
        return days.getDays();
    }

}
