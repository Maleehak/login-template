package com.backend.backend.RegisterAndLoginBackend.controller;

import com.backend.backend.RegisterAndLoginBackend.data.request.login.IdTokenRequest;
import com.backend.backend.RegisterAndLoginBackend.data.request.registration.RegisterUserRequest;
import com.backend.backend.RegisterAndLoginBackend.data.response.JwtTokenResponse;
import com.backend.backend.RegisterAndLoginBackend.entity.User;
import com.backend.backend.RegisterAndLoginBackend.service.JwtService;
import com.backend.backend.RegisterAndLoginBackend.service.UserAuthenticationService;
import com.backend.backend.RegisterAndLoginBackend.data.request.login.LoginUserRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthenticationController {

    @Autowired
    UserAuthenticationService userAuthenticationService;

    @Autowired
    JwtService jwtService;

    @PostMapping("/register")
    public String register(@Valid @RequestBody RegisterUserRequest request) {
        User user = userAuthenticationService.registerUser(request);
        return "Registered successfully";
    }

    @PostMapping("/login")
    public ResponseEntity<JwtTokenResponse> login(@RequestBody LoginUserRequest request) {
        return ResponseEntity.ok(userAuthenticationService.login(request));
    }

    @PostMapping("/login/google")
    public ResponseEntity<JwtTokenResponse> loginWithGoogle(@RequestBody IdTokenRequest request) {
        return ResponseEntity.ok(userAuthenticationService.loginWithGoogle(request));
    }
}
