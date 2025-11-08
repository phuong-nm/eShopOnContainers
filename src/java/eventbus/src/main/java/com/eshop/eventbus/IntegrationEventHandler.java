package com.eshop.eventbus;

public interface IntegrationEventHandler<T extends IntegrationEvent> {

    /**
     *
     * @param event
     * @return
     */
    Runnable handle(T event);

}
