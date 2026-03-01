package com.salonshop.repository;

import com.salonshop.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findBySupabaseUserId(UUID supabaseUserId);
    Optional<User> findByEmail(String email);
}
