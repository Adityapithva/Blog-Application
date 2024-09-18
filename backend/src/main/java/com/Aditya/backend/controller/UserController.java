package com.Aditya.backend.controller;

import com.Aditya.backend.entity.User;
import com.Aditya.backend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService service;

    @GetMapping("/myprofile")
    public ResponseEntity<?> getMyProfile(){
        try{
            User user = service.getProfile();
            return new ResponseEntity<>(user,HttpStatus.OK);
        }catch(Exception e){
            return new ResponseEntity<>("error loading profile details", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
