package com.stackroute.recommendationservice.controller;

import java.util.Collection;
import java.util.Map;

import com.stackroute.recommendationservice.domain.News;
import com.stackroute.recommendationservice.domain.User;
import com.stackroute.recommendationservice.services.QueryService;
import com.stackroute.recommendationservice.services.RecommendationService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/")
public class GraphController {

	private final RecommendationService recommendationService;
	private final QueryService queryService;
	
	public GraphController(RecommendationService recommendationService, QueryService queryService) {
		this.recommendationService = recommendationService;
		this.queryService = queryService;
	}

    @GetMapping("/graph")
	public Map<String, Object> graph(@RequestParam(value = "limit",required = false) Integer limit) {
		return recommendationService.graph(limit == null ? 100 : limit);
	}


	@GetMapping("/recommend/{id}")
	public Collection<News> recommend(@PathVariable(value = "id") String userId) {
		Collection<News> result = recommendationService.recommend(userId);
		return result;
	}

	@GetMapping("/news/{id}")
	public Collection<User> views(@PathVariable(value = "id") String id) {
		int videoId = Integer.parseInt(id);
		Collection<User> users = queryService.graph(videoId);
		return users;
	}

}
