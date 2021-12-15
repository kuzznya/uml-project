package com.github.kuzznya.bp.notificationservice.client;

import com.github.kuzznya.bp.notificationservice.config.ClientProperties;
import com.github.kuzznya.bp.notificationservice.model.AppUser;
import com.github.kuzznya.bp.notificationservice.model.UserRole;
import com.github.kuzznya.bp.notificationservice.service.JwtCreator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.util.MultiValueMapAdapter;
import org.springframework.web.client.RestTemplate;

import java.time.Duration;
import java.util.List;
import java.util.Map;

@Service
@Slf4j
public class UserClient {

    private final JwtCreator jwtCreator;
    private final RestTemplate restTemplate;

    public UserClient(JwtCreator jwtCreator,
                      ClientProperties clientProperties,
                      RestTemplateBuilder templateBuilder) {
        this.jwtCreator = jwtCreator;
        this.restTemplate = templateBuilder.rootUri(clientProperties.getUserService())
                .setConnectTimeout(Duration.ofMillis(5000))
                .setReadTimeout(Duration.ofMillis(5000))
                .build();
    }

    public AppUser getUser(long id) {
        return restTemplate.exchange("/users/" + id, HttpMethod.GET, getHttpEntityWithServiceToken(), AppUser.class)
                .getBody();
    }

    private HttpEntity<?> getHttpEntityWithServiceToken() {
        String token =  jwtCreator.createToken(0, UserRole.ADMIN);
        Map<String, List<String>> headers = Map.of("Authorization", List.of("Bearer " + token));
        return new HttpEntity<>(new MultiValueMapAdapter<>(headers));
    }
}
