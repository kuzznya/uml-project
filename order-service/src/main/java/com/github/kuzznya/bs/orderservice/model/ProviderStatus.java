package com.github.kuzznya.bs.orderservice.model;

public enum ProviderStatus {
    PENDING_VERIFICATION,
    PENDING_PAYMENT_INFO_VERIFICATION,
    INVALID_PAYMENT_INFO,
    VERIFIED,
    DECLINED
}
