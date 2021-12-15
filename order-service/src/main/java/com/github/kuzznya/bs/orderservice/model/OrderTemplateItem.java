package com.github.kuzznya.bs.orderservice.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Value;
import lombok.extern.jackson.Jacksonized;

@Value
@AllArgsConstructor
@Jacksonized
@Builder
public class OrderTemplateItem {
    Long id;
    String content;
    OrderTemplateItemType type;
}
