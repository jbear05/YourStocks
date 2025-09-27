package com.yourstocks.controller;

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

    @PostMapping("/register")
    public User register(@RequestBody RegistrationRequest request) {
        // Assuming RegistrationRequest is a simple class with a 'username' field
        return userService.registerNewUser(request.getUsername());
    }
}