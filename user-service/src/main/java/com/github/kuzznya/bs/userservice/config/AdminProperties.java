package com.github.kuzznya.bs.userservice.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@ConfigurationProperties("security.admin")
@Component
@Data
@Validated
public class AdminProperties {
    @NotBlank
    @Email
    private String email;
    @NotBlank
    private String password;
}
