package com.sneha.usersignupsignin.controller;

import com.sneha.usersignupsignin.dto.UserResponseDTO;
import com.sneha.usersignupsignin.entity.User;
import com.sneha.usersignupsignin.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class HomeController {

    @Autowired
    private UserService userService;
    //Testing Purpose
    @GetMapping("/")
    public String home(){
        return "Welcome to spring boot registration process";
    }


    @PostMapping("/saveUser")
    public ResponseEntity<String> saveUser(@RequestBody User user) {
        User savedUser = userService.saveUser(user);
        if (savedUser != null) {
            return ResponseEntity.status(HttpStatus.CREATED).body("User registered successfully");
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("User registration failed");
        }
    }
    @PostMapping("/login1")
    public ResponseEntity<?> login1(@RequestParam String username, @RequestParam String password) {
        User user = userService.login1(username, password);
        if (user != null) {
            UserResponseDTO responseDTO = new UserResponseDTO(user.getUserId(), user.getKey());
            return ResponseEntity.ok(responseDTO);
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Authentication failed");
        }
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestParam Integer userId, @RequestParam String key) {
        User user = userService.login(userId, key);
        if (user != null) {
            return ResponseEntity.ok(user);
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Authentication failed");
        }
    }
}
