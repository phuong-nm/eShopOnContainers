package com.eshop.catalog_api.events;

import java.math.BigDecimal;

import com.eshop.eventbus.IntegrationEvent;
import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@JsonFormat(with = { JsonFormat.Feature.ACCEPT_CASE_INSENSITIVE_PROPERTIES })
public class ProductPriceChangedIntegrationEvent extends IntegrationEvent {
    private int productId;
    private BigDecimal newPrice;
    private BigDecimal oldPrice;
}
