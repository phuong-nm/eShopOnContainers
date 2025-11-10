package com.eshop.eventbus_rabbitmq.autoconfig;

import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.Connection;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;

import com.eshop.eventbus.EventBus;
import com.eshop.eventbus.EventBusSubscriptionManager;
import com.eshop.eventbus_rabbitmq.EventBusRabbitMq;

@AutoConfiguration
@EnableConfigurationProperties
public class EventBusRabbitMqAutoConfiguration {

    @Value("${eshop.eventbus_rabbitmq.queue_name}")
    private String queueName;

    @Value("${eshop.eventbus_rabbitmq.host}")
    private String host;

    @Value("${eshop.eventbus_rabbitmq.port}")
    private int port;

    @Bean
    @ConditionalOnMissingBean
    ConnectionFactory getConnectionFactory() {
        CachingConnectionFactory factory = new CachingConnectionFactory();
        factory.setHost(host);
        factory.setPort(port);
        return factory;
    }

    @Bean
    @ConditionalOnMissingBean
    Connection getConnection(ConnectionFactory factory) {
        return factory.createConnection();
    }

    @Bean
    @ConditionalOnMissingBean
    EventBus getEventBus(ApplicationContext applicationContext, Connection connection, EventBusSubscriptionManager subscriptionManager) {
        return new EventBusRabbitMq(applicationContext, connection, subscriptionManager, queueName);
    }
}
