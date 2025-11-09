package com.eshop.ordering_signalrhub.events;

import com.eshop.eventbus.IntegrationEventHandler;

public class OrderStatusChangedToPaidIntegrationEventHandler
    implements IntegrationEventHandler<OrderStatusChangedToPaidIntegrationEvent> {

    @Override
    public Runnable handle(OrderStatusChangedToPaidIntegrationEvent event) {
        return null;
    }
}
