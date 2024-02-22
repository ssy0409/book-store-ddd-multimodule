package kr.ssy.bookstore2.buildingblocks.domain;

import org.springframework.http.HttpStatus;

public interface BusinessRuleMessage {


    String getErrorCode();

    String getMessageKey();

    HttpStatus getHttpStatus();
}
