package com.eshop.ordering_signalrhub.events;

import com.eshop.eventbus.IntegrationEventHandler;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class OrderStatusChangedToSubmittedIntegrationEventHandler
    implements IntegrationEventHandler<OrderStatusChangedToSubmittedIntegrationEvent> {

    @Override
    public Runnable handle(OrderStatusChangedToSubmittedIntegrationEvent event) {
        Runnable runnable = () -> {
            log.info("OrderStatusChangedToSubmittedIntegrationEventHandler.handle");
        };
        return runnable;
    }
}
