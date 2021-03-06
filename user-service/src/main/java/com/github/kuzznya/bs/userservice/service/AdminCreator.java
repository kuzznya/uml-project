package com.github.kuzznya.bs.userservice.service;

import com.github.kuzznya.bs.userservice.config.AdminProperties;
import com.github.kuzznya.bs.userservice.entity.AppUserEntity;
import com.github.kuzznya.bs.userservice.model.UserRole;
import com.github.kuzznya.bs.userservice.repository.AppUserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class AdminCreator implements CommandLineRunner {

    private final UserService userService;
    private final AppUserRepository userRepository;
    private final AdminProperties adminProperties;

    @Override
    public void run(String... args) {
        userRepository.findByEmail(adminProperties.getEmail())
                .ifPresentOrElse(
                        entity -> userService.updateUser(entity.withPassword(adminProperties.getPassword()), UserRole.ADMIN),
                        () -> userService.createUser(createAdmin(adminProperties.getEmail(), adminProperties.getPassword()), UserRole.ADMIN));
        log.info("Admin account {} created/updated", adminProperties.getEmail());
    }

    private AppUserEntity createAdmin(String email, String encodedPassword) {
        return AppUserEntity.builder()
                .role(UserRole.ADMIN)
                .email(email)
                .name("John")
                .surname("Doe")
                .password(encodedPassword)
                .build();
    }
}
