package com.stackroute.recommendationservice.domain;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.neo4j.ogm.annotation.GeneratedValue;
import org.neo4j.ogm.annotation.Id;
import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Relationship;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@NodeEntity
public class News {

	@Id
	private Long videoID;
	private String title;
	//private String[] interests;
	//private String videoURL;
	//private Date uploadedDate;

	public News() {}

	public News(Long videoID, String title) {
		this.videoID = videoID;
		this.title = title;
	}

	public Long getVideoID() {
		return videoID;
	}

	public String getTitle() {
		return title;
	}
}
