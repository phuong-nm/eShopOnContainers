package com.eshop.ordering_signalrhub;

import java.io.IOException;

import org.springframework.amqp.rabbit.connection.Connection;

import com.eshop.eventbus.EventBus;
import com.eshop.eventbus.EventBusSubscriptionManager;
import com.eshop.eventbus.IntegrationEvent;
import com.eshop.eventbus.IntegrationEventHandler;
import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Consumer;
import com.rabbitmq.client.Envelope;
import com.rabbitmq.client.ShutdownSignalException;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class EventBusRabbitMq implements EventBus, Consumer {

    private final String BROKER_NAME = "eshop_event_bus";

    private Connection connection;
    private Channel channel;
    private EventBusSubscriptionManager subscriptionManager;
    private String queueName;

    public EventBusRabbitMq(Connection connection, EventBusSubscriptionManager subscriptionManager, String queueName) {
        log.info("EventBusRabbitMQ constructor");
        this.connection = connection;
        this.channel = connection.createChannel(false);
        this.subscriptionManager = subscriptionManager;
        this.queueName = queueName;
    }

    @Override
    public void publish(IntegrationEvent event) {
        //channel.basicPublish();
    }

    @Override
    public <T extends IntegrationEvent, TH extends IntegrationEventHandler<T>> void subscribe(Class<T> t, Class<TH> th) {
        // create connection
        try {
            log.info("EventBusRabbitMQ declare queue");
            channel.queueDeclare(queueName, true, false, false, null);
            channel.queueBind(queueName, BROKER_NAME, t.getSimpleName());
            boolean autoAck = false;
            channel.basicConsume(queueName, autoAck, this);
            //subscriptionManager.addSubscription(t, th);

        } catch (Exception e) {
            log.error(e.toString());
        }
    }

    @Override
    public <T extends IntegrationEvent, TH extends IntegrationEventHandler<T>> void unsubscribe(Class<T> t, Class<TH> th) {

    }

    @Override
    public void handleConsumeOk(String consumerTag) {
        log.info("EventBusRabbitMQ handleConsumeOk");
    }

    @Override
    public void handleCancelOk(String consumerTag) {
        log.info("EventBusRabbitMQ handleCancelOk");
    }

    @Override
    public void handleCancel(String consumerTag) throws IOException {
        log.info("EventBusRabbitMQ handleCancel");
    }

    @Override
    public void handleShutdownSignal(String consumerTag, ShutdownSignalException var2) {
        log.info("EventBusRabbitMQ handleShutdownSignal");
    }

    @Override
    public void handleRecoverOk(String consumerTag) {
        log.info("EventBusRabbitMQ handleRecoverOk");
    }

    @Override
    public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] payload) throws IOException {
        log.info("EventBusRabbitMQ handleDelivery {} {} {}", consumerTag, envelope.getRoutingKey(), new String(payload));
        channel.basicAck(envelope.getDeliveryTag(), false);
        // TODO. get handler from routingKey and then dispatch the received message
    }
}
