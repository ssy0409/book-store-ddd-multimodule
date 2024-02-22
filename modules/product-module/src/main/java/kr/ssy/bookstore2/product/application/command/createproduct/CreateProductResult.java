package kr.ssy.bookstore2.product.application.command.createproduct;

import kr.ssy.bookstore2.product.application.contracts.Result;

public record CreateProductResult(long id) implements Result {
}
