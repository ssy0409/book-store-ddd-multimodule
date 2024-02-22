package kr.ssy.bookstore2.buildingblocks.domain;

import java.time.LocalDateTime;
import java.util.UUID;

public interface DomainEvent {

    UUID getEventId();

    LocalDateTime getOccurredOn();
}
