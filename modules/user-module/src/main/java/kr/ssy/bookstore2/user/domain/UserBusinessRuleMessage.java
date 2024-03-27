package kr.ssy.bookstore2.user.domain;

import kr.ssy.bookstore2.buildingblocks.domain.BusinessRuleMessage;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public enum UserBusinessRuleMessage implements BusinessRuleMessage {
    USER_NOT_EXISTS("user.notExist");

    private final String messageKey;
    private final HttpStatus httpStatus;

    UserBusinessRuleMessage(String messageKey) {
        this.messageKey = messageKey;
        this.httpStatus = HttpStatus.BAD_REQUEST;
    }

    UserBusinessRuleMessage(String messageKey, HttpStatus httpStatus) {
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
