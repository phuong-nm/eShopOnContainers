package com.eshop.ordering_signalrhub.events;

import java.util.List;

import com.eshop.eventbus.IntegrationEvent;
import com.eshop.ordering_signalrhub.OrderStockItem;
import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonFormat(with={ JsonFormat.Feature.ACCEPT_CASE_INSENSITIVE_PROPERTIES })
public class OrderStatusChangedToPaidIntegrationEvent extends IntegrationEvent {
    private int OrderId;
    private String OrderStatus;
    private String BuyerName;
    private List<OrderStockItem> orderStockItems;
}

