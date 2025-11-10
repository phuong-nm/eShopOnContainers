package com.eshop.eventbus;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.TestComponent;

@TestComponent
public class EventBusSubscriptionManagerTests {

    @Test
    void afterCreationShouldBeEmpty() {
        EventBusSubscriptionManager manager = new EventBusSubscriptionManagerInMemory();
        assertTrue(manager.isEmpty());
    }

    @Test
    void afterOneEventSubscriptionShouldContainTheEvent() {
        EventBusSubscriptionManager manager = new EventBusSubscriptionManagerInMemory();
        manager.addSubscription(TestIntegrationEvent.class, TestIntegrationEventHandler.class);
        assertTrue(manager.hasSubscriptionsForEvent(TestIntegrationEvent.class));
        Class<?> eventClass = manager.getEventTypeByName("TestIntegrationEvent");
        assertEquals(TestIntegrationEvent.class, eventClass);
    }

    @Test
    void afterAllSubscriptionsAreDeletedEventShouldNoLongerExist() {
        EventBusSubscriptionManager manager = new EventBusSubscriptionManagerInMemory();
        manager.addSubscription(TestIntegrationEvent.class, TestIntegrationEventHandler.class);
        manager.removeSubscription(TestIntegrationEvent.class, TestIntegrationEventHandler.class);
        assertFalse(manager.hasSubscriptionsForEvent(TestIntegrationEvent.class));
    }

    @Test
    void deletingLastSubscriptionShouldRaiseOnDeletedEvent() {
        AtomicReference<Boolean> raised = new AtomicReference<>(false);
        EventBusSubscriptionManager manager = new EventBusSubscriptionManagerInMemory();
        manager.onEventRemoved((eventName) -> {
            raised.set(true);
        });
        manager.addSubscription(TestIntegrationEvent.class, TestIntegrationEventHandler.class);
        manager.removeSubscription(TestIntegrationEvent.class, TestIntegrationEventHandler.class);
        assertTrue(raised.get());
    }

    @Test
    void getHandlersForEventShouldReturnAllHandlers() {
        EventBusSubscriptionManager manager = new EventBusSubscriptionManagerInMemory();
        manager.addSubscription(TestIntegrationEvent.class, TestIntegrationEventHandler.class);
        manager.addSubscription(TestIntegrationEvent.class, TestIntegrationEventOtherHandler.class);
        List<SubscriptionInfo> subscriptionInfos = manager.getHandlersForEvent(TestIntegrationEvent.class);
        assertEquals(2, subscriptionInfos.size());
    }
}
