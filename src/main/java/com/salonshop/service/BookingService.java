package com.salonshop.service;

import com.salonshop.model.Booking;
import com.salonshop.model.BookingStatus;
import com.salonshop.model.Slot;
import com.salonshop.model.User;
import com.salonshop.repository.BookingRepository;
import com.salonshop.repository.SlotRepository;
import com.salonshop.repository.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
public class BookingService {

    private final BookingRepository bookingRepository;
    private final SlotRepository slotRepository;
    private final UserRepository userRepository;

    public BookingService(BookingRepository bookingRepository, SlotRepository slotRepository, UserRepository userRepository) {
        this.bookingRepository = bookingRepository;
        this.slotRepository = slotRepository;
        this.userRepository = userRepository;
    }

    @Transactional
    public Booking bookSlot(UUID supabaseUserId, Long slotId) {
        User user = userRepository.findBySupabaseUserId(supabaseUserId)
                .orElseThrow(() -> new EntityNotFoundException("User not found"));

        Slot slot = slotRepository.findById(slotId)
                .orElseThrow(() -> new EntityNotFoundException("Slot not found"));

        if (bookingRepository.existsBySlotIdAndStatus(slotId, BookingStatus.BOOKED)) {
            throw new IllegalStateException("Slot already booked");
        }

        Booking booking = new Booking();
        booking.setUser(user);
        booking.setSlot(slot);
        booking.setStatus(BookingStatus.BOOKED);

        try {
            return bookingRepository.save(booking);
        } catch (DataIntegrityViolationException ex) {
            throw new IllegalStateException("Slot already booked");
        }
    }

    public List<Booking> getAllBookings() {
        return bookingRepository.findAll();
    }
}
