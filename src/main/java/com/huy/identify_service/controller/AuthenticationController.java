package com.huy.identify_service.controller;

import com.huy.identify_service.constants.StatusCode;
import com.huy.identify_service.dto.request.ApiResponse;
import com.huy.identify_service.dto.request.AuthenticationRequest;
import com.huy.identify_service.dto.response.AuthenticationResponse;
import com.huy.identify_service.service.AuthenticationService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class AuthenticationController {
    AuthenticationService authenticationService;

    @PostMapping("/login")
    ApiResponse<AuthenticationResponse> authenticate(@RequestBody AuthenticationRequest request) {
        boolean result = authenticationService.authenticate(request);
        return ApiResponse.<AuthenticationResponse>builder()
                .code(StatusCode.OK)
                .data(AuthenticationResponse.builder().authenticated(result).build())
                .build();
    }
}
