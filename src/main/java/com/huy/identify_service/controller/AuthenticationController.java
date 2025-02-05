package com.huy.identify_service.controller;

import com.huy.identify_service.constants.StatusCode;
import com.huy.identify_service.dto.request.ApiResponse;
import com.huy.identify_service.dto.request.AuthenticationRequest;
import com.huy.identify_service.dto.request.IntrospectRequest;
import com.huy.identify_service.dto.response.AuthenticationResponse;
import com.huy.identify_service.dto.response.IntrospectResponse;
import com.huy.identify_service.service.AuthenticationService;
import com.nimbusds.jose.JOSEException;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.text.ParseException;

@Slf4j
@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class AuthenticationController {
    AuthenticationService authenticationService;

    @PostMapping("/token")
    ApiResponse<AuthenticationResponse> authenticate(@RequestBody AuthenticationRequest request) {
        var result = authenticationService.authenticate(request);
//        use setter not builder
        return ApiResponse.<AuthenticationResponse>builder()
                .code(StatusCode.OK)
                .message("Authentication successful")
                .data(result)
                .build();
    }

    @PostMapping("/introspect")
    ApiResponse<IntrospectResponse> introspect(@RequestBody IntrospectRequest request) throws ParseException, JOSEException {
        var result = authenticationService.introspect(request);
        return ApiResponse.<IntrospectResponse>builder()
                .code(StatusCode.OK)
                .message("Introspect successful")
                .data(result)
                .build();
    }
}

