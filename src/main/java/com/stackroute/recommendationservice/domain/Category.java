package com.stackroute.recommendationservice.domain;

import jdk.internal.loader.AbstractClassLoaderValue;
import org.neo4j.ogm.annotation.GeneratedValue;
import org.neo4j.ogm.annotation.Id;
import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Relationship;

import java.util.Set;

@NodeEntity
public class Category {
    @Id
    private String name;

    @Relationship(type = "BELONGS_TO", direction = Relationship.INCOMING)
    private Set<SubCategory> subCategories;

    public Category() {}

    public Category(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
