package com.github.kuzznya.bs.orderservice.service;

import com.github.kuzznya.bs.orderservice.entity.OrderTemplateEntity;
import com.github.kuzznya.bs.orderservice.entity.OrderTemplateItemEntity;
import com.github.kuzznya.bs.orderservice.entity.ServiceEntity;
import com.github.kuzznya.bs.orderservice.entity.SubscriptionLimitEntity;
import com.github.kuzznya.bs.orderservice.entity.projection.ServiceInfo;
import com.github.kuzznya.bs.orderservice.exception.NotFoundException;
import com.github.kuzznya.bs.orderservice.model.*;
import com.github.kuzznya.bs.orderservice.repository.OrderTemplateItemRepository;
import com.github.kuzznya.bs.orderservice.repository.OrderTemplateRepository;
import com.github.kuzznya.bs.orderservice.repository.ServiceRepository;
import com.github.kuzznya.bs.orderservice.repository.SubscriptionLimitRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class ServiceManager {

    private final ServiceRepository serviceRepository;
    private final SubscriptionLimitRepository limitRepository;
    private final OrderTemplateRepository templateRepository;
    private final OrderTemplateItemRepository templateItemRepository;

    @Transactional
    public ServiceModel createService(ServiceCreationRequest request) {
        ServiceEntity entity = requestToEntity(request);
        ServiceEntity saved = serviceRepository.save(entity);

        List<SubscriptionLimitEntity> limits = request.getLimits()
                .stream()
                .map(model -> modelToEntity(saved.getId(), model))
                .collect(Collectors.toList());
        limitRepository.saveAll(limits);

        OrderTemplateEntity template = new OrderTemplateEntity(null);
        long templateId = templateRepository.save(template).getId();
        serviceRepository.save(saved.withTemplateId(templateId));

        List<OrderTemplateItemEntity> items = request.getTemplate().getItems()
                .stream()
                .map(item -> modelToEntity(templateId, item))
                .collect(Collectors.toList());
        templateItemRepository.saveAll(items);

        return getService(saved.getId()).orElseThrow(() -> new NotFoundException("Service not found"));
    }

    public List<ServiceInfo> getServices() {
        return serviceRepository.getServicesInfo();
    }

    public Optional<ServiceModel> getService(long id) {
        return serviceRepository.findById(id)
                .map(this::entityToModel);
    }

    private List<SubscriptionLimit> getServiceLimits(long serviceId) {
        return limitRepository.findAllByServiceIdAndDeprecatedIsFalse(serviceId)
                .stream()
                .map(this::entityToModel)
                .collect(Collectors.toList());
    }

    private OrderTemplate getServiceTemplate(long serviceId) {
        return templateRepository.findServiceOrderTemplate(serviceId)
                .map(this::entityToModel)
                .orElseThrow(() -> new NotFoundException("Template not found"));
    }

    private List<OrderTemplateItem> getTemplateItems(long templateId) {
        return templateItemRepository.findAllByTemplateId(templateId)
                .stream()
                .map(this::entityToModel)
                .collect(Collectors.toList());
    }

    private ServiceEntity requestToEntity(ServiceCreationRequest request) {
        return ServiceEntity.builder()
                .name(request.getName())
                .description(request.getDescription())
                .build();
    }

    private SubscriptionLimitEntity modelToEntity(long serviceId, SubscriptionLimit limit) {
        return SubscriptionLimitEntity.builder()
                .serviceId(serviceId)
                .limit(limit.getLimit())
                .price(limit.getPrice())
                .deprecated(false)
                .build();
    }

    private OrderTemplateItemEntity modelToEntity(long templateId, OrderTemplateItem item) {
        return OrderTemplateItemEntity.builder()
                .templateId(templateId)
                .type(item.getType())
                .content(item.getContent())
                .build();
    }

    private ServiceModel entityToModel(ServiceEntity entity) {
        return ServiceModel.builder()
                .id(entity.getId())
                .name(entity.getName())
                .description(entity.getDescription())
                .limits(getServiceLimits(entity.getId()))
                .template(getServiceTemplate(entity.getId()))
                .build();
    }

    private SubscriptionLimit entityToModel(SubscriptionLimitEntity entity) {
        return SubscriptionLimit.builder()
                .id(entity.getId())
                .price(entity.getPrice())
                .limit(entity.getLimit())
                .build();
    }

    private OrderTemplate entityToModel(OrderTemplateEntity entity) {
        return OrderTemplate.builder()
                .id(entity.getId())
                .items(getTemplateItems(entity.getId()))
                .build();
    }

    private OrderTemplateItem entityToModel(OrderTemplateItemEntity entity) {
        return OrderTemplateItem.builder()
                .id(entity.getId())
                .type(entity.getType())
                .content(entity.getContent())
                .build();
    }
}
