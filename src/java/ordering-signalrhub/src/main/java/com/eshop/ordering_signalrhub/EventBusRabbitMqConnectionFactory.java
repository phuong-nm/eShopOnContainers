package com.eshop.ordering_signalrhub;

import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.Connection;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import com.rabbitmq.client.Channel;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class EventBusRabbitMqConnectionFactory {

    @Bean
    @ConditionalOnMissingBean
    ConnectionFactory getConnectionFactory() {
        CachingConnectionFactory factory = new CachingConnectionFactory();
        factory.setHost("localhost");
        factory.setPort(5672);
        log.info("ConnectionFactory created");
        return factory;
    }

    @Bean
    @ConditionalOnMissingBean
    Connection getConnection(ConnectionFactory factory) {
        log.info("Connection created");
        return factory.createConnection();
    }

    @Bean
    @ConditionalOnMissingBean
    Channel getChannel(Connection connection) {
        return connection.createChannel(false);
    }
}
