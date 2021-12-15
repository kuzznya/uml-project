package com.github.kuzznya.bs.orderservice.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Value;
import lombok.extern.jackson.Jacksonized;

import java.util.List;

@Value
@AllArgsConstructor
@Builder
@Jacksonized
public class ServiceCreationRequest {
    String name;
    String description;
    OrderTemplate template;
    List<SubscriptionLimit> limits;
}
