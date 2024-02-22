package kr.ssy.bookstore2.order.application.contracts;

import kr.ssy.bookstore2.buildingblocks.application.contracts.CqrsQueryHandler;

public interface QueryHandler<Q extends Query<R>, R extends Result> extends CqrsQueryHandler<Q, R> {

}
