package com.backend.backend.RegisterAndLoginBackend.strategy.login;

import com.backend.backend.RegisterAndLoginBackend.data.request.login.LoginRequest;
import com.backend.backend.RegisterAndLoginBackend.data.response.JwtTokenResponse;

public interface LoginStrategy {
    JwtTokenResponse login(LoginRequest request);
}
