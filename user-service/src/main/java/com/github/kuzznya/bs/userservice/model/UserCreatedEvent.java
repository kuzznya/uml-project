package com.github.kuzznya.bs.userservice.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Value;
import lombok.extern.jackson.Jacksonized;

@Value
@AllArgsConstructor
@Builder
@Jacksonized
public class UserCreatedEvent {
    long id;
    String email;
}
