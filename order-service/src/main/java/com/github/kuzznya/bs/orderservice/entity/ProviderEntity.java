package com.github.kuzznya.bs.orderservice.entity;

import com.github.kuzznya.bs.orderservice.model.ProviderStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Value;
import lombok.With;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

@Table("provider")
@Value
@AllArgsConstructor
@Builder
public class ProviderEntity {
    @Id
    @With
    Long id;
    String name;
    String address;
    String information;
    @With
    ProviderStatus status;
}
