package com.eshop.ordering_signalrhub.events;

import com.eshop.eventbus.IntegrationEvent;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderStatusChangedToSubmittedIntegrationEvent extends IntegrationEvent {
    private int OrderId;
    private String OrderStatus;
    private String BuyerName;
}
