package com.fzm.phm.controller;

import com.fzm.phm.dto.SlotRequest;
import com.fzm.phm.service.SlotService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/slots")
@CrossOrigin(originPatterns = "*")
public class SlotController {

    private final SlotService slotService;

    public SlotController(SlotService slotService) {
        this.slotService = slotService;
    }

    @PostMapping
    public ResponseEntity<?> createSlots(@RequestBody SlotRequest request) {
        return ResponseEntity.ok(slotService.createSlots(request));
    }
}

