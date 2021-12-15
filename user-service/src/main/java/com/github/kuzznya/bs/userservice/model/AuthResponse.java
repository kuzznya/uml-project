package com.github.kuzznya.bs.userservice.model;

import lombok.AllArgsConstructor;
import lombok.Value;
import lombok.extern.jackson.Jacksonized;

@Value
@Jacksonized
@AllArgsConstructor
public class AuthResponse {
    String token;
}
