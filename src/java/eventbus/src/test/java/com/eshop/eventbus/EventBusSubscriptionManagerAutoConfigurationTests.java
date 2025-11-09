package com.eshop.eventbus;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.eshop.eventbus.autoconfig.EventBusSubscriptionManagerAutoConfiguration;

// We must manually tell SpringBootTest to load AutoConfiguration class
@SpringBootTest(classes = EventBusSubscriptionManagerAutoConfiguration.class)
public class EventBusSubscriptionManagerAutoConfigurationTests {

    @Autowired
    private EventBusSubscriptionManager eventBusSubscriptionManager;

    @Test
    void subscriptionManagerShallBeCreated() {
        assertNotNull(eventBusSubscriptionManager);
    }
}
