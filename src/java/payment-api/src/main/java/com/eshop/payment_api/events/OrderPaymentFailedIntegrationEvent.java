package com.eshop.payment_api.events;

import com.eshop.eventbus.IntegrationEvent;
import com.fasterxml.jackson.annotation.JsonFormat;

@JsonFormat(with = { JsonFormat.Feature.ACCEPT_CASE_INSENSITIVE_PROPERTIES })
public class OrderPaymentFailedIntegrationEvent extends IntegrationEvent {
    private int orderId;


    public OrderPaymentFailedIntegrationEvent(int orderId) {
        this.orderId = orderId;
    }

    public int getOrderId() {
        return orderId;
    }
}
