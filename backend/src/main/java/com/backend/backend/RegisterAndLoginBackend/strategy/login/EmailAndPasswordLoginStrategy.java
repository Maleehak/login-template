package com.backend.backend.RegisterAndLoginBackend.strategy.login;

import com.backend.backend.RegisterAndLoginBackend.data.request.login.LoginRequest;
import com.backend.backend.RegisterAndLoginBackend.data.request.login.LoginUserRequest;
import com.backend.backend.RegisterAndLoginBackend.data.response.JwtTokenResponse;
import com.backend.backend.RegisterAndLoginBackend.entity.User;
import com.backend.backend.RegisterAndLoginBackend.repository.UserRepository;
import com.backend.backend.RegisterAndLoginBackend.service.JwtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

@Service
public class EmailAndPasswordLoginStrategy implements LoginStrategy{

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    UserRepository userRepository;

    @Autowired
    JwtService jwtService;

    @Override
    public JwtTokenResponse login(LoginRequest request) {
        LoginUserRequest loginUserRequest = (LoginUserRequest) request;

        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                loginUserRequest.getEmail(), loginUserRequest.getPassword()));
        User user = userRepository.findByEmail(loginUserRequest.getEmail()).orElseThrow();
        return JwtTokenResponse.builder()
                .token(jwtService.generateToken(user))
                .expiresIn(jwtService.getJwtExpiration())
                .build();
    }
}
