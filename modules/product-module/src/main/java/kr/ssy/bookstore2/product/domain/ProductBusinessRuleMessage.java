package kr.ssy.bookstore2.product.domain;

import kr.ssy.bookstore2.buildingblocks.domain.BusinessRuleMessage;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public enum ProductBusinessRuleMessage implements BusinessRuleMessage {
    PRODUCT_NOT_EXISTS("product.notExist"),
    PRODUCT_CONTENTS_NOT_EXISTS("product.contents.notExist"),
    PRODUCT_CONTENTS_INVALID("product.contents.invalid.mainContentsCount"),
    PRODUCT_INVALID_PRICE("product.invalid.price"),
    PRODUCT_INVALID_SALES_PERIOD("product.invalid.salesPeriod"),
    PRODUCT_CANNOT_START_ON_SALE("product.invalid.cannotStartOnSale"),
    PRODUCT_ONLY_EDITABLE_STATUS_IN_DRAFTED("product.invalid.onlyEditableStatusInDrafted"),
    CONTENTS_NOT_EXISTS("contents.notExist");

    private final String messageKey;
    private final HttpStatus httpStatus;

    ProductBusinessRuleMessage(String messageKey) {
        this.messageKey = messageKey;
        this.httpStatus = HttpStatus.BAD_REQUEST;
    }

    ProductBusinessRuleMessage(String messageKey, HttpStatus httpStatus) {
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
