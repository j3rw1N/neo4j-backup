package com.stackroute.recommendationservice.domain;

import org.neo4j.ogm.annotation.*;


@RelationshipEntity(type = "VIEWED")
public class Viewed {

    @Id
    @GeneratedValue
	private Long id;

	@StartNode
	private User person;

	@EndNode
	private Post news;

	//private Date viewedDate;

	public Viewed() {
	}

	public Viewed(Post news, User person) {
		this.news = news;
		this.person = person;
	}

	public Long getId() {
	    return id;
	}

	public User getPerson() {
	    return person;
	}

	public Post getNews() {
	    return news;
	}

}
