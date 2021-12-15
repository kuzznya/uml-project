package com.github.kuzznya.bp.notificationservice.config;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

@ConfigurationProperties("email")
@Component
@Data
@NoArgsConstructor
@AllArgsConstructor
@Validated
public class EmailProperties {
    private String from;
}
