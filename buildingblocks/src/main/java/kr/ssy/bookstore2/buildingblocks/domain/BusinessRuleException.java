package kr.ssy.bookstore2.buildingblocks.domain;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class BusinessRuleException extends RuntimeException {

    private final transient BusinessRuleMessage businessRuleMessage;
    private final String[] messageArguments;

    public BusinessRuleException(BusinessRuleMessage businessRuleMessage,
            String... messageArguments) {
        super(businessRuleMessage.getErrorCode());
        this.businessRuleMessage = businessRuleMessage;
        this.messageArguments = messageArguments;
    }

    public String getCode() {
        return businessRuleMessage.getErrorCode();
    }

    @Override
    public String getMessage() {
        return getCode();
    }

    public String getMessageKey() {
        return businessRuleMessage.getMessageKey();
    }

    public HttpStatus getHttpStatus() {
        return businessRuleMessage.getHttpStatus();
    }
}
