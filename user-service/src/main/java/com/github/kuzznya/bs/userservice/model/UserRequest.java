package com.github.kuzznya.bs.userservice.model;

import lombok.AllArgsConstructor;
import lombok.Value;
import lombok.extern.jackson.Jacksonized;

@Value
@AllArgsConstructor
@Jacksonized
public class UserRequest {
    long id;
}
