package com.eshop.eventbus.autoconfig;

import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;

import com.eshop.eventbus.EventBusSubscriptionManager;
import com.eshop.eventbus.EventBusSubscriptionManagerInMemory;

@AutoConfiguration
public class EventBusSubscriptionManagerAutoConfiguration {

    @Bean
    @ConditionalOnMissingBean
    EventBusSubscriptionManager eventBusSubscriptionManager() {
        return new EventBusSubscriptionManagerInMemory();
    }
}
