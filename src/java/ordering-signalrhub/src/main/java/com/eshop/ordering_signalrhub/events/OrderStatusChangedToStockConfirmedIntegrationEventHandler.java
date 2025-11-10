package com.eshop.ordering_signalrhub.events;

import com.eshop.eventbus.IntegrationEventHandler;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class OrderStatusChangedToStockConfirmedIntegrationEventHandler
    implements IntegrationEventHandler<OrderStatusChangedToStockConfirmedIntegrationEvent> {

    @Override
    public Runnable handle(OrderStatusChangedToStockConfirmedIntegrationEvent event) {
        Runnable runnable = () -> {
            log.info("OrderStatusChangedToStockConfirmedIntegrationEventHandler.handle");
        };
        return runnable;
    }
}
