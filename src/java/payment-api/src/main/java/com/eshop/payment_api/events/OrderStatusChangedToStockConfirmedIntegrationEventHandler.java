package com.eshop.payment_api.events;

import org.springframework.stereotype.Component;

import com.eshop.eventbus.EventBus;
import com.eshop.eventbus.IntegrationEvent;
import com.eshop.eventbus.IntegrationEventHandler;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class OrderStatusChangedToStockConfirmedIntegrationEventHandler implements IntegrationEventHandler<OrderStatusChangedToStockConfirmedIntegrationEvent> {

    private final EventBus eventBus;

    public OrderStatusChangedToStockConfirmedIntegrationEventHandler(EventBus eventBus) {
        this.eventBus = eventBus;
    }

    @Override
    public Runnable handle(OrderStatusChangedToStockConfirmedIntegrationEvent event) {
        log.info("OrderStatusChangedToStockConfirmedIntegrationEventHandler.handle");
        Runnable runnable = () -> {
            boolean paymentSucceeded = true;
            IntegrationEvent integrationEvent;

            if (paymentSucceeded) {
                integrationEvent = new OrderPaymentSucceededIntegrationEvent(event.getOrderId());
            } else {
                integrationEvent = new OrderPaymentFailedIntegrationEvent(event.getOrderId());
            }
            eventBus.publish(integrationEvent);
        };
        return runnable;
    }
}
