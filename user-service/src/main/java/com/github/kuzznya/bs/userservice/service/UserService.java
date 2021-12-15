package com.github.kuzznya.bs.userservice.service;

import com.github.kuzznya.bs.userservice.entity.AppUserEntity;
import com.github.kuzznya.bs.userservice.exception.NotFoundException;
import com.github.kuzznya.bs.userservice.model.AppUser;
import com.github.kuzznya.bs.userservice.model.UserCreatedEvent;
import com.github.kuzznya.bs.userservice.model.UserRole;
import com.github.kuzznya.bs.userservice.repository.AppUserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserService {

    private final AppUserRepository repository;
    private final PasswordEncoder encoder;
    private final UserCreatedEventPublisher eventPublisher;

    public AppUser getUser(long id) {
        return repository.findById(id)
                .map(this::entityToModel)
                .orElseThrow(() -> new NotFoundException("User with id " + id + " not found"));
    }

    public AppUser createUser(AppUserEntity entity) {
        return createUser(entity, UserRole.USER);
    }

    public AppUser createUser(AppUserEntity entity, UserRole role) {
        AppUserEntity result = repository.save(entity
                .withId(null)
                .withRole(role)
                .withPassword(encoder.encode(entity.getPassword())));
        eventPublisher.publishEvent(new UserCreatedEvent(result.getId(), result.getEmail()));
        return entityToModel(result);
    }

    public AppUser updateUser(AppUserEntity entity, UserRole role) {
        AppUserEntity prepared = entity.withRole(role).withPassword(encoder.encode(entity.getPassword()));
        AppUser result = entityToModel(repository.save(prepared));
        log.debug("User {} updated, role is {}", result.getId(), role);
        return result;
    }

    public Optional<AppUser> findByEmail(String email) {
        return repository.findByEmail(email).map(this::entityToModel);
    }

    public Optional<AppUserEntity> findEntityByEmail(String email) {
        return repository.findByEmail(email);
    }

    public Optional<AppUser> findById(long id) {
        return repository.findById(id).map(this::entityToModel);
    }

    private AppUser entityToModel(AppUserEntity entity) {
        return AppUser.builder()
                .id(entity.getId())
                .email(entity.getEmail())
                .name(entity.getName())
                .surname(entity.getSurname())
                .role(entity.getRole())
                .build();
    }
}
