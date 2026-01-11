package com.aptitude.aptitude_project.controller;

import com.aptitude.aptitude_project.model.User;
import com.aptitude.aptitude_project.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = "*")
public class AuthController {
    
    @Autowired
    private UserService userService;
    
    @PostMapping("/register")
    public ResponseEntity<Map<String, Object>> register(@RequestBody User user) {
        Map<String, Object> response = new HashMap<>();
        
        User registeredUser = userService.registerUser(user);
        
        response.put("success", true);
        response.put("message", "Registration successful");
        response.put("userId", registeredUser.getId());
        response.put("username", registeredUser.getUsername());
        
        return ResponseEntity.ok(response);
    }
    
    @PostMapping("/register-admin")
    public ResponseEntity<Map<String, Object>> registerAdmin(@RequestBody User user) {
        Map<String, Object> response = new HashMap<>();
        
        if (user.getUsername() == null || user.getEmail() == null || user.getPassword() == null) {
            response.put("success", false);
            response.put("message", "All fields are required");
            return ResponseEntity.badRequest().body(response);
        }
        
        if (userService.existsByUsername(user.getUsername())) {
            response.put("success", false);
            response.put("message", "Username already exists");
            return ResponseEntity.badRequest().body(response);
        }
        
        user.setRole("admin");
        User registeredUser = userService.registerUser(user);
        
        response.put("success", true);
        response.put("message", "Admin user created successfully");
        response.put("userId", registeredUser.getId());
        response.put("username", registeredUser.getUsername());
        
        return ResponseEntity.ok(response);
    }
    
    @PostMapping("/login")
    public ResponseEntity<Map<String, Object>> login(@RequestBody User loginUser) {
        Map<String, Object> response = new HashMap<>();
        
        Optional<User> user = userService.loginUser(
                loginUser.getUsername(), 
                loginUser.getPassword()
        );
        
        if (user.isPresent()) {
            response.put("success", true);
            response.put("message", "Login successful");
            response.put("userId", user.get().getId());
            response.put("username", user.get().getUsername());
            response.put("role", user.get().getRole());
            return ResponseEntity.ok(response);
        } else {
            response.put("success", false);
            response.put("message", "Invalid username or password");
            return ResponseEntity.badRequest().body(response);
        }
    }
}