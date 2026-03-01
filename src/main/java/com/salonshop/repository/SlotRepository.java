package com.salonshop.repository;

import com.salonshop.model.Slot;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface SlotRepository extends JpaRepository<Slot, Long> {

    @Query("""
            select s from Slot s
            where s.active = true
              and not exists (
                select b from Booking b
                where b.slot.id = s.id and b.status = com.salonshop.model.BookingStatus.BOOKED
              )
            order by s.startTime asc
            """)
    List<Slot> findAvailableSlots();
}
