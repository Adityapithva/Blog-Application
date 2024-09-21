package com.Aditya.backend.controller;

import com.Aditya.backend.entity.Comments;
import com.Aditya.backend.entity.Post;
import com.Aditya.backend.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/post")
public class PostController {

    @Autowired
    private PostService service;

    //Create a new post
    @PostMapping("/create")
    public ResponseEntity<?> createPost(@RequestBody Post post){
       try{
           Post savedPost = service.createPost(post);
           return new ResponseEntity<>("Post created", HttpStatus.CREATED);
       }catch(Exception e){
           return new ResponseEntity<>("Error creating post",HttpStatus.INTERNAL_SERVER_ERROR);
       }
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

    //Delete a post
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deletePost(@PathVariable String id){
        try{
            Optional<Post> deletedPost = service.deletePost(id);
            return new ResponseEntity<>("Post deleted successful",HttpStatus.OK);
        }catch(Exception e){
            return new ResponseEntity<>("no post found",HttpStatus.NOT_FOUND);
        }
    }

    //Comment in a post
    @PostMapping("/comment/{id}")
    public ResponseEntity<?> addComment(@PathVariable String id, @RequestBody Comments comment){
        try{
            service.addComment(id, comment);
            return new ResponseEntity<>("comment added",HttpStatus.OK);
        }catch(Exception e){
            return new ResponseEntity<>("error adding comment"+e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    //Delete a comment in a post
    @DeleteMapping("/comment/delete/{postId}/{commentId}")
    public ResponseEntity<?> deleteComment(@PathVariable String postId,@PathVariable String commentId){
        try{
            service.deleteComment(postId,commentId);
            return new ResponseEntity<>("comment deleted",HttpStatus.OK);
        }catch(Exception e){
            return new ResponseEntity<>("error deleting post "+e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    //Like to a post
    @GetMapping("/like/{postId}")
    public ResponseEntity<?> likeToPost(@PathVariable String postId){
        try{
            service.likeToPost(postId);
            return new ResponseEntity<>("liked",HttpStatus.OK);
        }catch(Exception e){
            return new ResponseEntity<>("error liking a post "+e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    //Delete a like
    @DeleteMapping("/like/delete/{postId}/{likeId}")
    public ResponseEntity<?> deleteLike(@PathVariable String postId,@PathVariable String likeId){
        try{
            service.deleteLike(postId,likeId);
            return new ResponseEntity<>("unliked",HttpStatus.OK);
        }catch(Exception e){
            return new ResponseEntity<>("error deleting like "+e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    //Save a post
    @GetMapping("/save/{postId}")
    public ResponseEntity<?> savePost(@PathVariable String postId){
        try{
            Post savedPost = service.savePost(postId);
            return new ResponseEntity<>("post saved",HttpStatus.OK);
        }catch(Exception e){
            return new ResponseEntity<>("error saving post",HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    //Unsave a post
    @GetMapping("/unsave/{postId}")
    public ResponseEntity<?> unSavePost(@PathVariable String postId){
        try{
            Post unSavedPost = service.unSavePost(postId);
            return new ResponseEntity<>("post unsaved",HttpStatus.OK);
        }catch(Exception e){
            return new ResponseEntity<>("error unsaving post",HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
