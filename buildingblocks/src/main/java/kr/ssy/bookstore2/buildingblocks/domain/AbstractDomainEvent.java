package kr.ssy.bookstore2.buildingblocks.domain;

import lombok.Getter;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
public abstract class AbstractDomainEvent implements DomainEvent {

    private final UUID eventId;
    private final LocalDateTime occurredOn;

    protected AbstractDomainEvent() {
        eventId = UUID.randomUUID();
        occurredOn = LocalDateTime.now();
    }
}
