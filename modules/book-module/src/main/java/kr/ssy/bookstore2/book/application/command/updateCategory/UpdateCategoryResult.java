package kr.ssy.bookstore2.book.application.command.updateCategory;

import kr.ssy.bookstore2.book.application.contracts.Result;

public record UpdateCategoryResult(long orderIndex) implements Result {
}
