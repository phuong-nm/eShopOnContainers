package com.eshop.ordering_signalrhub.events;

import com.eshop.eventbus.IntegrationEvent;
import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@JsonFormat(with = { JsonFormat.Feature.ACCEPT_CASE_INSENSITIVE_PROPERTIES })
public class OrderStatusChangedToSubmittedIntegrationEvent extends IntegrationEvent {
    private int orderId;
    private String orderStatus;
    private String buyerName;
}
