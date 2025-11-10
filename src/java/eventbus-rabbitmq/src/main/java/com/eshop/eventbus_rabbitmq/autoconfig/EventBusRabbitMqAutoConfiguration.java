package com.eshop.eventbus_rabbitmq.autoconfig;

import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.Connection;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;

@AutoConfiguration
public class EventBusRabbitMqAutoConfiguration {

    @Bean
    @ConditionalOnMissingBean
    ConnectionFactory getConnectionFactory() {
        CachingConnectionFactory factory = new CachingConnectionFactory();
        // TODO allow parameters to be passed via properties
        factory.setHost("localhost");
        factory.setPort(5672);
        return factory;
    }

    @Bean
    @ConditionalOnMissingBean
    Connection getConnection(ConnectionFactory factory) {
        return factory.createConnection();
    }
}
