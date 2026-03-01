package com.salonshop.service;

import com.salonshop.model.Role;
import com.salonshop.model.User;
import com.salonshop.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.UUID;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User upsertFromSignupResponse(Map<String, Object> signupResponse) {
        Object userObj = signupResponse.get("user");
        if (!(userObj instanceof Map<?, ?> userMap)) {
            throw new IllegalArgumentException("Supabase signup response missing user payload");
        }

        UUID supabaseUserId = UUID.fromString(String.valueOf(userMap.get("id")));
        String email = String.valueOf(userMap.get("email"));

        User user = userRepository.findBySupabaseUserId(supabaseUserId).orElseGet(User::new);
        user.setSupabaseUserId(supabaseUserId);
        user.setEmail(email);
        if (user.getRole() == null) {
            user.setRole(Role.USER);
        }

        return userRepository.save(user);
    }
}
