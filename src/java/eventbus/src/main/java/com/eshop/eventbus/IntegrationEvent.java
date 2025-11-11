package com.eshop.eventbus;

import java.util.Date;
import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Getter;

@Getter
@JsonFormat(with = { JsonFormat.Feature.ACCEPT_CASE_INSENSITIVE_PROPERTIES })
public class IntegrationEvent {
    private UUID id;

    @JsonFormat(
        shape = JsonFormat.Shape.STRING,
        pattern = "yyyy-MM-dd'T'HH:mm:ss.SSSSSSS'Z'",
        timezone = "UTC"
    )
    private Date creationDate;

    public IntegrationEvent() {
        id = UUID.randomUUID();
        creationDate = new Date();
    }
}
