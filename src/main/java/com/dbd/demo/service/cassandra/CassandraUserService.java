package com.dbd.demo.service.cassandra;

import com.datastax.driver.core.Statement;
import com.datastax.driver.core.querybuilder.Delete;
import com.datastax.driver.core.querybuilder.Insert;
import com.datastax.driver.core.querybuilder.QueryBuilder;
import com.datastax.driver.core.querybuilder.Select;
import com.dbd.demo.cassandra.repositories.UserRepository;
import com.dbd.demo.domain.User;
import com.dbd.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.cassandra.core.CassandraOperations;
import org.springframework.stereotype.Component;

@Component
public class CassandraUserService implements UserService {

    private final UserRepository userRepository;
    private final CassandraOperations cassandraOperations;

    @Autowired
    public CassandraUserService(UserRepository userRepository,
                                CassandraOperations cassandraOperations) {
        this.userRepository = userRepository;
        this.cassandraOperations = cassandraOperations;
    }

    @Override
    public void createUser(User user) {
        Insert insert = QueryBuilder
                .insertInto("users")
                .value("user_id", user.getUserId())
                .value("first_name", user.getFirstName())
                .value("last_name", user.getLastName())
                .value("password", user.getPassword())
                .ifNotExists();

        cassandraOperations.execute(insert);
    }

    @Override
    public User findUser(String userId) {
        return userRepository.findByUserId(userId);
    }

    @Override
    public void deleteUser(String userId) {
        User user = new User();
        user.setUserId(userId);
        cassandraOperations.delete(user);
    }
}
