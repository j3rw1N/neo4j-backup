package com.stackroute.recommendationservice.domain;


import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.neo4j.ogm.annotation.GeneratedValue;
import org.neo4j.ogm.annotation.Id;
import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Relationship;


@NodeEntity
public class User {

	@Id
	private String userName;
	private String name;
	//private String[] interests;
	//private String gender;
	//private String location;
	//private Date dob;

	public User() {}

	public User(String userName, String name) {
		this.userName = userName;
		this.name = name;
	}

	public String getUserName() {
		return userName;
	}

	public String getName() {
		return name;
	}
}
