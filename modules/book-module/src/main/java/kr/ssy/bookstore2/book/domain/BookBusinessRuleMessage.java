package kr.ssy.bookstore2.book.domain;


import kr.ssy.bookstore2.buildingblocks.domain.BusinessRuleMessage;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public enum BookBusinessRuleMessage implements BusinessRuleMessage {
    ADMIN_NOT_EXISTS("admin.notExist");

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
