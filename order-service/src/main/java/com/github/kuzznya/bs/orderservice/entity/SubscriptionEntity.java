package com.github.kuzznya.bs.orderservice.entity;

import com.github.kuzznya.bs.orderservice.model.SubscriptionStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Value;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

@Table("subscription")
@Value
@AllArgsConstructor
@Builder
public class SubscriptionEntity {
    @Id
    Long id;
    Long clientId;
    Long serviceId;
    Long providerId;
    SubscriptionStatus status;
    Long limitId;
}
