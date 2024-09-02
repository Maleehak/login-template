package com.backend.backend.RegisterAndLoginBackend.strategy.login;

import com.backend.backend.RegisterAndLoginBackend.data.request.login.IdTokenRequest;
import com.backend.backend.RegisterAndLoginBackend.data.request.login.LoginRequest;
import com.backend.backend.RegisterAndLoginBackend.data.response.JwtTokenResponse;
import com.backend.backend.RegisterAndLoginBackend.entity.User;
import com.backend.backend.RegisterAndLoginBackend.exception.UserAlreadyExistsException;
import com.backend.backend.RegisterAndLoginBackend.repository.UserRepository;
import com.backend.backend.RegisterAndLoginBackend.service.JwtService;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdTokenVerifier;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.Optional;

@Service
public class GoogleLoginStrategy implements LoginStrategy {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private JwtService jwtService;

    @Autowired
    private GoogleIdTokenVerifier googleIdTokenVerifier;


    @Override
    public JwtTokenResponse login(LoginRequest request) {
        IdTokenRequest idTokenRequest = (IdTokenRequest) request;

        User user = verifyIDToken(idTokenRequest.getIdToken());
        Optional<User> existingUser = userRepository.findByEmail(user.getEmail());
        if(existingUser.isEmpty()){
            userRepository.save(user);
        }
        return JwtTokenResponse.builder()
                .token(jwtService.generateToken(user))
                .expiresIn(jwtService.getJwtExpiration())
                .build();
    }

    private User verifyIDToken(String idToken) {
        try {
            GoogleIdToken idTokenObj = googleIdTokenVerifier.verify(idToken);
            if (idTokenObj == null) {
                return null;
            }
            GoogleIdToken.Payload payload = idTokenObj.getPayload();
            String firstName = (String) payload.get("given_name");
            String lastName = (String) payload.get("family_name");
            String email = payload.getEmail();

            User user = User.builder()
                    .firstname(firstName)
                    .lastname(lastName)
                    .email(email)
                    .isEnabled(Boolean.TRUE)
                    .isAccountExpired(Boolean.FALSE)
                    .role("USER")
                    .build();

            return user;
        } catch (GeneralSecurityException | IOException e) {
            return null;
        }
    }
}
