package com.eshop.eventbus_rabbitmq;

import java.io.IOException;
import java.lang.reflect.Method;
import java.util.List;

import org.springframework.amqp.core.ExchangeTypes;
import org.springframework.amqp.rabbit.connection.Connection;
import org.springframework.context.ApplicationContext;

import com.eshop.eventbus.EventBus;
import com.eshop.eventbus.EventBusSubscriptionManager;
import com.eshop.eventbus.IntegrationEvent;
import com.eshop.eventbus.IntegrationEventHandler;
import com.eshop.eventbus.SubscriptionInfo;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Consumer;
import com.rabbitmq.client.DefaultConsumer;
import com.rabbitmq.client.Envelope;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class EventBusRabbitMq implements EventBus {

    private final String BROKER_NAME = "eshop_event_bus";

    private final ApplicationContext applicationContext;
    private Connection connection;
    private EventBusSubscriptionManager subscriptionManager;
    private String queueName;
    private Channel consumerChannel;
    private Channel producerChannel;
    private Consumer consumer;
    private final ObjectMapper objectMapper;
    private final boolean autoAck = false;

    public EventBusRabbitMq(ApplicationContext applicationContext, Connection connection, EventBusSubscriptionManager subscriptionManager, String queueName) {
        this.applicationContext = applicationContext;
        this.connection = connection;
        this.subscriptionManager = subscriptionManager;
        this.queueName = queueName;
        this.consumerChannel = createConsumerChannel();
        this.producerChannel = createProducerChannel();
        this.objectMapper = new ObjectMapper();
        this.objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
    }

    @Override
    public void publish(IntegrationEvent event) {
        log.info("publish {}", event);
        //channel.basicPublish();
    }

    @Override
    public <T extends IntegrationEvent, TH extends IntegrationEventHandler<T>> void subscribe(Class<T> t, Class<TH> th) {
        // Handle subscription
        String eventName = subscriptionManager.getEventKey(t);
        if (!subscriptionManager.hasSubscriptionsForEvent(t)) {
            if (consumerChannel != null) {
                try {
                    consumerChannel.queueBind(queueName, BROKER_NAME, eventName);

                    log.info("Subscribing to event {} with {}", eventName, th.getSimpleName());

                    subscriptionManager.addSubscription(t, th);

                    startBasicConsume();

                } catch (Exception e) {
                    // TODO: handle exception
                    log.error("subscribe failed {}", e.toString());
                }
            }
        }
    }

    @Override
    public <T extends IntegrationEvent, TH extends IntegrationEventHandler<T>> void unsubscribe(Class<T> t, Class<TH> th) {

    }

    private Channel createConsumerChannel() {
        Channel channel = connection.createChannel(false);

        try {
            channel.exchangeDeclare(BROKER_NAME, ExchangeTypes.DIRECT);
            channel.queueDeclare(queueName, true, false, false, null);
        } catch (Exception e) {
            // TODO: handle exception
            log.error("createConsumerChannel failed {}", e.toString());
            channel = null;
        }

        return channel;
    }

    private Channel createProducerChannel() {
        Channel channel = connection.createChannel(false);
        return channel;
    }

    private void startBasicConsume() {
        if (consumerChannel != null) {
            consumer = new DefaultConsumer(consumerChannel) {
                @Override
                public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] payload) throws IOException {
                    String eventName = envelope.getRoutingKey();
                    try {
                        processConsumerMessage(envelope.getRoutingKey(), payload);
                        consumerChannel.basicAck(envelope.getDeliveryTag(), false);
                    } catch (Exception e) {
                        // TODO: handle exception
                        log.warn("Failed to process event {}: {} {}", eventName, e.getMessage(), new String(payload));
                    }
                }
            };

            try {
                consumerChannel.basicConsume(queueName, autoAck, consumer);
            } catch (Exception e) {
                // TODO: handle exception
                log.error("basicConsume failed {}", e.toString());
            }
        }
    }

    private void processConsumerMessage(String eventName, byte[] payload) throws Exception {
        Class<?> eventClass = subscriptionManager.getEventTypeByName(eventName);
        Object eventInstance = objectMapper.readValue(payload, eventClass);
        List<SubscriptionInfo> subscriptionInfos = subscriptionManager.getHandlersForEvent(eventName);
        try {
            for (SubscriptionInfo subscriptionInfo : subscriptionInfos) {
                Class<?> eventHandlerClass = subscriptionInfo.getHandlerType();
                if (eventHandlerClass != null) {
                    Object eventHandlerInstance;
                    try {
                        // Try to find auto-generated bean for event handler
                        eventHandlerInstance = applicationContext.getBean(eventHandlerClass);
                    } catch (Exception e) {
                        // When no such bean is found, try to create an instance of the event handler type.
                        eventHandlerInstance = eventHandlerClass.getDeclaredConstructor().newInstance();
                    }
                    // Find and execute "handle" method of the event handler instance.
                    Method handleMethod = eventHandlerClass.getMethod("handle", eventClass);
                    Runnable runnable = (Runnable)handleMethod.invoke(eventHandlerInstance, eventInstance);
                    if (runnable != null) {
                        runnable.run();
                    }
                }
            }
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }
}
