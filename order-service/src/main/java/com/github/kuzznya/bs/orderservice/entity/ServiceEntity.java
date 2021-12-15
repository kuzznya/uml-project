package com.github.kuzznya.bs.orderservice.entity;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.github.kuzznya.bs.orderservice.entity.projection.ServiceInfo;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Value;
import lombok.With;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

@Table("service")
@Value
@AllArgsConstructor
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ServiceEntity implements ServiceInfo {
    @Id
    Long id;
    String name;
    String description;
    @With
    Long templateId;
}
