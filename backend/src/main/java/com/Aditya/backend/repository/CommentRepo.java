package com.Aditya.backend.repository;

import com.Aditya.backend.entity.Comments;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface CommentRepo extends MongoRepository<Comments,String> {
}
