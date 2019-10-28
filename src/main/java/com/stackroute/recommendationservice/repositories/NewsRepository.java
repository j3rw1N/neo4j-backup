package com.stackroute.recommendationservice.repositories;

import java.util.Collection;

import com.stackroute.recommendationservice.domain.News;
import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;


@RepositoryRestResource(collectionResourceRel = "movies", path = "movies")
public interface NewsRepository extends Neo4jRepository<News, Long> {

	News findByTitle(@Param("title") String title);

	Collection<News> findByTitleLike(@Param("title") String title);

    @Query("MATCH (m:Movie)<-[r:ACTED_IN]-(a:Person) RETURN m,r,a LIMIT {limit}")
	Collection<News> graph(@Param("limit") int limit);

	@Query("match (u:User) where u.userName={userName} \n" +
			"\tmatch (u)-[:Viewed]->(n:News) with collect(n) as viewedNews \n" +
			"\t\tmatch (publisher:User)-[:Published]->(n) \n" +
			"\t\t\tmatch (publisher)-[:Published]->(recommend) \n" +
			"\t\t\t\tWHERE not recommend in viewedNews\n" +
			"\t\t\t\t\treturn DISTINCT recommend")
	Collection<News> recommend(@Param("userName") String userName);
}