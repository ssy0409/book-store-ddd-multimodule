package kr.ssy.bookstore2.order.application.contracts;

import kr.ssy.bookstore2.buildingblocks.application.contracts.CqrsCommandHandler;

public interface CommandHandler<C extends Command<R>, R extends Result> extends
        CqrsCommandHandler<C, R> {

}
