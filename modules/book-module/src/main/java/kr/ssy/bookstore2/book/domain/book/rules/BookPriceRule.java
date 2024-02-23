package kr.ssy.bookstore2.book.domain.book.rules;

import kr.ssy.bookstore2.book.domain.BookBusinessRuleMessage;
import kr.ssy.bookstore2.book.domain.book.Book;
import kr.ssy.bookstore2.buildingblocks.domain.BusinessRule;
import kr.ssy.bookstore2.buildingblocks.domain.BusinessRuleMessage;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class BookPriceRule implements BusinessRule {

    private final Book book;

    @Override
    public boolean isBroken() {
        if (book.getRetailPrice() == null || book.getSalesPrice() == null) {
            return true;
        }

        return book.getRetailPrice().compareTo(book.getSalesPrice()) < 0;
    }

    @Override
    public BusinessRuleMessage getMessage() {
        return BookBusinessRuleMessage.BOOK_INVALID_PRICE;
    }
}
