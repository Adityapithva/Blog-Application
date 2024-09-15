package com.Aditya.backend.service;

import com.Aditya.backend.auth.SecurityUtils;
import com.Aditya.backend.entity.Post;
import com.Aditya.backend.entity.User;
import com.Aditya.backend.repository.PostRepo;
import com.Aditya.backend.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class PostService {

    @Autowired
    private PostRepo repo;

    @Autowired
    private UserRepo userRepo;

    public Post createPost(Post post){
        User user = userRepo.findByEmail(SecurityUtils.getEmail());
        post.setCreatedAt(new Date());
        post.setAuthor(user.getUsername());
        return repo.save(post);
    }

    public List<Post> getPostsOfUser(){
        User user = userRepo.findByEmail(SecurityUtils.getEmail());
        List<Post> posts = repo.findByAuthor(user.getUsername());
        if(posts.isEmpty()){
            throw new RuntimeException("No posts found");
        }
        return posts;
    }

    public List<Post> getAllPosts(){
        User user = userRepo.findByEmail(SecurityUtils.getEmail());
        List<Post> posts = repo.findByAuthorNot(user.getUsername());
        if(posts.isEmpty()){
            throw new RuntimeException("No posts found");
        }
        return posts;
    }
}
