package com.eshop.eventbus;

public class SubscriptionInfo {

    private Class<?> handlerType;

    public SubscriptionInfo(Class<?> handlerType) {
        this.handlerType = handlerType;
    }

    public static SubscriptionInfo typed(Class<?> handlerType) {
        return new SubscriptionInfo(handlerType);
    }

    Class<?> getHandlerType() {
        return handlerType;
    }
}
