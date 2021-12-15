package com.github.kuzznya.bs.orderservice.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Value;
import lombok.With;
import lombok.extern.jackson.Jacksonized;

@Value
@AllArgsConstructor
@Jacksonized
@Builder
public class SubscriptionLimit {
    @With
    Long id;
    int limit;
    int price;
}
