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

    @Bean
    @ConditionalOnMissingBean
    EventBus getEventBus(Connection connection, EventBusSubscriptionManager subscriptionManager) {
        return new EventBusRabbitMq(connection, subscriptionManager, "Ordering.signalrhub.Test");
    }
}
