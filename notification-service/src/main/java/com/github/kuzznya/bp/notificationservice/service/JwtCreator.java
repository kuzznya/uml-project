package com.github.kuzznya.bp.notificationservice.service;

import com.github.kuzznya.bp.notificationservice.config.SecurityProperties;
import com.github.kuzznya.bp.notificationservice.model.UserRole;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;
import java.time.Instant;
import java.util.Date;

@Component
@RequiredArgsConstructor
@Slf4j
public class JwtCreator {

    private final SecurityProperties securityProperties;

    public String createToken(long id, UserRole role) {
        return Jwts.builder()
                .setSubject(String.valueOf(id))
                .claim("role", role.name())
                .setIssuedAt(new Date())
                .setExpiration(Date.from(
                        Instant.now().plus(securityProperties.getTokenTtl()))
                )
                .signWith(SignatureAlgorithm.HS512, securityProperties.getSecret().getBytes(StandardCharsets.UTF_8))
                .compact();
    }
}
