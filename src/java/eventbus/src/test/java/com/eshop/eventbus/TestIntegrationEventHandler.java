package com.eshop.eventbus;

public class TestIntegrationEventHandler implements IntegrationEventHandler<TestIntegrationEvent> {

    private boolean handled = false;

    boolean getHandled() {
        return handled;
    }

    @Override
    public Runnable handle(TestIntegrationEvent event) {
        handled = true;
        return () -> {
            // empty lambda function
        };
    }
}
