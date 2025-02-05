package com.huy.identify_service.service;

import com.huy.identify_service.dto.request.UserCreationRequest;
import com.huy.identify_service.dto.request.UserUpdateRequest;
import com.huy.identify_service.dto.response.UserResponse;
import com.huy.identify_service.entity.User;
import com.huy.identify_service.enums.Role;
import com.huy.identify_service.exception.AppException;
import com.huy.identify_service.exception.ErrorCode;
import com.huy.identify_service.mapper.UserMapper;
import com.huy.identify_service.repository.UserRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
// @RequiredArgsConstructor to generate constructor with all final fields
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class UserService {
    UserRepository userRepository;
    UserMapper userMapper;
    PasswordEncoder passwordEncoder;

    public User createUser(UserCreationRequest request) {

        if (userRepository.existsByUsername(request.getUsername())) {
//           bắt lỗi khác những lỗi đã default sẵn
//            throw new RuntimeException("Username existed");
            throw new AppException(ErrorCode.USERNAME_EXISTED);
        }
//        UserCreationRequest userCreationRequest = UserCreationRequest.builder()
//                .username(request.getUsername())
//                .password(request.getPassword())
//                .firstName(request.getFirstName())
//                .lastName(request.getLastName())
//                .dob(request.getDob())
//                .build();
//        User user = new User();
//        user.setUsername(request.getUsername());
//        user.setPassword(request.getPassword());
//        user.setFirstName(request.getFirstName());
//        user.setLastName(request.getLastName());
//        user.setDob(request.getDob());
        User user = userMapper.toUser(request);
        user.setPassword(passwordEncoder.encode(user.getPassword()));

        HashSet<String> roles = new HashSet<>();
        roles.add(Role.USER.name());
        user.setRoles(roles);

        return userRepository.save(user);
    }

    @PreAuthorize("hasRole('ADMIN')")
    public List<UserResponse> getAllUsers() {
        return userRepository.findAll().stream().map(userMapper::toUserResponse).toList();
    }

    @PostAuthorize("returnObject.username == authentication.name")
    public UserResponse getUserById(String userId) {
        log.info("Get user by id: {}", userId);
        return userMapper.toUserResponse(userRepository.findById(userId).orElseThrow(() -> new AppException(ErrorCode.USER_NOT_FOUND)));
    }

    public UserResponse getMyInfo() {
        var context = SecurityContextHolder.getContext();
        String name = context.getAuthentication().getName();
        User user = userRepository.findByUsername(name).orElseThrow(() -> new AppException(ErrorCode.USER_NOT_FOUND));
        return userMapper.toUserResponse(user);
    }

    public UserResponse updateUser(String userId, UserUpdateRequest request) {
//      check if user exists
        User user = userRepository.findById(userId).orElseThrow(() -> new AppException(ErrorCode.USER_NOT_FOUND));

        userMapper.updateUser(user, request);
        return userMapper.toUserResponse(userRepository.save(user));
    }

    public void deleteUser(String userId) {
        userRepository.deleteById(userId);
    }
}
