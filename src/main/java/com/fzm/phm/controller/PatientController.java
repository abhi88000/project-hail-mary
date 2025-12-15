package com.fzm.phm.controller;

import com.fzm.phm.dto.PatientRequest;
import com.fzm.phm.dto.PatientResponse;
import com.fzm.phm.service.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/patients")
@CrossOrigin(originPatterns = "*")
public class PatientController {

    @Autowired
    private PatientService patientService;

    @PostMapping
    public ResponseEntity<PatientResponse> create(@RequestBody PatientRequest request) {
        return ResponseEntity.ok(patientService.createPatient(request));
    }
}
