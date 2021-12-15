package com.github.kuzznya.bp.notificationservice.service;

import com.github.kuzznya.bp.notificationservice.client.UserClient;
import com.github.kuzznya.bp.notificationservice.entity.UserEmail;
import com.github.kuzznya.bp.notificationservice.exception.NotFoundException;
import com.github.kuzznya.bp.notificationservice.model.AppUser;
import com.github.kuzznya.bp.notificationservice.model.UserCreatedEvent;
import com.github.kuzznya.bp.notificationservice.repository.UserEmailRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserService {

    private static final String CREATED_USERS_QUEUE = "users.created";

    private final UserClient userClient;
    private final UserEmailRepository repository;

    @RabbitListener(queuesToDeclare = @Queue(name = CREATED_USERS_QUEUE, durable = "true", exclusive = "false"))
    public void onUserCreated(UserCreatedEvent event) {
        if (repository.existsById(event.getId()))
            repository.save(new UserEmail(event.getId(), event.getEmail(), false));
        else
            repository.save(new UserEmail(event.getId(), event.getEmail(), true));
        log.info("User {} with email {} saved", event.getId(), event.getEmail());
    }

    String getEmailById(long id) {
        return repository.findById(id)
                .map(UserEmail::getEmail)
                .or(() -> loadUserInfo(id).map(AppUser::getEmail))
                .orElseThrow(() -> new NotFoundException("User with id " + id + " not found"));
    }

    Optional<AppUser> loadUserInfo(long id) {
        try {
            AppUser user = userClient.getUser(id);
            onUserCreated(new UserCreatedEvent(user.getId(), user.getEmail()));
            return Optional.of(user);
        } catch (Exception e) {
            log.error("Cannot get user info", e);
            return Optional.empty();
        }
    }
}
