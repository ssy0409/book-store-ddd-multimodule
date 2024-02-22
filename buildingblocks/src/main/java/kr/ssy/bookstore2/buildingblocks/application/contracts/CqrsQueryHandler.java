package kr.ssy.bookstore2.buildingblocks.application.contracts;

public interface CqrsQueryHandler<Q extends CqrsQuery<R>, R extends CqrsResult> extends
        CqrsHandler<Q, R> {

    R handle(Q query);
}
