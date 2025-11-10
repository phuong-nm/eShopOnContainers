package com.eshop.ordering_signalrhub;

import java.io.IOException;
import java.lang.reflect.Method;
import java.util.List;

import org.springframework.amqp.core.ExchangeTypes;
import org.springframework.amqp.rabbit.connection.Connection;

import com.eshop.eventbus.EventBus;
import com.eshop.eventbus.EventBusSubscriptionManager;
import com.eshop.eventbus.IntegrationEvent;
import com.eshop.eventbus.IntegrationEventHandler;
import com.eshop.eventbus.SubscriptionInfo;
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

    private Connection connection;
    private EventBusSubscriptionManager subscriptionManager;
    private String queueName;
    private Channel consumerChannel;
    private Channel producerChannel;
    private Consumer consumer;
    private final ObjectMapper objectMapper;
    private final boolean autoAck = true;

    public EventBusRabbitMq(Connection connection, EventBusSubscriptionManager subscriptionManager, String queueName) {
        this.connection = connection;
        this.subscriptionManager = subscriptionManager;
        this.queueName = queueName;
        this.consumerChannel = createConsumerChannel();
        this.producerChannel = createProducerChannel();
        this.objectMapper = new ObjectMapper();
    }

    @Override
    public void publish(IntegrationEvent event) {
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
                    log.info("handleDelivery {} {}", envelope.getRoutingKey(), new String(payload));
                    String eventName = envelope.getRoutingKey();
                    Class<?> eventClass = subscriptionManager.getEventTypeByName(eventName);
                    Object eventInstance = objectMapper.readValue(payload, eventClass);
                    List<SubscriptionInfo> subscriptionInfos = subscriptionManager.getHandlersForEvent(eventName);
                    if (subscriptionInfos != null) {
                        subscriptionInfos.forEach((s) -> {
                            Class<?> eventHandlerClass = s.getHandlerType();
                            try {
                                Object eventHandlerInstance = eventHandlerClass.getDeclaredConstructor().newInstance();
                                Method handleMethod = eventHandlerClass.getMethod("handle", eventClass);
                                Runnable runnable = (Runnable)handleMethod.invoke(eventHandlerInstance, eventInstance);
                                if (runnable != null) {
                                    runnable.run();
                                }
                            } catch (Exception e) {
                                // TODO: handle exception
                                log.error("Failed to process event {}", e.toString());
                            }
                        });
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
}
