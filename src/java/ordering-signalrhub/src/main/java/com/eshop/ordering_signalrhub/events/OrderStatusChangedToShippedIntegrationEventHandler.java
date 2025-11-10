package com.eshop.ordering_signalrhub.events;

import com.eshop.eventbus.IntegrationEventHandler;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class OrderStatusChangedToShippedIntegrationEventHandler
    implements IntegrationEventHandler<OrderStatusChangedToShippedIntegrationEvent> {

    @Override
    public Runnable handle(OrderStatusChangedToShippedIntegrationEvent event) {
        Runnable runnable = () -> {
            log.info("OrderStatusChangedToShippedIntegrationEventHandler.handle");
        };
        return runnable;
    }
}
