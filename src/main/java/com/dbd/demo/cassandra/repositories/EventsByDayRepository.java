package com.dbd.demo.cassandra.repositories;

import com.dbd.demo.domain.Event;
import com.dbd.demo.domain.EventByDay;

import java.util.List;
import org.springframework.data.cassandra.repository.CassandraRepository;
import org.springframework.data.cassandra.repository.Query;

/**
 * Created by niallmilton on 04/03/15.
 */
public interface EventsByDayRepository extends CassandraRepository<EventByDay> {

    @Query("select * from events_by_day where eid = ?0 and day = ?1")
    List<EventByDay> findByEidAndDay(Integer eid, Integer day);

}
