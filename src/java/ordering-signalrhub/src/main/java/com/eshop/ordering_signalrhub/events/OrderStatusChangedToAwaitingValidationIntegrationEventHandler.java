package com.eshop.ordering_signalrhub.events;

import com.eshop.eventbus.IntegrationEventHandler;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class OrderStatusChangedToAwaitingValidationIntegrationEventHandler
    implements IntegrationEventHandler<OrderStatusChangedToAwaitingValidationIntegrationEvent> {

    @Override
    public Runnable handle(OrderStatusChangedToAwaitingValidationIntegrationEvent event) {
        Runnable runnable = () -> {
            log.info("OrderStatusChangedToAwaitingValidationIntegrationEventHandler.handle");
        };
        return runnable;
    }
}
