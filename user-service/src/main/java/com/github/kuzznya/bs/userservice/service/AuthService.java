package com.github.kuzznya.bs.userservice.service;

import com.github.kuzznya.bs.userservice.exception.ForbiddenException;
import com.github.kuzznya.bs.userservice.model.AuthRequest;
import com.github.kuzznya.bs.userservice.model.AuthResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class AuthService {

    private final JwtCreator jwtCreator;
    private final PasswordEncoder encoder;
    private final UserService userService;

    public AuthResponse authenticate(AuthRequest request) {
        return userService.findEntityByEmail(request.getEmail())
                .filter(user -> encoder.matches(request.getPassword(), user.getPassword()))
                .map(user -> new AuthResponse(jwtCreator.createToken(user.getId(), user.getRole())))
                .orElseThrow(() -> new ForbiddenException("Invalid email or password"));
    }
}
