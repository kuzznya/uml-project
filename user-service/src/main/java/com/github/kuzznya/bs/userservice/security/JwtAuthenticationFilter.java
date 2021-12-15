package com.github.kuzznya.bs.userservice.security;

import com.github.kuzznya.bs.userservice.config.SecurityProperties;
import com.github.kuzznya.bs.userservice.model.UserRole;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.List;

@Component
@RequiredArgsConstructor
@Slf4j
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private static final String ROLE_PREFIX = "ROLE_";

    private final SecurityProperties securityProperties;

    @Override
    protected void doFilterInternal(HttpServletRequest req,
                                    HttpServletResponse res,
                                    FilterChain chain) throws ServletException, IOException {
        try {
            readTokenIfProvided(req);
        } catch (Exception e) {
            log.error("Cannot parse token", e);
        }
        chain.doFilter(req, res);
    }

    private void readTokenIfProvided(HttpServletRequest req) {
        String tokenString = req.getHeader("Authorization");
        if (tokenString == null || !tokenString.startsWith("Bearer "))
            return;
        String token = tokenString.substring("Bearer ".length());
        Authentication auth = readToken(token);
        SecurityContextHolder.getContext().setAuthentication(auth);
        log.debug("User {} authorized", auth.getPrincipal());
    }

    private Authentication readToken(String token) {
        Claims claims = Jwts.parser()
                .setSigningKey(securityProperties.getSecret().getBytes(StandardCharsets.UTF_8))
                .parseClaimsJws(token)
                .getBody();
        long id = Long.parseLong(claims.getSubject());
        UserRole role = UserRole.valueOf(claims.get("role", String.class));
        return new UsernamePasswordAuthenticationToken(new UserInfo(id), token, List.of(new SimpleGrantedAuthority(ROLE_PREFIX + role.name())));
    }
}
