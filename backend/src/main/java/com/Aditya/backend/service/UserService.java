package com.Aditya.backend.service;

import com.Aditya.backend.auth.*;
import com.Aditya.backend.entity.User;
import com.Aditya.backend.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserRepo repo;

    @Autowired
    private CustomUserDetailsService customUserDetailsService;

    public User registerUser(User user) throws Exception {
        User isExist = repo.findByEmail(user.getEmail());
        if(isExist != null){
            throw new Exception("email already taken");
        }
        User newUser = new User();
        newUser.setUsername(user.getUsername());
        newUser.setPassword(passwordEncoder.encode(user.getPassword()));
        newUser.setEmail(user.getEmail());
        repo.save(newUser);
        return newUser;
    }

    public AuthResponse login(LoginRequest loginRequest){
        Authentication authentication= authenticate(loginRequest.getEmail(),loginRequest.getPassword());
        String token = JwtProvider.generateToken(authentication);
        AuthResponse res = new AuthResponse(token,"Login Success");
        return res;
    }

    private Authentication authenticate(String email, String password) {
        UserDetails userDetails = customUserDetailsService.loadUserByUsername(email);
        System.out.println(userDetails);
        if(userDetails == null){
            throw new BadCredentialsException("invalid username");
        }
        if(!passwordEncoder.matches(password,userDetails.getPassword())){
            throw new BadCredentialsException("invalid username and password");
        }
        return new UsernamePasswordAuthenticationToken(userDetails,null,userDetails.getAuthorities());
    }

    public User getProfile(){
        User user = repo.findByEmail(SecurityUtils.getEmail());
        return user;
    }
}
