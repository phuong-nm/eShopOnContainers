package com.eshop.ordering_signalrhub.events;

import java.util.List;

import com.eshop.eventbus.IntegrationEvent;
import com.eshop.ordering_signalrhub.OrderStockItem;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderStatusChangedToPaidIntegrationEvent extends IntegrationEvent {
    private int OrderId;
    private String OrderStatus;
    private String BuyerName;
    private List<OrderStockItem> orderStockItems;
}

