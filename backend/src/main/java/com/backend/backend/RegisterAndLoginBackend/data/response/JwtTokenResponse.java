package com.backend.backend.RegisterAndLoginBackend.data.response;

import lombok.Builder;

@Builder
public record JwtTokenResponse(
        String token,
        Long expiresIn
){ }
