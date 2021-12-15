package com.github.kuzznya.bp.notificationservice.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@ConfigurationProperties("client")
@Component
@Data
public class ClientProperties {
    private String userService = "http://localhost:8000";
}
