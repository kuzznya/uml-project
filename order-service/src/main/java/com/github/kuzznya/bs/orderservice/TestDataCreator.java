package com.github.kuzznya.bs.orderservice;

import com.github.kuzznya.bs.orderservice.entity.ProviderEntity;
import com.github.kuzznya.bs.orderservice.model.*;
import com.github.kuzznya.bs.orderservice.security.UserInfo;
import com.github.kuzznya.bs.orderservice.service.ProviderService;
import com.github.kuzznya.bs.orderservice.service.ServiceManager;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
@Slf4j
public class TestDataCreator implements CommandLineRunner {

    private final ServiceManager serviceManager;
    private final ProviderService providerService;

    @Override
    public void run(String... args) throws Exception {
        createService();
        createUnverifiedProvider();
    }

    private void createService() {
        ServiceCreationRequest request = ServiceCreationRequest.builder()
                .name("First service")
                .description("Sample description")
                .limits(List.of(
                                SubscriptionLimit.builder().price(100_00).limit(100).build()
                        )
                ).template(OrderTemplate
                        .builder()
                        .items(List.of(
                                        OrderTemplateItem.builder().type(OrderTemplateItemType.TEXT).content("Sample text").build(),
                                        OrderTemplateItem.builder().type(OrderTemplateItemType.INPUT).content("Sample input").build()
                                )
                        ).build()
                ).build();

        serviceManager.createService(request);
    }

    private void createUnverifiedProvider() {
        ProviderEntity provider = ProviderEntity.builder()
                .name("Test provider")
                .address("St. Petersburg")
                .information("Just a test")
                .build();
        providerService.registerProvider(provider, new UserInfo(1));
    }
}
