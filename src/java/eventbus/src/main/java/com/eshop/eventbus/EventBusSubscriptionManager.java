package com.eshop.eventbus;

import java.util.List;
import java.util.function.Consumer;

public interface EventBusSubscriptionManager {

    boolean isEmpty();

    void onEventRemoved(Consumer<String> handler);

    <T extends IntegrationEvent, TH extends IntegrationEventHandler<T>> void addSubscription(Class<T> t, Class<TH> th);

    <T extends IntegrationEvent, TH extends IntegrationEventHandler<T>> void removeSubscription(Class<T> t, Class<TH> th);

    <T extends IntegrationEvent> boolean hasSubscriptionsForEvent(Class<T> t);

    <T extends IntegrationEvent> boolean hasSubscriptionsForEvent(String eventName);

    Class<?> getEventTypeByName(String eventName);

    void clear();

    <T extends IntegrationEvent> List<SubscriptionInfo> getHandlersForEvent(Class<T> t);

    <T extends IntegrationEvent> List<SubscriptionInfo> getHandlersForEvent(String eventName);

    <T extends IntegrationEvent> String getEventKey(Class<T> t);
}

