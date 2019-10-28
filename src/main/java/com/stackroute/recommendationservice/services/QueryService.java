package com.stackroute.recommendationservice.services;

import com.stackroute.recommendationservice.domain.News;
import com.stackroute.recommendationservice.domain.User;
import com.stackroute.recommendationservice.repositories.NewsRepository;
import com.stackroute.recommendationservice.repositories.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.Iterator;
import java.util.Map;

@Service
public class QueryService {
    private final NewsRepository newsRepository;
    private final UserRepository userRepository;
    public QueryService(NewsRepository newsRepository, UserRepository userRepository) {
        this.newsRepository = newsRepository;
        this.userRepository = userRepository;
    }

    @Transactional(readOnly = true)
    public Collection<User> graph(int userId) {
        Collection<User> result = userRepository.graph(userId);
        return result;
    }


    @Transactional(readOnly = true)
    public Collection<News> findByTitleLike(String title) {
        Collection<News> result = newsRepository.findByTitleLike(title);
        Iterator<News> iterator = result.iterator();
        while (iterator.hasNext()) {
            News news = iterator.next();
            System.out.println(news.getTitle());
        }
        return result;
    }
}
