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

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@NodeEntity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@ToString
public class Post {
	@Id
	private Long videoID;
	private String title;
	private String videoURL;
	private String category;
	private List<String> tags;
	@Relationship(type = "NEWS_LOCATION", direction = Relationship.OUTGOING)
	private Location location;
}
