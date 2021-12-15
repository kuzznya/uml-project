package com.github.kuzznya.bs.userservice.service;

import com.github.kuzznya.bs.userservice.model.UserCreatedEvent;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserCreatedEventPublisher {

    private static final String CREATED_USERS_QUEUE = "users.created";

    private final RabbitTemplate rabbitTemplate;

    public void publishEvent(UserCreatedEvent event) {
        rabbitTemplate.convertAndSend(CREATED_USERS_QUEUE, event);
    }
}
