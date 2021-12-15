package com.github.kuzznya.bs.orderservice.repository;

import com.github.kuzznya.bs.orderservice.entity.OrderTemplateEntity;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface OrderTemplateRepository extends CrudRepository<OrderTemplateEntity, Long> {
    @Query("SELECT t.id FROM order_template t " +
            "JOIN service s ON t.id = s.template_id " +
            "WHERE s.id = :serviceId")
    Optional<OrderTemplateEntity> findServiceOrderTemplate(long serviceId);
}
