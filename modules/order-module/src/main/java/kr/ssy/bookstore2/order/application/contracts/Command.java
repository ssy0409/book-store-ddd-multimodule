package kr.ssy.bookstore2.order.application.contracts;

import kr.ssy.bookstore2.buildingblocks.application.contracts.CqrsCommand;

public interface Command<R extends Result> extends CqrsCommand<R> {

}
