package kr.ssy.bookstore2.product.application.contracts;

import kr.ssy.bookstore2.buildingblocks.application.contracts.CqrsQuery;

public interface Query<R extends Result> extends CqrsQuery<R> {

}
