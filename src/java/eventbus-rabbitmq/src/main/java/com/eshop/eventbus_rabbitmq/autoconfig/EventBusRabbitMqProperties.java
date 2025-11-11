package com.eshop.eventbus_rabbitmq.autoconfig;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "eshop.eventbus_rabbitmq")
public class EventBusRabbitMqProperties {
    // AMQP 0-9-1 URI, for example "amqp://guest:guest@localhost:5672"
    // See also: https://www.rabbitmq.com/docs/uri-spec
    private String connectionString;
    private String queueName;

    public String getConnectionString() {
        return connectionString;
    }

    public void setConnectionString(String connectionString) {
        this.connectionString = connectionString;
    }

    public String getQueueName() {
        return queueName;
    }

    public void setQueueName(String queueName) {
        this.queueName = queueName;
    }
}
