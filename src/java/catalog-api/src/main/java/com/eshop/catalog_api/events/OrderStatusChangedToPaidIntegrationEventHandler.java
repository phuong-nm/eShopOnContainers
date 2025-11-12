package com.eshop.catalog_api.events;

import java.util.Optional;

import org.springframework.stereotype.Component;

import com.eshop.catalog_api.entities.CatalogItem;
import com.eshop.catalog_api.repositories.CatalogRepository;
import com.eshop.eventbus.IntegrationEventHandler;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class OrderStatusChangedToPaidIntegrationEventHandler implements IntegrationEventHandler<OrderStatusChangedToPaidIntegrationEvent> {

    private CatalogRepository catalogRepository;

    public OrderStatusChangedToPaidIntegrationEventHandler(CatalogRepository catalogRepository) {
        this.catalogRepository = catalogRepository;
    }

    @Override
    public Runnable handle(OrderStatusChangedToPaidIntegrationEvent event) {
        log.info("OrderStatusChangedToPaidIntegrationEventHandler.handle");
        Runnable runnable = () -> {
            for (OrderStockItem orderStockItem : event.getOrderStockItems()) {
                Optional<CatalogItem> catalogItem = catalogRepository.findById(orderStockItem.getProductId());
                if (catalogItem.isPresent()) {
                    try {
                        catalogItem.get().removeStock(orderStockItem.getUnits());
                    } catch (Exception e) {
                        log.warn("Failed to remove stock: {}", e.getMessage());
                    }
                }
            }
        };
        return runnable;
    }
}
