package com.github.kuzznya.bs.userservice.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Value;
import lombok.extern.jackson.Jacksonized;

import javax.validation.Valid;
import javax.validation.constraints.Email;

@Value
@AllArgsConstructor
@Jacksonized
@Builder
@Valid
public class AppUser {
    Long id;
    String name;
    String surname;
    @Email
    String email;
    UserRole role;
}
