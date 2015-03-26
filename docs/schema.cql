CREATE KEYSPACE IF NOT EXISTS demo WITH replication = {'class': 'SimpleStrategy', 'replication_factor': '1'}  AND durable_writes = true;

USE demo;

CREATE TABLE IF NOT EXISTS users (

    user_id varchar,
    first_name varchar,
    last_name varchar,
    password varchar,
    PRIMARY KEY(user_id)

);

CREATE TABLE IF NOT EXISTS events_by_day (
    eid int,
    day int,
    event_id uuid,
    tag varchar,
    value varchar,
    PRIMARY KEY((eid, day), event_id)
) WITH CLUSTERING ORDER BY (event_id DESC);

CREATE TABLE IF NOT EXISTS events_by_tag (
    eid int,
    event_id uuid,
    tag varchar,
    value varchar,
    PRIMARY KEY((eid, tag), event_id)
) WITH CLUSTERING ORDER BY (event_id DESC);