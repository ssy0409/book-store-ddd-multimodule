package kr.ssy.bookstore2.admin.domain.user.events;


import kr.ssy.bookstore2.admin.domain.user.Client;
import kr.ssy.bookstore2.buildingblocks.domain.AbstractDomainEvent;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class RegisterClientDomainEvent extends AbstractDomainEvent {
    private final Client client;
}
