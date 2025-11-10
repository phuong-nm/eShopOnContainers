package com.eshop.payment_api.events;

import com.eshop.eventbus.IntegrationEvent;
import com.fasterxml.jackson.annotation.JsonFormat;

@JsonFormat(with = { JsonFormat.Feature.ACCEPT_CASE_INSENSITIVE_PROPERTIES })
public class OrderPaymentSucceededIntegrationEvent extends IntegrationEvent {
    private int orderId;


    public OrderPaymentSucceededIntegrationEvent(int orderId) {
        this.orderId = orderId;
    }

    public int getOrderId() {
        return orderId;
    }
}
