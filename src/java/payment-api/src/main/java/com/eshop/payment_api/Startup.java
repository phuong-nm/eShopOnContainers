package com.eshop.payment_api;

import org.springframework.stereotype.Component;

import com.eshop.eventbus.EventBus;
import com.eshop.payment_api.events.OrderStatusChangedToStockConfirmedIntegrationEvent;
import com.eshop.payment_api.events.OrderStatusChangedToStockConfirmedIntegrationEventHandler;

import jakarta.annotation.PostConstruct;

@Component
public class Startup {

    private final EventBus eventBus;

    public Startup(EventBus eventBus) {
        this.eventBus = eventBus;
    }

    @PostConstruct
    private void configureEventBus() {
        eventBus.subscribe(OrderStatusChangedToStockConfirmedIntegrationEvent.class, OrderStatusChangedToStockConfirmedIntegrationEventHandler.class);
    }
}
