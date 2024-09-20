package com.Aditya.backend.service;

import com.Aditya.backend.auth.SecurityUtils;
import com.Aditya.backend.entity.Comments;
import com.Aditya.backend.entity.Post;
import com.Aditya.backend.entity.User;
import com.Aditya.backend.repository.CommentRepo;
import com.Aditya.backend.repository.PostRepo;
import com.Aditya.backend.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class PostService {

    @Autowired
    private PostRepo repo;

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private CommentRepo commentRepo;

    public Post createPost(Post post) {
        User user = userRepo.findByEmail(SecurityUtils.getEmail());

        Post savedPost = new Post();
        savedPost.setCreatedAt(new Date());

            savedPost.setAuthor(user.getUsername());


        savedPost.setTags(post.getTags());
        savedPost.setTitle(post.getTitle());
        savedPost.setContent(post.getContent());

        // Save the post and return the saved entity from repo.save
        savedPost = repo.save(savedPost);

        // Ensure user's post list is initialized
        if (user.getPosts() == null) {
            user.setPosts(new ArrayList<>());
        }

        user.getPosts().add(savedPost);
        userRepo.save(user);

        return savedPost;
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

    public Optional<Post> deletePost(String id){
        Optional<Post> post = repo.findById(id);
        if(!post.isPresent()){
            throw new RuntimeException("post not found");
        }
        repo.deleteById(id);
        User user = userRepo.findByEmail(SecurityUtils.getEmail());
        List<Post> posts = user.getPosts();
        posts.remove(post);
        user.setPosts(posts);
        userRepo.save(user);
        return post;
    }

    public Comments addComment(String postId,Comments comment){
        Post post = repo.findById(postId).orElse(null);
        comment.setPostId(postId);
        User user = userRepo.findByEmail(SecurityUtils.getEmail());
        comment.setAuthor(user.getUsername());
        Comments savedComment = commentRepo.save(comment);
        if(post.getComments() == null){
            post.setComments(new ArrayList<>());
        }
        post.getComments().add(savedComment);
        repo.save(post);
        return savedComment;
    }

    public Comments deleteComment(String postId,String commentId){
        Post post = repo.findById(postId).orElse(null);
        Comments comment = commentRepo.findById(commentId).orElse(null);
        post.getComments().remove(comment);
        repo.save(post);
        commentRepo.delete(comment);
        return comment;
    }
}
