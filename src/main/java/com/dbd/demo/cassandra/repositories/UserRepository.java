package com.dbd.demo.cassandra.repositories;

import com.dbd.demo.domain.User;
import org.springframework.data.cassandra.repository.CassandraRepository;
import org.springframework.data.cassandra.repository.Query;

public interface UserRepository extends CassandraRepository<User> {
    @Query("select * from users where user_id = ?0")
    User findByUserId(String userId);
}
