package kr.ssy.bookstore2.admin.application.contracts;

import kr.ssy.bookstore2.buildingblocks.application.contracts.CqrsCommand;

public interface Command<R extends Result> extends CqrsCommand<R> {

}
