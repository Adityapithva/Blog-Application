package com.Aditya.backend.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Data
@NoArgsConstructor@AllArgsConstructor
@Document(collection = "user")
public class User {
    @Id
    private String id;
    private String email;
    private String username;
    private String password;
    @DBRef
    private List<Post> posts;
}
