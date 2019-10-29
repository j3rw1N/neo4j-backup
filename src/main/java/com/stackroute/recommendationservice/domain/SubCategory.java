package com.stackroute.recommendationservice.domain;

import org.neo4j.ogm.annotation.GeneratedValue;
import org.neo4j.ogm.annotation.Id;
import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Relationship;

import java.util.Set;

@NodeEntity
public class SubCategory {
    @Id
    private String name;

    @Relationship(type = "BELONGS_TO", direction = Relationship.OUTGOING)
    private Set<Category> categories;

    public SubCategory() {}

    public SubCategory(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
