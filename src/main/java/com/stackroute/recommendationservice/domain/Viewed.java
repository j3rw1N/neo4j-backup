package com.stackroute.recommendationservice.domain;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.neo4j.ogm.annotation.*;


@RelationshipEntity(type = "VIEWED")
public class Viewed {

    @Id
    @GeneratedValue
	private Long id;

	@StartNode
	private User person;

	@EndNode
	private News news;

	//private Date viewedDate;

	public Viewed() {
	}

	public Viewed(News news, User person) {
		this.news = news;
		this.person = person;
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
