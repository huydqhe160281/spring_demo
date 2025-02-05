package com.huy.identify_service.controller;

import com.huy.identify_service.constants.StatusCode;
import com.huy.identify_service.dto.request.ApiResponse;
import com.huy.identify_service.dto.request.UserCreationRequest;
import com.huy.identify_service.dto.request.UserUpdateRequest;
import com.huy.identify_service.dto.response.UserResponse;
import com.huy.identify_service.entity.User;
import com.huy.identify_service.service.UserService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/users")
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping
    ApiResponse<User> createUser(@RequestBody @Valid UserCreationRequest request) {
        return ApiResponse.<User>builder()
                .code(StatusCode.CREATED)
                .message("Create user successfully")
                .data(userService.createUser(request))
                .build();
    }

    @GetMapping
    ApiResponse<List<UserResponse>> getAllUsers() {
        var authentication = SecurityContextHolder.getContext().getAuthentication();

//        log.info("User: {}", authentication.getName());
//        authentication.getAuthorities().forEach(authority -> log.info("Authority: {}", authority.getAuthority()));

        return ApiResponse.<List<UserResponse>>builder()
                .code(StatusCode.OK)
                .message("Get all users successfully")
                .data(userService.getAllUsers())
                .build();
    }

    @GetMapping("/{userId}")
    ApiResponse<UserResponse> getUserById(@PathVariable String userId) {
        return ApiResponse.<UserResponse>builder()
                .code(StatusCode.OK)
                .message("Get user by id successfully")
                .data(userService.getUserById(userId))
                .build();
    }

    @GetMapping("/me")
    ApiResponse<UserResponse> getCurrentUser() {
        return ApiResponse.<UserResponse>builder()
                .code(StatusCode.OK)
                .message("Get my info successfully")
                .data(userService.getMyInfo())
                .build();
    }

    @PutMapping("/{userId}")
    ApiResponse<UserResponse> updateUser(@PathVariable String userId, @RequestBody @Valid UserUpdateRequest request) {
        return ApiResponse.<UserResponse>builder()
                .code(StatusCode.OK)
                .message("Update user successfully")
                .data(userService.updateUser(userId, request))
                .build();
    }

    @DeleteMapping("/{userId}")
    ApiResponse<String> deleteUser(@PathVariable String userId) {
        return ApiResponse.<String>builder()
                .code(StatusCode.OK)
                .message("Delete user successfully")
                .build();
    }

}
