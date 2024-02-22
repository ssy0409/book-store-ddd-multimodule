package kr.ssy.bookstore2.admin.domain.admin.events;

import kr.ssy.bookstore2.admin.domain.admin.Admin;
import kr.ssy.bookstore2.buildingblocks.domain.AbstractDomainEvent;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class RegisterAdminDomainEvent extends AbstractDomainEvent {
    private final Admin admin;
}
