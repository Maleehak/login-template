package com.backend.backend.RegisterAndLoginBackend.service;

import com.backend.backend.RegisterAndLoginBackend.data.request.login.IdTokenRequest;
import com.backend.backend.RegisterAndLoginBackend.data.response.JwtTokenResponse;
import com.backend.backend.RegisterAndLoginBackend.data.request.login.LoginUserRequest;
import com.backend.backend.RegisterAndLoginBackend.entity.User;
import com.backend.backend.RegisterAndLoginBackend.data.request.registration.RegisterUserRequest;
import com.backend.backend.RegisterAndLoginBackend.event.NotificationEvent;
import enums.LoginType;
import enums.NotificationType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import static com.backend.backend.RegisterAndLoginBackend.contant.Constants.*;

@Service
public class UserAuthenticationService {
    @Autowired
     private RegistrationService registrationService;

     @Autowired
     private ApplicationEventPublisher eventPublisher;

     @Autowired
     private LoginService loginService;

     public User registerUser(RegisterUserRequest request) {
         User user = registrationService.register(request);
         Map<String, String> additional = new HashMap<>();
         additional.put(FIRST_NAME, user.getFirstname());
         additional.put(LAST_NAME, user.getLastname());
         additional.put(PHONE_NUMBER, user.getPhoneNumber());

          eventPublisher.publishEvent(new NotificationEvent(this,
                  NotificationType.REGISTRATION,
                  user.getEmail(),
                  additional)
                  );

          return user;
     }

     public JwtTokenResponse login(LoginUserRequest request) {
          JwtTokenResponse token = loginService.login(LoginType.EMAIL_PASSWORD, request);
          Optional<User> user = loginService.getByEmail(request.getEmail());

          Map<String, String> additional = new HashMap<>();
          additional.put(FIRST_NAME, user.get().getFirstname());
          additional.put(LAST_NAME, user.get().getLastname());
          additional.put(PHONE_NUMBER, user.get().getPhoneNumber());

          eventPublisher.publishEvent(new NotificationEvent(this,
                  NotificationType.LOGIN,
                  request.getEmail(),
                  additional));

          return token;
     }

     public JwtTokenResponse loginWithGoogle(IdTokenRequest request) {
          JwtTokenResponse token = loginService.login(LoginType.GOOGLE, request);
          return token;
     }

}
