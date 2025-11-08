package com.eshop.eventbus;

import java.util.Date;
import java.util.UUID;

import lombok.Getter;

@Getter
public class IntegrationEvent {
    UUID id;
    Date creationDate;

    public IntegrationEvent() {
        id = UUID.randomUUID();
        creationDate = new Date();
    }
}
