package kr.ssy.bookstore2.buildingblocks.application.contracts;

public interface CqrsCommandHandler<C extends CqrsCommand<R>, R extends CqrsResult> extends
        CqrsHandler<C, R> {

}
