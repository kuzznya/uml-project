package com.github.kuzznya.bs.orderservice.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Value;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

@Table("subscription_limit")
@Value
@AllArgsConstructor
@Builder
public class SubscriptionLimitEntity {
    @Id
    Long id;
    Long serviceId;
    int limit;
    int price;
    boolean deprecated;
}
