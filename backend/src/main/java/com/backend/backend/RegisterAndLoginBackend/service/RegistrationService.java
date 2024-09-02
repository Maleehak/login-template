package com.backend.backend.RegisterAndLoginBackend.service;

import com.backend.backend.RegisterAndLoginBackend.data.request.registration.RegisterUserRequest;
import com.backend.backend.RegisterAndLoginBackend.entity.User;
import com.backend.backend.RegisterAndLoginBackend.exception.UserAlreadyExistsException;
import com.backend.backend.RegisterAndLoginBackend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class RegistrationService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public User register(RegisterUserRequest request) {
        Optional<User> existingUser = userRepository.findByEmail(request.getEmail());
        if(existingUser.isPresent()){
            throw new UserAlreadyExistsException("User with email " + request.getEmail() +" already exists");
        }

        User user = User.builder()
                .email(request.getEmail())
                .phoneNumber(request.getPhoneNumber())
                .password(passwordEncoder.encode(request.getPassword()))
                .isEnabled(Boolean.TRUE)
                .firstname(request.getFirstName())
                .lastname(request.getLastName())
                .isAccountExpired(Boolean.FALSE)
                .role(request.getRole() == null ? "USER": request.getRole())
                .build();

        return userRepository.save(user);
    }
}
