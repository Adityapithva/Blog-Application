package com.Aditya.backend.controller;

import com.Aditya.backend.entity.Post;
import com.Aditya.backend.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/post")
public class PostController {

    @Autowired
    private PostService service;

    @PostMapping("/create")
    public ResponseEntity<Post> createPost(@RequestBody Post post){
        Post savedPost = service.createPost(post);
        return new ResponseEntity<>(savedPost, HttpStatus.CREATED);
    }
}
