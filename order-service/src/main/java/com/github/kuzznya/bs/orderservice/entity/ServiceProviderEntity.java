package com.github.kuzznya.bs.orderservice.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Value;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

@Table("service_provider")
@Value
@AllArgsConstructor
@Builder
public class ServiceProviderEntity {
    @Id
    Long id;
    Long serviceId;
    Long providerId;
}
