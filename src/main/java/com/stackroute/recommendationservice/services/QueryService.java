package com.stackroute.recommendationservice.services;

import com.stackroute.recommendationservice.domain.Post;
import com.stackroute.recommendationservice.domain.User;
import com.stackroute.recommendationservice.repositories.NewsRepository;
import com.stackroute.recommendationservice.repositories.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;

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
    public Collection<Post> findByTitleLike(String title) {
        return newsRepository.findByTitleLike(title);
    }

    @Transactional
    public User getUser(String userName) {
        return userRepository.findByUserName(userName);
    }

    @Transactional
    public void saveUser(User user) {
        userRepository.save(user);
    }
}
