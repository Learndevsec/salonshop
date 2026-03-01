package com.salonshop.controller;

import com.salonshop.dto.AuthRequest;
import com.salonshop.dto.AuthResponse;
import com.salonshop.service.SupabaseAuthService;
import com.salonshop.service.UserService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final SupabaseAuthService supabaseAuthService;
    private final UserService userService;

    public AuthController(SupabaseAuthService supabaseAuthService, UserService userService) {
        this.supabaseAuthService = supabaseAuthService;
        this.userService = userService;
    }

    @PostMapping("/signup")
    public ResponseEntity<Map<String, Object>> signup(@Valid @RequestBody AuthRequest request) {
        Map<String, Object> response = supabaseAuthService.signup(request);
        userService.upsertFromSignupResponse(response);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@Valid @RequestBody AuthRequest request) {
        return ResponseEntity.ok(supabaseAuthService.login(request));
    }
}
