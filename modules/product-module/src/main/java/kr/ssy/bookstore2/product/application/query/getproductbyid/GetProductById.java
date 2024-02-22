package kr.ssy.bookstore2.product.application.query.getproductbyid;

import kr.ssy.bookstore2.product.application.contracts.Query;

public record GetProductById(long id) implements Query<GetProductByIdResult> {
}
