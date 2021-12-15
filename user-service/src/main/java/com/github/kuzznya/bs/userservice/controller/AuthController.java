package com.github.kuzznya.bs.userservice.controller;

import com.github.kuzznya.bs.userservice.model.AuthRequest;
import com.github.kuzznya.bs.userservice.model.AuthResponse;
import com.github.kuzznya.bs.userservice.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService service;

    @PostMapping
    public AuthResponse authenticate(@RequestBody AuthRequest request) {
        return service.authenticate(request);
    }
}
