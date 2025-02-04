package com.huy.identify_service.service;

import com.huy.identify_service.dto.request.AuthenticationRequest;
import com.huy.identify_service.dto.response.AuthenticationResponse;
import com.huy.identify_service.exception.AppException;
import com.huy.identify_service.exception.ErrorCode;
import com.huy.identify_service.repository.UserRepository;
import com.nimbusds.jose.JWSAlgorithm;
import com.nimbusds.jose.JWSHeader;
import com.nimbusds.jose.JWSObject;
import com.nimbusds.jose.Payload;
import com.nimbusds.jose.crypto.MACSigner;
import com.nimbusds.jwt.JWTClaimsSet;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.experimental.NonFinal;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;

@Slf4j
@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class AuthenticationService {
    UserRepository userRepository;

    @NonFinal
    protected static final String SECRET_KEY = "UholDsvsRBLngm3B2O+JNxoPiVfGveka7dcZyUT9iSymM8ZK6yqtINPBpa2DXchK";

    public AuthenticationResponse authenticate(AuthenticationRequest request) {
        var user = userRepository.findByUsername(request.getUsername())
                .orElseThrow(() -> new AppException(ErrorCode.USER_NOT_EXISTED));

        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder(10);
        boolean authenticated = passwordEncoder.matches(request.getPassword(), user.getPassword());

        if (!authenticated) {
            throw new AppException(ErrorCode.UNAUTHENTICATED);
        }

        var token = generateToken(user.getUsername());

        return AuthenticationResponse.builder()
                .token(token)
                .authenticated(true)
                .build();
    }

    private String generateToken(String username) {
//        JWSHeader is the first part of the JWT token
        JWSHeader jwsHeader = new JWSHeader(JWSAlgorithm.HS512);

//        JWTClaimsSet is the second part of the JWT token
        JWTClaimsSet jwtClaimsSet = new JWTClaimsSet.Builder()
//                subject is the username
                .subject(username)
//                issuer is the service that issues the token
                .issuer("identify-service")
//                issueTime is the time the token is issued
                .issueTime(new Date())
//                expirationTime is the time the token is expired
                .expirationTime(new Date(Instant.now().plus(1, ChronoUnit.HOURS).toEpochMilli()))
//                claim is the information of the user
                .claim("role", "user")
                .build();


        Payload payload = new Payload(jwtClaimsSet.toJSONObject());

        JWSObject jwsObject = new JWSObject(jwsHeader, payload);

        try {
            jwsObject.sign(new MACSigner(SECRET_KEY.getBytes()));
//            serialize the token, serialize là chuyển đối tượng thành chuỗi
            return jwsObject.serialize();
        } catch (Exception e) {
            log.error("Error when generating token", e);
            throw new AppException(ErrorCode.UNCATEGORIZED_EXCEPTION);
        }
    }

}