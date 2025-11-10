package com.eshop.eventbus;

import java.util.Date;
import java.util.UUID;

import lombok.Data;

@Data
public class IntegrationEvent {
    private UUID Id;
    private Date CreationDate;

    public IntegrationEvent() {
        Id = UUID.randomUUID();
        CreationDate = new Date();
    }
}
