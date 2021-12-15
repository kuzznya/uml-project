package com.github.kuzznya.bs.orderservice.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Value;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

@Table("provider_employee")
@Value
@AllArgsConstructor
@Builder
public class ProviderEmployeeEntity {
    @Id
    Long id;
    Long providerId;
    Long userId;
}
