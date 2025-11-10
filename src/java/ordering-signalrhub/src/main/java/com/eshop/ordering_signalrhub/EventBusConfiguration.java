package com.eshop.ordering_signalrhub;

import org.springframework.amqp.rabbit.connection.Connection;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.eshop.eventbus.EventBus;
import com.eshop.eventbus.EventBusSubscriptionManager;
import com.eshop.eventbus_rabbitmq.EventBusRabbitMq;

@Configuration
public class EventBusConfiguration {
    @Bean
    @ConditionalOnMissingBean
    EventBus getEventBus(Connection connection, EventBusSubscriptionManager subscriptionManager) {
        return new EventBusRabbitMq(connection, subscriptionManager, "Ordering.signalrhub.Test");
    }
}
