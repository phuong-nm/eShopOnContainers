package com.eshop.ordering_signalrhub.events;

import java.util.List;

import com.eshop.eventbus.IntegrationEvent;
import com.fasterxml.jackson.annotation.JsonFormat;

@JsonFormat(with = { JsonFormat.Feature.ACCEPT_CASE_INSENSITIVE_PROPERTIES })
public class OrderStatusChangedToShippedIntegrationEvent extends IntegrationEvent {
    private int orderId;
    private String orderStatus;
    private String buyerName;

    public OrderStatusChangedToShippedIntegrationEvent(int orderId, String orderStatus, String buyerName, List<OrderStockItem> orderStockItems) {
        this.orderId = orderId;
        this.orderStatus = orderStatus;
        this.buyerName = buyerName;
    }

    public int getOrderId() {
        return orderId;
    }

    public String getOrderStatus() {
        return orderStatus;
    }

    public String getBuyerName() {
        return buyerName;
    }
}
