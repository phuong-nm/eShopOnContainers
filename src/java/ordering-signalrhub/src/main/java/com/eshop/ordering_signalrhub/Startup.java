package com.eshop.ordering_signalrhub;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.eshop.eventbus.EventBus;
import com.eshop.ordering_signalrhub.events.OrderStatusChangedToAwaitingValidationIntegrationEvent;
import com.eshop.ordering_signalrhub.events.OrderStatusChangedToAwaitingValidationIntegrationEventHandler;

import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;

// configure service: connectionFactory
// registerEventBus
//      - create eventBusRabbitMq
// configureEventBus
//      - subscribe event and event handler

@Slf4j
@Component
public class Startup {

    // Let creation of eventBus take place before @PostConstruct
    @Autowired
    private EventBus eventBus;

    @PostConstruct
    private void postConstruct() {
        log.info("Startup postConstruct");
        eventBus.subscribe(OrderStatusChangedToAwaitingValidationIntegrationEvent.class, OrderStatusChangedToAwaitingValidationIntegrationEventHandler.class);
    }
}
