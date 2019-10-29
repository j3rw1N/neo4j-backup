package com.stackroute.recommendationservice.services;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.stackroute.recommendationservice.domain.Post;
import com.stackroute.recommendationservice.domain.PostDTO;
import com.stackroute.recommendationservice.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class RabbitMqReceiver {

    @Autowired
    QueryService queryService;

    public void receivePosts(String message) {
        PostDTO postDTO;
        try {
            postDTO = new ObjectMapper().readValue(message, PostDTO.class);
            Post post = Post.builder()
                    .videoID(postDTO.getId())
                    .title(postDTO.getTitle())
                    .videoURL(postDTO.getVideoUrl())
                    .tags(postDTO.getTags())
                    .build();
            System.out.println(postDTO.toString());
            User user = queryService.getUser(postDTO.getPostedBy().getUserName());
            if(user.getPublishedPosts()!=null) {
                user.getPublishedPosts().add(post);
            } else {
                Set<Post> posts = new HashSet<>();
                posts.add(post);
                user.setPublishedPosts(posts);
            }
            queryService.saveUser(user);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void receiveUsers(String message) {
        User user = null;
        try {
            System.out.println(message);
            user = new ObjectMapper().readValue(message, User.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        queryService.saveUser(user);
    }

}

