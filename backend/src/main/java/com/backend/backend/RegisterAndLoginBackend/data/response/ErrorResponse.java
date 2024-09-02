package com.backend.backend.RegisterAndLoginBackend.data.response;

import lombok.Builder;

@Builder
public record ErrorResponse(
        String message
){ }
