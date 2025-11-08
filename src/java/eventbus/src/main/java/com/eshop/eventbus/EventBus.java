package com.eshop.eventbus;

public interface EventBus {

    /**
     * Publish an integration event.
     * @param event
     */
    void publish(IntegrationEvent event);

    /**
     * Subscribe a concrete integration event and a concrete integration event handler.
     * @param <T> type of the concrete integration event.
     * @param <TH> type of the concrete integration event handler.
     * @param t type of the concrete integration event class.
     * @param th type of the concrete integration event handler class.
     */
    <T extends IntegrationEvent, TH extends IntegrationEventHandler<T>> void subscribe(Class<T> t, Class<TH> th);

    /**
     * Unsubscribe a concrete integration event and a concrete integration event handler.
     * @param <T> type of the concrete integration event.
     * @param <TH> type of the concrete integration event handler.
     * @param t type of the concrete integration event class.
     * @param th type of the concrete integration event handler class.
     */
    <T extends IntegrationEvent, TH extends IntegrationEventHandler<T>> void unsubscribe(Class<T> t, Class<TH> th);

}
