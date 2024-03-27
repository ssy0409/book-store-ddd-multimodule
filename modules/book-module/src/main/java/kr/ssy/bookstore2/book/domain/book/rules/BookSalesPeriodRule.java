package kr.ssy.bookstore2.book.domain.book.rules;

import kr.ssy.bookstore2.book.domain.BookBusinessRuleMessage;
import kr.ssy.bookstore2.book.domain.book.Book;
import kr.ssy.bookstore2.buildingblocks.domain.BusinessRule;
import kr.ssy.bookstore2.buildingblocks.domain.BusinessRuleMessage;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class BookSalesPeriodRule implements BusinessRule {

    private final Book book;

    @Override
    public boolean isBroken() {
        if (book.getSalesOpenedAt() == null) {
            return true;
        }

        if (book.getSalesClosedAt() == null) { // unlimited
            return false;
        }

        return book.getSalesOpenedAt().isAfter(book.getSalesClosedAt())
                || book.getSalesOpenedAt().isEqual(book.getSalesClosedAt());
    }

    @Override
    public BusinessRuleMessage getMessage() {
        return BookBusinessRuleMessage.BOOK_INVALID_SALES_PERIOD;
    }
}
