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

    @Autowired
    private SlotService slotService;

    @PostMapping
    public ResponseEntity<String> generateSlots(@RequestBody SlotRequest request) {

        int count = slotService.generateSlots(request);

        return ResponseEntity.ok(count + " slots created successfully");
    }
}
