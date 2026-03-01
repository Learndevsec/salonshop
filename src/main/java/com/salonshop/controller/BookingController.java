package com.salonshop.controller;

import com.salonshop.dto.BookSlotRequest;
import com.salonshop.model.Booking;
import com.salonshop.service.BookingService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/bookings")
public class BookingController {

    private final BookingService bookingService;

    public BookingController(BookingService bookingService) {
        this.bookingService = bookingService;
    }

    @PostMapping
    public ResponseEntity<Booking> bookSlot(
            @AuthenticationPrincipal Jwt jwt,
            @Valid @RequestBody BookSlotRequest request
    ) {
        UUID userId = UUID.fromString(jwt.getSubject());
        Booking booking = bookingService.bookSlot(userId, request.slotId());
        return ResponseEntity.ok(booking);
    }
}
