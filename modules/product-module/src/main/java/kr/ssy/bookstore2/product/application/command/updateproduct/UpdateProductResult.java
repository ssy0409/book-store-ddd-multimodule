package kr.ssy.bookstore2.product.application.command.updateproduct;

import kr.ssy.bookstore2.product.application.contracts.Result;

public record UpdateProductResult(long id) implements Result {
}
