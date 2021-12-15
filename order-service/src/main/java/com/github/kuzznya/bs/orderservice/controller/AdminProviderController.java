package com.github.kuzznya.bs.orderservice.controller;

import com.github.kuzznya.bs.orderservice.entity.ProviderEntity;
import com.github.kuzznya.bs.orderservice.model.ProviderStatus;
import com.github.kuzznya.bs.orderservice.service.ProviderService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Secured("ROLE_ADMIN")
@RequestMapping("/admin/providers")
@RequiredArgsConstructor
public class AdminProviderController {

    private final ProviderService service;

    @GetMapping("/unverified")
    public List<ProviderEntity> getUnverifiedProviders() {
        return service.getUnverifiedProviders();
    }

    @PutMapping("/{id}/status/{status}")
    public ProviderEntity updateStatus(@PathVariable long id, @PathVariable ProviderStatus status) {
        return service.updateStatus(id, status);
    }
}
