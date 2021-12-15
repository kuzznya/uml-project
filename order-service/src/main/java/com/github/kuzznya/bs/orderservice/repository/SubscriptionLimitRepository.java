package com.github.kuzznya.bs.orderservice.repository;

import com.github.kuzznya.bs.orderservice.entity.SubscriptionLimitEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SubscriptionLimitRepository extends CrudRepository<SubscriptionLimitEntity, Long> {
    List<SubscriptionLimitEntity> findAllByServiceIdAndDeprecatedIsFalse(long serviceId);
}
