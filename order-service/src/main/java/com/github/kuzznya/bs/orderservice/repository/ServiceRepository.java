package com.github.kuzznya.bs.orderservice.repository;

import com.github.kuzznya.bs.orderservice.entity.ServiceEntity;
import com.github.kuzznya.bs.orderservice.entity.projection.ServiceInfo;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ServiceRepository extends CrudRepository<ServiceEntity, Long> {
    List<ServiceEntity> findAllByNameLike(String name);
    @Query("SELECT id, name, description FROM service")
    List<ServiceInfo> getServicesInfo();
}
