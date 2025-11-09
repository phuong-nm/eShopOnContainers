package com.eshop.ordering_signalrhub;

import org.springframework.stereotype.Component;

import com.eshop.eventbus.EventBus;
import com.eshop.ordering_signalrhub.events.OrderStatusChangedToAwaitingValidationIntegrationEvent;
import com.eshop.ordering_signalrhub.events.OrderStatusChangedToAwaitingValidationIntegrationEventHandler;
import com.eshop.ordering_signalrhub.events.OrderStatusChangedToCancelledIntegrationEvent;
import com.eshop.ordering_signalrhub.events.OrderStatusChangedToCancelledIntegrationEventHandler;
import com.eshop.ordering_signalrhub.events.OrderStatusChangedToPaidIntegrationEvent;
import com.eshop.ordering_signalrhub.events.OrderStatusChangedToPaidIntegrationEventHandler;
import com.eshop.ordering_signalrhub.events.OrderStatusChangedToShippedIntegrationEvent;
import com.eshop.ordering_signalrhub.events.OrderStatusChangedToShippedIntegrationEventHandler;
import com.eshop.ordering_signalrhub.events.OrderStatusChangedToStockConfirmedIntegrationEvent;
import com.eshop.ordering_signalrhub.events.OrderStatusChangedToStockConfirmedIntegrationEventHandler;
import com.eshop.ordering_signalrhub.events.OrderStatusChangedToSubmittedIntegrationEvent;
import com.eshop.ordering_signalrhub.events.OrderStatusChangedToSubmittedIntegrationEventHandler;

import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class Startup {

    private final EventBus eventBus;

    public Startup(EventBus eventBus) {
        this.eventBus = eventBus;
    }

    @PostConstruct
    private void configureEventBus() {
        log.info("Startup postConstruct");
        eventBus.subscribe(OrderStatusChangedToAwaitingValidationIntegrationEvent.class, OrderStatusChangedToAwaitingValidationIntegrationEventHandler.class);
        eventBus.subscribe(OrderStatusChangedToCancelledIntegrationEvent.class, OrderStatusChangedToCancelledIntegrationEventHandler.class);
        eventBus.subscribe(OrderStatusChangedToPaidIntegrationEvent.class, OrderStatusChangedToPaidIntegrationEventHandler.class);
        eventBus.subscribe(OrderStatusChangedToShippedIntegrationEvent.class, OrderStatusChangedToShippedIntegrationEventHandler.class);
        eventBus.subscribe(OrderStatusChangedToStockConfirmedIntegrationEvent.class, OrderStatusChangedToStockConfirmedIntegrationEventHandler.class);
        eventBus.subscribe(OrderStatusChangedToSubmittedIntegrationEvent.class, OrderStatusChangedToSubmittedIntegrationEventHandler.class);
    }
}
