package com.github.kuzznya.bs.userservice.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.convert.DurationUnit;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotEmpty;
import java.time.Duration;
import java.time.temporal.ChronoUnit;

@ConfigurationProperties("security")
@Data
@Component
@Validated
public class SecurityProperties {
    @NotEmpty
    String secret;
    @DurationUnit(ChronoUnit.DAYS)
    Duration tokenTtl;
}
