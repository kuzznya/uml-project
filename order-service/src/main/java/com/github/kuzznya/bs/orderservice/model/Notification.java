package com.github.kuzznya.bs.orderservice.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Value;
import lombok.extern.jackson.Jacksonized;

@Value
@AllArgsConstructor
@Builder
@Jacksonized
public class Notification {
    long id;
    String title;
    String message;
}
