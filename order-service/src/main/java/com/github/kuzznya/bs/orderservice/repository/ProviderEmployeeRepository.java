package com.github.kuzznya.bs.orderservice.repository;

import com.github.kuzznya.bs.orderservice.entity.ProviderEmployeeEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProviderEmployeeRepository extends CrudRepository<ProviderEmployeeEntity, Long> {
    List<ProviderEmployeeEntity> findAllByProviderId(long id);
}
