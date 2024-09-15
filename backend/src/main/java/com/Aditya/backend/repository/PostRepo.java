package com.Aditya.backend.repository;

import com.Aditya.backend.entity.Post;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface PostRepo extends MongoRepository<Post,String> {
    List<Post> findByAuthor(String author);
    List<Post> findByAuthorNot(String author);
}
