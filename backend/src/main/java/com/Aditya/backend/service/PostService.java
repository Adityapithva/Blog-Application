package com.Aditya.backend.service;

import com.Aditya.backend.entity.Post;
import com.Aditya.backend.entity.User;
import com.Aditya.backend.repository.PostRepo;
import com.Aditya.backend.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class PostService {

    @Autowired
    private PostRepo repo;

    @Autowired
    private UserRepo userRepo;

    public Post createPost(Post post){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = userRepo.findByEmail(authentication.getName());
        post.setCreatedAt(new Date());
        post.setAuthor(user.getUsername());
        return repo.save(post);
    }
}
