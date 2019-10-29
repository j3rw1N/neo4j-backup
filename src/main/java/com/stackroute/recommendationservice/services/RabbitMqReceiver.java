package com.stackroute.recommendationservice.services;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.stackroute.recommendationservice.domain.*;
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
            System.out.println(message);
            postDTO = new ObjectMapper().readValue(message, PostDTO.class);
            System.out.println("after mapped");
            Post post = Post.builder()
                    .videoID(postDTO.getId())
                    .title(postDTO.getTitle())
                    .videoURL(postDTO.getVideoUrl())
                    .category(postDTO.getCategory())
                    .location(new Location(postDTO.getLocation(), null, null))
                    .tags(postDTO.getTags())
                    .build();
            System.out.println(postDTO.toString());
            User user = queryService.getUser(postDTO.getPostedBy().getUsername());
            if(user == null) {
                return;
            }
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
        UserDTO userDTO = null;
        User user = null;
        try {
            System.out.println(message);
            userDTO = new ObjectMapper().readValue(message, UserDTO.class);
            user = User.builder()
                    .username(userDTO.getUsername())
                    .build();
        } catch (IOException e) {
            e.printStackTrace();
        }
        queryService.saveUser(user);
    }

}

