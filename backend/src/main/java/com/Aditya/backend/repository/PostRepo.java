package com.Aditya.backend.repository;

import com.Aditya.backend.entity.Post;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface PostRepo extends MongoRepository<Post,String> {
}
