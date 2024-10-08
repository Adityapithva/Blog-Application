package com.Aditya.backend.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;


@Document(collection = "likes")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Like {
    @Id
    private String id;
    private String username;
}
