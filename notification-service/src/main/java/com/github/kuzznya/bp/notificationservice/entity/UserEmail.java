package com.github.kuzznya.bp.notificationservice.entity;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.PersistenceConstructor;
import org.springframework.data.annotation.Transient;
import org.springframework.data.domain.Persistable;
import org.springframework.data.relational.core.mapping.Table;

@Table("user_email")
@Getter
@RequiredArgsConstructor(onConstructor_ = @PersistenceConstructor)
@AllArgsConstructor
public class UserEmail implements Persistable<Long> {
    @Id
    private final Long id;
    @With
    private final String email;
    @Transient
    boolean isNew;

    @Override
    public boolean isNew() {
        return isNew;
    }
}
