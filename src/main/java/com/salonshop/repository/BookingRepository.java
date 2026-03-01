package com.salonshop.repository;

import com.salonshop.model.Booking;
import com.salonshop.model.BookingStatus;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookingRepository extends JpaRepository<Booking, Long> {
    boolean existsBySlotIdAndStatus(Long slotId, BookingStatus status);
}
