package com.fzm.phm.controller;

import com.fzm.phm.dto.TenantRequest;
import com.fzm.phm.dto.TenantResponse;
import com.fzm.phm.service.TenantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/tenants")
@CrossOrigin(origins = "http://localhost:8080")
public class TenantController {

    @Autowired
    private TenantService tenantService;

    @PostMapping
    public ResponseEntity<TenantResponse> create(@RequestBody TenantRequest request) {
        return ResponseEntity.ok(tenantService.createTenant(request));
    }

    @GetMapping("/{slug}")
    public ResponseEntity<TenantResponse> getBySlug(@PathVariable String slug) {
        return ResponseEntity.ok(tenantService.getTenantBySlug(slug));
    }
}
