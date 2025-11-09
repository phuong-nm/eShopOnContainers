package com.eshop.ordering_signalrhub.events;

import com.eshop.eventbus.IntegrationEventHandler;

public class OrderStatusChangedToAwaitingValidationIntegrationEventHandler
    implements IntegrationEventHandler<OrderStatusChangedToAwaitingValidationIntegrationEvent> {

    @Override
    public Runnable handle(OrderStatusChangedToAwaitingValidationIntegrationEvent event) {
        return null;
    }
}
