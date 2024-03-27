package kr.ssy.bookstore2.book.domain;

import kr.ssy.bookstore2.buildingblocks.domain.BusinessRuleMessage;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public enum BookBusinessRuleMessage implements BusinessRuleMessage {
    BOOK_NOT_EXISTS("book.notExist"),
    BOOK_INVALID_PRICE("book.invalid.price"),
    BOOK_INVALID_SALES_PERIOD("book.invalid.sales.period"),
    BOOK_PARENTID_NOT_EXISTS("book,parnetid.not.exists"),
    BOOK_INVALID_NOT_CATEGROY_INDEX("book.invalid.not.category.index"),
    BOOK_CATEGORY_NOT_EXISTS("book.category.not.exists"),
    BOOK_ID_NOT_EXISTS("book.id.not.exists"),
    BOOK_CATEGORY_ID_NOT_EXISTS("book.category.id.not.exists"),
    CATEGORY_ID_NOT_EXISTS("categroy.id.not.exists");

    private final String messageKey;
    private final HttpStatus httpStatus;

    BookBusinessRuleMessage(String messageKey) {
        this.messageKey = messageKey;
        this.httpStatus = HttpStatus.BAD_REQUEST;
    }

    BookBusinessRuleMessage(String messageKey, HttpStatus httpStatus) {
        this.messageKey = messageKey;
        this.httpStatus = httpStatus;
    }

    @Override
    public String getErrorCode() {
        return this.name();
    }

    @Override
    public String getMessageKey() {
        return this.messageKey;
    }

    @Override
    public HttpStatus getHttpStatus() {
        return this.httpStatus;
    }
}
