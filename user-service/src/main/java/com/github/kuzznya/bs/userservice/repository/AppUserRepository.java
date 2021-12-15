package com.github.kuzznya.bs.userservice.repository;

import com.github.kuzznya.bs.userservice.entity.AppUserEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface AppUserRepository extends CrudRepository<AppUserEntity, Long> {
    Optional<AppUserEntity> findByEmail(String email);
}
