package com.github.kuzznya.bp.notificationservice.repository;

import com.github.kuzznya.bp.notificationservice.entity.UserEmail;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserEmailRepository extends CrudRepository<UserEmail, Long> {
}
