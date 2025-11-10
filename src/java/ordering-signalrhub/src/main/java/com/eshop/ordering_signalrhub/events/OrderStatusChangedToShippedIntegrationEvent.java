package com.eshop.ordering_signalrhub.events;

import com.eshop.eventbus.IntegrationEvent;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderStatusChangedToShippedIntegrationEvent extends IntegrationEvent {
    private int OrderId;
    private String OrderStatus;
    private String BuyerName;
}
