package com.Aditya.backend.repository;

import com.Aditya.backend.entity.Like;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface LikeRepo extends MongoRepository<Like,String> {
}
