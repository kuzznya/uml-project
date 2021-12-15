package com.github.kuzznya.bs.orderservice.controller;

import com.github.kuzznya.bs.orderservice.model.ServiceCreationRequest;
import com.github.kuzznya.bs.orderservice.model.ServiceModel;
import com.github.kuzznya.bs.orderservice.service.ServiceManager;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Secured("ROLE_ADMIN")
@RequestMapping("/admin/services")
@RequiredArgsConstructor
public class AdminServiceController {

    private final ServiceManager manager;

    @PostMapping
    public ServiceModel createService(@RequestBody ServiceCreationRequest request) {
        return manager.createService(request);
    }
}
