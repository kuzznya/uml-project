package com.github.kuzznya.bs.orderservice.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Value;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

@Table("order")
@Value
@AllArgsConstructor
@Builder
public class OrderValue {
    @Id
    Long id;
    Long itemId;
    String value;
}
