package com.github.kuzznya.bs.orderservice.controller;

import com.github.kuzznya.bs.orderservice.entity.projection.ServiceInfo;
import com.github.kuzznya.bs.orderservice.exception.NotFoundException;
import com.github.kuzznya.bs.orderservice.model.ServiceModel;
import com.github.kuzznya.bs.orderservice.service.ServiceManager;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/services")
@RequiredArgsConstructor
public class ServiceController {

    private final ServiceManager manager;

    @GetMapping
    public List<ServiceInfo> getServices() {
        return manager.getServices();
    }

    @GetMapping("/{id}")
    public ServiceModel getService(@PathVariable("id") long id) {
        return manager.getService(id).orElseThrow(() -> new NotFoundException("Service with id " + id + " not found"));
    }
}
