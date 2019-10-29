package com.stackroute.recommendationservice.domain;


import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

import lombok.*;
import org.neo4j.ogm.annotation.GeneratedValue;
import org.neo4j.ogm.annotation.Id;
import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Relationship;


@NodeEntity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@ToString
public class User {

	@Id
	private String userName;
	//private String name;
	//private String[] interests;
	//private String gender;
	//private String location;
	//private Date dob;
	@Relationship(type = "PUBLISHED", direction = Relationship.OUTGOING)
	private Set<Post> publishedPosts;

	@Relationship(type = "VIEWED", direction = Relationship.OUTGOING)
	private Set<Post> viewedPosts;

}
