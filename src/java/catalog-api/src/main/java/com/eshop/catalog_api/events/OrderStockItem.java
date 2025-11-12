package com.eshop.catalog_api.events;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class OrderStockItem {
    private int productId;
    private int units;
}
