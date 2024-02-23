package kr.ssy.bookstore2.book.domain.book.events;

import kr.ssy.bookstore2.book.domain.book.Book;
import kr.ssy.bookstore2.buildingblocks.domain.AbstractDomainEvent;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class CreateBookDomainEvent extends AbstractDomainEvent {
    private final Book book;
}
