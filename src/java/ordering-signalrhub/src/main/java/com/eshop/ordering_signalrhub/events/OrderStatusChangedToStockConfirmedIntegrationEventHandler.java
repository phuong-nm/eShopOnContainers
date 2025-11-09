package com.eshop.ordering_signalrhub.events;

import com.eshop.eventbus.IntegrationEventHandler;

public class OrderStatusChangedToStockConfirmedIntegrationEventHandler
    implements IntegrationEventHandler<OrderStatusChangedToStockConfirmedIntegrationEvent> {

    @Override
    public Runnable handle(OrderStatusChangedToStockConfirmedIntegrationEvent event) {
        return null;
    }
}
