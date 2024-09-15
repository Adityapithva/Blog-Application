package com.Aditya.backend.repository;

import com.Aditya.backend.entity.User;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserRepo extends MongoRepository<User,String> {
    User findByEmail(String email);
}
