package com.yourstocks.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.yourstocks.model.User;
import com.yourstocks.request.RegistrationRequest;
import com.yourstocks.service.UserService;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }
    
    @GetMapping("/{userId}") 
    public ResponseEntity<User> getuserById(@PathVariable Long userId) {
        // This line expects userService.getUserById(userId) to return an Optional<User>
        return userService.getUserById(userId) 
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping("/register")
    public User register(@RequestBody RegistrationRequest request) {
        // Assuming RegistrationRequest is a simple class with a 'username' field
        return userService.registerNewUser(request.getUsername());
    }
    
    // Maps to: GET /api/users/search?query=...
    @GetMapping("/search")
    public ResponseEntity<List<User>> searchUsers(@RequestParam(name = "query") String usernameQuery) {
        
        // Pass the cleaned query parameter to your service
        List<User> users = userService.searchUsersByUsername(usernameQuery);
        
        // TEMPORARY DEBUG: Check if the list is correctly populated
        // You should still have the debug logic in your UserService to verify its output.
        
        return ResponseEntity.ok(users);
    }
}