package com.eshop.ordering_signalrhub;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderStockItem {
    private int productId;
    private int units;
}
