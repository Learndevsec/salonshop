package com.salonshop.dto;

public record AuthResponse(
        String access_token,
        String refresh_token,
        String token_type,
        Long expires_in
) {
}
