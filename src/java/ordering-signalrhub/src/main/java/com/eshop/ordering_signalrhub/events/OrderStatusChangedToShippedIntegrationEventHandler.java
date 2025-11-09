package com.eshop.ordering_signalrhub.events;

import com.eshop.eventbus.IntegrationEventHandler;

public class OrderStatusChangedToShippedIntegrationEventHandler
    implements IntegrationEventHandler<OrderStatusChangedToShippedIntegrationEvent> {

    @Override
    public Runnable handle(OrderStatusChangedToShippedIntegrationEvent event) {
        return null;
    }
}
