package com.eshop.ordering_signalrhub.events;

import com.eshop.eventbus.IntegrationEventHandler;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class OrderStatusChangedToPaidIntegrationEventHandler
    implements IntegrationEventHandler<OrderStatusChangedToPaidIntegrationEvent> {

    @Override
    public Runnable handle(OrderStatusChangedToPaidIntegrationEvent event) {
        Runnable runnable = () -> {
            log.info("OrderStatusChangedToPaidIntegrationEventHandler.handle");
        };
        return runnable;
    }
}
