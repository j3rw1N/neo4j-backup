package com.stackroute.recommendationservice.domain;

import org.neo4j.ogm.annotation.*;

@RelationshipEntity(type = "BELONGS_TO")
public class BelongsTo {
    @Id
    @GeneratedValue
    private Long id;

    @StartNode
    private SubCategory subCategory;

    @EndNode
    private Category category;

    public BelongsTo() {}

    public BelongsTo(SubCategory subCategory, Category category) {
        this.subCategory = subCategory;
        this.category = category;
    }

    public Long getId() {
        return id;
    }

    public SubCategory getSubCategory() {
        return subCategory;
    }

    public Category getCategory() {
        return category;
    }
}
