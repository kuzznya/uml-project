package com.github.kuzznya.bs.orderservice.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Value;
import lombok.With;
import lombok.extern.jackson.Jacksonized;

import java.util.List;

@Value
@AllArgsConstructor
@Builder
@Jacksonized
public class ServiceModel {
    @With
    Long id;
    String name;
    String description;
    List<SubscriptionLimit> limits;
    OrderTemplate template;
}
