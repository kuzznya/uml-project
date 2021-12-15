package com.github.kuzznya.bs.orderservice.entity;

import com.github.kuzznya.bs.orderservice.model.OrderTemplateItemType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Value;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

@Table("order_template_item")
@Value
@AllArgsConstructor
@Builder
public class OrderTemplateItemEntity {
    @Id
    Long id;
    Long templateId;
    String content;
    OrderTemplateItemType type;
}
