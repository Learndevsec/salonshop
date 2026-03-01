package com.salonshop.controller;

import com.salonshop.model.Slot;
import com.salonshop.service.SlotService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/slots")
public class SlotController {

    private final SlotService slotService;

    public SlotController(SlotService slotService) {
        this.slotService = slotService;
    }

    @GetMapping("/available")
    public ResponseEntity<List<Slot>> getAvailableSlots() {
        return ResponseEntity.ok(slotService.getAvailableSlots());
    }
}
