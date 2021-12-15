package com.github.kuzznya.bs.orderservice.service;

import com.github.kuzznya.bs.orderservice.entity.ProviderEmployeeEntity;
import com.github.kuzznya.bs.orderservice.entity.ProviderEntity;
import com.github.kuzznya.bs.orderservice.exception.NotFoundException;
import com.github.kuzznya.bs.orderservice.model.ProviderStatus;
import com.github.kuzznya.bs.orderservice.repository.ProviderEmployeeRepository;
import com.github.kuzznya.bs.orderservice.repository.ProviderRepository;
import com.github.kuzznya.bs.orderservice.security.UserInfo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class ProviderService {

    private final ProviderNotifier notifier;
    private final ProviderRepository repository;
    private final ProviderEmployeeRepository employeeRepository;

    @Transactional
    public ProviderEntity registerProvider(ProviderEntity provider, UserInfo userInfo) {
        ProviderEntity result = repository.save(provider.withId(null).withStatus(ProviderStatus.PENDING_VERIFICATION));
        employeeRepository.save(new ProviderEmployeeEntity(null, result.getId(), userInfo.getId()));
        return result;
    }

    public List<ProviderEntity> getUnverifiedProviders() {
        return repository.findAllByStatus(ProviderStatus.PENDING_VERIFICATION);
    }

    public ProviderEntity updateStatus(long id, ProviderStatus status) {
        ProviderEntity provider = repository.findById(id)
                .orElseThrow(() -> new NotFoundException("Provider with id " + id + " not found"));
        if (provider.getStatus().equals(status))
            return provider;
        ProviderEntity result = repository.save(provider.withStatus(status));
        notifier.onStatusChange(result);
        return result;
    }
}
