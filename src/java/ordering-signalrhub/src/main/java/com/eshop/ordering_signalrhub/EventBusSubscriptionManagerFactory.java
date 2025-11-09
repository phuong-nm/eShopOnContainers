package com.eshop.ordering_signalrhub;

import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import com.eshop.eventbus.EventBusSubscriptionManager;
import com.eshop.eventbus.EventBusSubscriptionManagerInMemory;

@Component
public class EventBusSubscriptionManagerFactory {
    @Bean
    @ConditionalOnMissingBean
    EventBusSubscriptionManager getEventBusSubscriptionManager() {
        return new EventBusSubscriptionManagerInMemory();
    }
}
