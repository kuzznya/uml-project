package com.github.kuzznya.bs.orderservice.repository;

import com.github.kuzznya.bs.orderservice.entity.ProviderEntity;
import com.github.kuzznya.bs.orderservice.model.ProviderStatus;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProviderRepository extends CrudRepository<ProviderEntity, Long> {
    List<ProviderEntity> findAllByStatus(ProviderStatus status);
}
