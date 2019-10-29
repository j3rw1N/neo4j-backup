package com.stackroute.recommendationservice.domain;

import org.neo4j.ogm.annotation.Id;
import org.neo4j.ogm.annotation.NodeEntity;

import java.time.LocalDateTime;

@NodeEntity
public class Location {
    @Id
    private String name;

    public Location() {}

    public Location(String name) {
        this.name = name;
    }
}
