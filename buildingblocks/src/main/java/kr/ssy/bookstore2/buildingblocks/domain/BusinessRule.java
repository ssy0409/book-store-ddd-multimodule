package kr.ssy.bookstore2.buildingblocks.domain;

public interface BusinessRule {

    boolean isBroken();

    BusinessRuleMessage getMessage();
}
