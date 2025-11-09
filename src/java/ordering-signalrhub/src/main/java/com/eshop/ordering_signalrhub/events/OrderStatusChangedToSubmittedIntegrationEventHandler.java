package com.eshop.ordering_signalrhub.events;

import com.eshop.eventbus.IntegrationEventHandler;

public class OrderStatusChangedToSubmittedIntegrationEventHandler
    implements IntegrationEventHandler<OrderStatusChangedToSubmittedIntegrationEvent> {

    @Override
    public Runnable handle(OrderStatusChangedToSubmittedIntegrationEvent event) {
        return null;
    }
}
