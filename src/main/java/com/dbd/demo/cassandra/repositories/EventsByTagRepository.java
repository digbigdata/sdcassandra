package com.dbd.demo.cassandra.repositories;

import com.dbd.demo.domain.Event;
import com.dbd.demo.domain.EventByTag;
import org.springframework.data.cassandra.repository.CassandraRepository;
import org.springframework.data.cassandra.repository.Query;

import java.util.List;

/**
 * Created by niallmilton on 04/03/15.
 */
public interface EventsByTagRepository extends CassandraRepository<EventByTag> {

    @Query("select * from events_by_tag where eid = ?0 and tag = ?1")
    List<EventByTag> findByEidAndTag(Integer eid, String tag);

}
