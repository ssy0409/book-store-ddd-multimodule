package kr.ssy.bookstore2.book.domain.book.events;

import kr.ssy.bookstore2.book.domain.book.Category;
import kr.ssy.bookstore2.buildingblocks.domain.AbstractDomainEvent;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class CreateCategoryDomainEvent extends AbstractDomainEvent {
    private final Category category;
}
