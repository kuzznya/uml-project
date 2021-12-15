package com.github.kuzznya.bs.orderservice.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Value;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

@Table("order_template")
@Value
@AllArgsConstructor
@Builder
public class OrderTemplateEntity {
    @Id
    Long id;
}
