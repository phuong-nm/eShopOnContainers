package com.eshop.ordering_signalrhub.events;

import com.eshop.eventbus.IntegrationEventHandler;

public class OrderStatusChangedToCancelledIntegrationEventHandler
    implements IntegrationEventHandler<OrderStatusChangedToCancelledIntegrationEvent> {

    @Override
    public Runnable handle(OrderStatusChangedToCancelledIntegrationEvent event) {
        return null;
    }
}
