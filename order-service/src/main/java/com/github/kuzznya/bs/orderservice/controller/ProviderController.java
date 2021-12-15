package com.github.kuzznya.bs.orderservice.controller;

import com.github.kuzznya.bs.orderservice.entity.ProviderEntity;
import com.github.kuzznya.bs.orderservice.security.UserInfo;
import com.github.kuzznya.bs.orderservice.service.ProviderService;
import io.swagger.v3.oas.annotations.Parameter;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/providers")
@RequiredArgsConstructor
public class ProviderController {

    private final ProviderService service;

    @PostMapping
    public ProviderEntity registerProvider(@RequestBody ProviderEntity provider,
                                           @Parameter(hidden = true) @AuthenticationPrincipal UserInfo userInfo) {
        return service.registerProvider(provider, userInfo);
    }
}
