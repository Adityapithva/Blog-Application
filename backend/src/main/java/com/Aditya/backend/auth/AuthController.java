package com.Aditya.backend.auth;

import com.Aditya.backend.entity.User;
import com.Aditya.backend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public User createUser(@RequestBody User user) throws Exception {
        return userService.registerUser(user);
    }

    @PostMapping("/login")
    public AuthResponse signin(@RequestBody LoginRequest loginRequest){
        return userService.login(loginRequest);
    }
}
