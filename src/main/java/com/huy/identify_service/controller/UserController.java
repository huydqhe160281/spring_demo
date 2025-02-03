package com.huy.identify_service.controller;

import com.huy.identify_service.constants.StatusCode;
import com.huy.identify_service.dto.request.ApiResponse;
import com.huy.identify_service.dto.request.UserCreationRequest;
import com.huy.identify_service.dto.request.UserUpdateRequest;
import com.huy.identify_service.dto.response.UserResponse;
import com.huy.identify_service.entity.User;
import com.huy.identify_service.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping
        // @Valid to validate request body
    ApiResponse<User> createUser(@RequestBody @Valid UserCreationRequest request) {
        ApiResponse<User> apiResponse = new ApiResponse<>(StatusCode.CREATED, "Create user successfully", userService.createUser(request));
        return apiResponse;
    }

    @GetMapping
    ApiResponse<List<User>> getAllUsers() {
        return new ApiResponse<>(StatusCode.OK, "Get all users successfully", userService.getAllUsers());
    }

    @GetMapping("/{userId}")
    ApiResponse<UserResponse> getUserById(@PathVariable String userId) {
        return new ApiResponse<>(StatusCode.OK, "Get user successfully", userService.getUserById(userId));
    }

    @PutMapping("/{userId}")
    ApiResponse<UserResponse> updateUser(@PathVariable String userId, @RequestBody @Valid UserUpdateRequest request) {
        ApiResponse<UserResponse> apiResponse = new ApiResponse<>(StatusCode.OK, "Update user successfully", userService.updateUser(userId, request));
        return apiResponse;
    }

    @DeleteMapping("/{userId}")
    ApiResponse<String> deleteUser(@PathVariable String userId) {
        userService.deleteUser(userId);
        return new ApiResponse<>(StatusCode.OK, "Delete user successfully", null);
    }

}
