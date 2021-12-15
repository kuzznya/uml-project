package com.github.kuzznya.bs.userservice.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.github.kuzznya.bs.userservice.model.UserRole;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Value;
import lombok.With;
import lombok.extern.jackson.Jacksonized;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

import javax.validation.Valid;
import javax.validation.constraints.Email;

@Table("app_user")
@Value
@AllArgsConstructor
@Jacksonized
@Builder
@Valid
public class AppUserEntity {
    @With
    @Id
    Long id;
    String name;
    String surname;
    @Email
    String email;
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @With
    String password;
    @With
    UserRole role;
}
