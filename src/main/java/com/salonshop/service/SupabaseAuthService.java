package com.salonshop.service;

import com.salonshop.dto.AuthRequest;
import com.salonshop.dto.AuthResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import java.util.Map;

@Service
public class SupabaseAuthService {

    private final RestClient restClient;

    public SupabaseAuthService(
            @Value("${supabase.url}") String supabaseUrl,
            @Value("${supabase.anon-key}") String anonKey
    ) {
        this.restClient = RestClient.builder()
                .baseUrl(supabaseUrl + "/auth/v1")
                .defaultHeader("apikey", anonKey)
                .defaultHeader("Content-Type", MediaType.APPLICATION_JSON_VALUE)
                .build();
    }

    public Map<String, Object> signup(AuthRequest request) {
        return restClient.post()
                .uri("/signup")
                .body(Map.of("email", request.email(), "password", request.password()))
                .retrieve()
                .body(Map.class);
    }

    public AuthResponse login(AuthRequest request) {
        return restClient.post()
                .uri(uriBuilder -> uriBuilder
                        .path("/token")
                        .queryParam("grant_type", "password")
                        .build())
                .body(Map.of("email", request.email(), "password", request.password()))
                .retrieve()
                .body(AuthResponse.class);
    }
}
