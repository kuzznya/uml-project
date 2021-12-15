package com.github.kuzznya.bs.orderservice.repository;

import com.github.kuzznya.bs.orderservice.entity.OrderTemplateItemEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderTemplateItemRepository extends CrudRepository<OrderTemplateItemEntity, Long> {
    List<OrderTemplateItemEntity> findAllByTemplateId(long templateId);
}
