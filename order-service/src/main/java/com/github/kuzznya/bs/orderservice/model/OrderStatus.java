package com.github.kuzznya.bs.orderservice.model;

public enum OrderStatus {
    CREATED,
    IN_PROCESS,
    VERIFICATION_REQUESTED,
    COMPLETED,
    DECLINED,
    DISPUTE_OPENED
}
