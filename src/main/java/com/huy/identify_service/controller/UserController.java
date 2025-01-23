package com.huy.identify_service.controller;

import com.huy.identify_service.dto.request.UserCreationRequest;
import com.huy.identify_service.entity.User;
import com.huy.identify_service.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping
    User createUser(@RequestBody UserCreationRequest request) {
        return userService.createUser(request);
    }

    @GetMapping
    List<User> getAllUsers() {
        return userService.getAllUsers();
    }
}
