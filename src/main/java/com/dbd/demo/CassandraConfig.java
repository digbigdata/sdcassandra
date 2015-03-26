package com.dbd.demo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.data.cassandra.config.CassandraClusterFactoryBean;
import org.springframework.data.cassandra.config.CassandraSessionFactoryBean;
import org.springframework.data.cassandra.config.SchemaAction;
import org.springframework.data.cassandra.convert.CassandraConverter;
import org.springframework.data.cassandra.convert.MappingCassandraConverter;
import org.springframework.data.cassandra.core.CassandraOperations;
import org.springframework.data.cassandra.core.CassandraTemplate;
import org.springframework.data.cassandra.mapping.BasicCassandraMappingContext;
import org.springframework.data.cassandra.mapping.CassandraMappingContext;
import org.springframework.data.cassandra.repository.config.EnableCassandraRepositories;
import org.springframework.data.repository.query.QueryLookupStrategy;

/**
 * Created by niallmilton on 19/02/15.
 */
@Configuration
@EnableCassandraRepositories(basePackages = { "com.dbd.demo.cassandra.repositories" },
        queryLookupStrategy = QueryLookupStrategy.Key.CREATE_IF_NOT_FOUND)
public class CassandraConfig  {

    @Autowired
    private Environment env;

    @Bean
    public CassandraClusterFactoryBean cluster() {

        CassandraClusterFactoryBean cluster = new CassandraClusterFactoryBean();
        cluster.setContactPoints(env.getProperty("cassandra.contactpoints"));
        cluster.setPort(Integer.parseInt(env.getProperty("cassandra.port")));

        return cluster;
    }

    @Bean
    public CassandraMappingContext mappingContext() {
        BasicCassandraMappingContext mappingContext = new BasicCassandraMappingContext();

        return mappingContext;
    }

    @Bean
    public CassandraConverter converter() {
        MappingCassandraConverter mappingCassandraConverter = new MappingCassandraConverter(mappingContext());
        return mappingCassandraConverter;
    }

    @Bean
    public CassandraSessionFactoryBean session() throws Exception {

        CassandraSessionFactoryBean session = new CassandraSessionFactoryBean();
        session.setCluster(cluster().getObject());
        session.setKeyspaceName(env.getProperty("cassandra.keyspace"));
        session.setConverter(converter());
        session.setSchemaAction(SchemaAction.NONE);

        return session;
    }

    @Bean
    public CassandraOperations cassandraTemplate() throws Exception {
        return new CassandraTemplate(session().getObject());
    }

}
