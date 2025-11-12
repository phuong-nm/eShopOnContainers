package com.eshop.catalog_api.events;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;

import org.springframework.stereotype.Component;

import com.eshop.catalog_api.entities.CatalogItem;
import com.eshop.catalog_api.repositories.CatalogRepository;
import com.eshop.eventbus.EventBus;
import com.eshop.eventbus.IntegrationEvent;
import com.eshop.eventbus.IntegrationEventHandler;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class OrderStatusChangedToAwaitingValidationIntegrationEventHandler implements IntegrationEventHandler<OrderStatusChangedToAwaitingValidationIntegrationEvent> {

    private EventBus eventBus;
    private CatalogRepository catalogRepository;

    public OrderStatusChangedToAwaitingValidationIntegrationEventHandler(EventBus eventBus, CatalogRepository catalogRepository) {
        this.eventBus = eventBus;
        this.catalogRepository = catalogRepository;
    }

    @Override
    public Runnable handle(OrderStatusChangedToAwaitingValidationIntegrationEvent event) {
        log.info("OrderStatusChangedToAwaitingValidationIntegrationEventHandler.handle");
        Runnable runnable = () -> {
            List<ConfirmedOrderStockItem> confirmedOrderStockItems = new ArrayList<>();

            for (OrderStockItem orderStockItem : event.getOrderStockItems()) {
                Optional<CatalogItem> catalogItem = catalogRepository.findById(orderStockItem.getProductId());
                if (catalogItem.isPresent()) {
                    boolean hasStock = catalogItem.get().getAvailableStock() >= orderStockItem.getUnits();
                    ConfirmedOrderStockItem confirmedOrderStockItem = new ConfirmedOrderStockItem(catalogItem.get().getId(), hasStock);
                    confirmedOrderStockItems.add(confirmedOrderStockItem);
                }
            }

            IntegrationEvent eventToPublish;
            Predicate<ConfirmedOrderStockItem> condition = (s) -> {
                return !s.isHasStock();
            };
            if (confirmedOrderStockItems.stream().anyMatch(condition)) {
                eventToPublish = new OrderStockRejectedIntegrationEvent(event.getOrderId(), confirmedOrderStockItems);
            } else {
                eventToPublish = new OrderStockConfirmedIntegrationEvent(event.getOrderId());
            }
            eventBus.publish(eventToPublish);
        };
        return runnable;
    }
}
