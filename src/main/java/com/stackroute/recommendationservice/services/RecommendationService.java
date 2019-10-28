package com.stackroute.recommendationservice.services;

import java.util.*;

import com.stackroute.recommendationservice.domain.News;
import com.stackroute.recommendationservice.repositories.NewsRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class RecommendationService {

    private final static Logger LOG = LoggerFactory.getLogger(RecommendationService.class);

	private final NewsRepository newsRepository;
	public RecommendationService(NewsRepository newsRepository) {
		this.newsRepository = newsRepository;
	}


	@Transactional(readOnly = true)
	public Collection<News> recommend(String userId) {
		return newsRepository.recommend(userId);
	}

	private Map<String, Object> toD3Format(Collection<News> newsCollection) {
		List<Map<String, Object>> nodes = new ArrayList<>();
		List<Map<String, Object>> rels = new ArrayList<>();
		int i = 0;
		Iterator<News> result = newsCollection.iterator();
		while (result.hasNext()) {

		}
		return map("nodes", nodes, "links", rels);
	}

	private Map<String, Object> map(String key1, Object value1, String key2, Object value2) {
		Map<String, Object> result = new HashMap<String, Object>(2);
		result.put(key1, value1);
		result.put(key2, value2);
		return result;
	}

    @Transactional(readOnly = true)
    public News findByTitle(String title) {
        News result = newsRepository.findByTitle(title);
        return result;
    }

    @Transactional(readOnly = true)
    public Collection<News> findByTitleLike(String title) {
        Collection<News> result = newsRepository.findByTitleLike(title);
        return result;
    }

	@Transactional(readOnly = true)
	public Map<String, Object>  graph(int limit) {
		Collection<News> result = newsRepository.graph(limit);
		return toD3Format(result);
	}
}
