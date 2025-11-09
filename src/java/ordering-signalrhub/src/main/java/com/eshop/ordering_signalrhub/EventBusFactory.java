package com.eshop.ordering_signalrhub;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import com.eshop.eventbus.EventBus;
import com.eshop.eventbus.EventBusSubscriptionManager;
import org.springframework.amqp.rabbit.connection.Connection;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class EventBusFactory {

    @Autowired
    private EventBusSubscriptionManager subscriptionManager;

    @Autowired
    private Connection connection;

    @Bean
    @ConditionalOnMissingBean
    EventBus getEventBus() {
        return new EventBusRabbitMq(connection, subscriptionManager, "Ordering.signalrhub.Test");
    }
}
