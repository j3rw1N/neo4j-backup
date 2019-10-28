package com.stackroute.recommendationservice.domain;

import org.neo4j.ogm.annotation.EndNode;
import org.neo4j.ogm.annotation.GeneratedValue;
import org.neo4j.ogm.annotation.Id;
import org.neo4j.ogm.annotation.StartNode;

import java.util.Date;

public class Published {
    @Id
    @GeneratedValue
    private Long id;

    @StartNode
    private User person;

    @EndNode
    private News news;

    private Date publishedDate;

    public Published() {
    }

    public Published(News news, User actor) {
        this.news = news;
        this.person = actor;
    }

    public Long getId() {
        return id;
    }

    public User getPerson() {
        return person;
    }

    public News getNews() {
        return news;
    }
}
