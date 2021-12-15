package com.github.kuzznya.bs.orderservice.entity;

import com.github.kuzznya.bs.orderservice.model.OrderStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Value;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

import java.time.Instant;

@Table("order")
@Value
@AllArgsConstructor
@Builder
public class Order {
    @Id
    Long id;
    Long subscriptionId;
    OrderStatus status;
    Instant creationTimestamp;
}
