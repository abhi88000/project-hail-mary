package com.fzm.phm.service;

import com.fzm.phm.dto.TenantRequest;
import com.fzm.phm.dto.TenantResponse;
import com.fzm.phm.model.Tenant;
import com.fzm.phm.repository.TenantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TenantService {

    @Autowired
    private TenantRepository tenantRepository;

    public TenantResponse createTenant(TenantRequest request) {

        Tenant tenant = new Tenant();
        tenant.setName(request.getName());
        tenant.setSlug(request.getSlug());
        tenant.setIndustry(request.getIndustry());
        tenant.setPlan(request.getPlan());
        tenant.setTimezone(request.getTimezone());

        Tenant saved = tenantRepository.save(tenant);

        return mapToResponse(saved);
    }

    public TenantResponse getBySlug(String slug) {

        Tenant tenant = tenantRepository.findBySlug(slug)
                .orElseThrow(() -> new RuntimeException("Tenant not found"));

        return mapToResponse(tenant);
    }

    // legacy/alternate method name kept for compatibility with controllers
    public TenantResponse getTenantBySlug(String slug) {
        return getBySlug(slug);
    }

    private TenantResponse mapToResponse(Tenant t) {
        TenantResponse res = new TenantResponse();

        res.setId(t.getId());
        res.setName(t.getName());
        res.setSlug(t.getSlug());
        res.setIndustry(t.getIndustry());
        res.setPlan(t.getPlan());
        res.setTimezone(t.getTimezone());

        return res;
    }
}
