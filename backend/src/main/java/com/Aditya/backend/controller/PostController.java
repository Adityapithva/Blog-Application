package com.Aditya.backend.controller;

import com.Aditya.backend.entity.Post;
import com.Aditya.backend.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/post")
public class PostController {

    @Autowired
    private PostService service;

    //Create a new post
    @PostMapping("/create")
    public ResponseEntity<Post> createPost(@RequestBody Post post){
        Post savedPost = service.createPost(post);
        return new ResponseEntity<>(savedPost, HttpStatus.CREATED);
    }

    //All posts of particular user
    @GetMapping("/myposts")
    public ResponseEntity<?> getPostsOfUser(){
        try{
            List<Post> posts = service.getPostsOfUser();
            return new ResponseEntity<>(posts,HttpStatus.OK);
        }catch(RuntimeException e){
            return new ResponseEntity<>("No posts found",HttpStatus.NO_CONTENT);
        }
    }

    //Get posts for reading
    @GetMapping("/reading")
    public ResponseEntity<?> getAllPosts(){
        try{
            List<Post> posts = service.getAllPosts();
            return new ResponseEntity<>(posts,HttpStatus.OK);
        }catch(RuntimeException e){
            return new ResponseEntity<>("No posts found",HttpStatus.NO_CONTENT);
        }
    }
}
