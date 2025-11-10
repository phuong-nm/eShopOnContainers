package com.eshop.ordering_signalrhub.events;

import com.eshop.eventbus.IntegrationEventHandler;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class OrderStatusChangedToCancelledIntegrationEventHandler
    implements IntegrationEventHandler<OrderStatusChangedToCancelledIntegrationEvent> {

    @Override
    public Runnable handle(OrderStatusChangedToCancelledIntegrationEvent event) {
        Runnable runnable = () -> {
            log.info("OrderStatusChangedToCancelledIntegrationEventHandler.handle");
        };
        return runnable;
    }
}
