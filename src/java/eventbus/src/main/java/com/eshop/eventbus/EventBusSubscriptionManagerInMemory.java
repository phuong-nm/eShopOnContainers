package com.eshop.eventbus;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Predicate;

public class EventBusSubscriptionManagerInMemory implements EventBusSubscriptionManager {

    private HashMap<String, List<SubscriptionInfo>> eventHandlers;
    private List<Class<?>> eventTypes;
    private Consumer<String> onEventRemovedHandler;

    public EventBusSubscriptionManagerInMemory() {
        eventHandlers = new HashMap<>();
        eventTypes = new ArrayList<>();
    }

    @Override
    public boolean isEmpty() {
        return eventHandlers.isEmpty();
    }

    @Override
    public void onEventRemoved(Consumer<String> handler) {
        onEventRemovedHandler = handler;
    }

    @Override
    public <T extends IntegrationEvent, TH extends IntegrationEventHandler<T>> void addSubscription(Class<T> t, Class<TH> th) {
        doAddSubscription(t, th);
        if (!eventTypes.contains(t)) {
            eventTypes.add(t);
        }
    }

    @Override
    public <T extends IntegrationEvent, TH extends IntegrationEventHandler<T>> void removeSubscription(Class<T> t, Class<TH> th) {
        String key = getEventKey(t);
        SubscriptionInfo subscriptionInfo = findSubscriptionToRemove(t, th);
        doRemoveHandler(th, key, subscriptionInfo);
    }

    @Override
    public <T extends IntegrationEvent> boolean hasSubscriptionsForEvent(Class<T> t) {
        return hasSubscriptionsForEvent(getEventKey(t));
    }

    @Override
    public <T extends IntegrationEvent> boolean hasSubscriptionsForEvent(String eventName) {
        return eventHandlers.containsKey(eventName);
    }

    @Override
    public Class<?> getEventTypeByName(String eventName) {
        Predicate<Class<?>> condition = (s) -> {
            return s.getSimpleName().compareTo(eventName) == 0;
        };
        return eventTypes.stream().filter(condition).findFirst().get();
    }

    @Override
    public void clear() {
        eventHandlers.clear();
    }

    @Override
    public <T extends IntegrationEvent> List<SubscriptionInfo> getHandlersForEvent(Class<T> t) {
        String key = getEventKey(t);
        return getHandlersForEvent(key);
    }

    @Override
    public <T extends IntegrationEvent> List<SubscriptionInfo> getHandlersForEvent(String eventName) {
        return eventHandlers.get(eventName);
    }

    @Override
    public <T extends IntegrationEvent> String getEventKey(Class<T> t) {
        return t.getSimpleName();
    }

    private <T extends IntegrationEvent, TH extends IntegrationEventHandler<T>> void doAddSubscription(Class<T> t, Class<TH> th) {
        String key = getEventKey(t);
        if (!hasSubscriptionsForEvent(t)) {
            eventHandlers.put(key, new ArrayList<SubscriptionInfo>());
        }
        SubscriptionInfo subscriptionInfo = SubscriptionInfo.typed(th);
        if (!eventHandlers.get(key).contains(subscriptionInfo)) {
            eventHandlers.get(key).add(subscriptionInfo);
        } else {
            // TODO throw exception ("Handler type " +  th.getSimpleName() + "already registered for " + t.getSimpleName());
        }
    }

    private <T extends IntegrationEvent, TH extends IntegrationEventHandler<T>> void doRemoveHandler(Class<TH> th, String eventName, SubscriptionInfo subscriptionToRemove) {
        if (subscriptionToRemove != null) {
            eventHandlers.get(eventName).remove(subscriptionToRemove);
            if (eventHandlers.get(eventName).isEmpty()) {
                eventHandlers.remove(eventName);
                if (eventTypes.contains(th)) {
                    eventTypes.remove(th);
                }
                raiseOnEventRemoved(eventName);
            }
        }
    }

    private <T extends IntegrationEvent, TH extends IntegrationEventHandler<T>> SubscriptionInfo findSubscriptionToRemove(Class<T> t, Class<TH> th) {
        String eventName = getEventKey(t);
        if (!hasSubscriptionsForEvent(t)) {
            return null;
        }
        Predicate<SubscriptionInfo> condition = s -> s.getHandlerType() == th;
        return eventHandlers.get(eventName).stream().filter(condition).findFirst().get();
    }

    private void raiseOnEventRemoved(String eventName) {
        if (onEventRemovedHandler != null) {
            onEventRemovedHandler.accept(eventName);
        }
    }
}
