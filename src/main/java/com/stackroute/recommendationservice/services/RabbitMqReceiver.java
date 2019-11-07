package com.stackroute.recommendationservice.services;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.stackroute.recommendationservice.domain.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.HashSet;
import java.util.Random;
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
                    .subCategory(new SubCategory(postDTO.getCategory(), null, null))
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
            String[] locations = {"Bangalore", "Kolkata", "Pondicherri", "Kerala", "Bihar", "UP"};
            Random r = new Random();
            int index = r.nextInt(locations.length);
            user = User.builder()
                    .username(userDTO.getUsername())
                    .location(new Location(locations[index], null, null))
                    .build();
        } catch (IOException e) {
            e.printStackTrace();
        }
        queryService.saveUser(user);
    }

}

