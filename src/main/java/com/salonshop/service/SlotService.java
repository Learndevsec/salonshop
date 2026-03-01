package com.salonshop.service;

import com.salonshop.model.Slot;
import com.salonshop.repository.SlotRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SlotService {

    private final SlotRepository slotRepository;

    public SlotService(SlotRepository slotRepository) {
        this.slotRepository = slotRepository;
    }

    public List<Slot> getAvailableSlots() {
        return slotRepository.findAvailableSlots();
    }
}
