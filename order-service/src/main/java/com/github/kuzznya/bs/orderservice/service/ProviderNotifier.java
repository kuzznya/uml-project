package com.github.kuzznya.bs.orderservice.service;

import com.github.kuzznya.bs.orderservice.entity.ProviderEmployeeEntity;
import com.github.kuzznya.bs.orderservice.entity.ProviderEntity;
import com.github.kuzznya.bs.orderservice.model.Notification;
import com.github.kuzznya.bs.orderservice.repository.ProviderEmployeeRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class ProviderNotifier {

    private static final String NOTIFICATION_QUEUE = "notifications.email";

    private final ProviderEmployeeRepository employeeRepository;
    private final RabbitTemplate rabbitTemplate;

    public void onStatusChange(ProviderEntity provider) {
        employeeRepository.findAllByProviderId(provider.getId())
                .stream()
                .map(ProviderEmployeeEntity::getUserId)
                .map(id -> new Notification(id, "Request accepted", "Your provider request has been approved by moderator"))
                .forEach(notification -> rabbitTemplate.convertAndSend(NOTIFICATION_QUEUE, notification));
    }
}
