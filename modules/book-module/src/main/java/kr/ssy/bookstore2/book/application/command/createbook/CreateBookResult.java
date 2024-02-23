package kr.ssy.bookstore2.book.application.command.createbook;

import kr.ssy.bookstore2.book.application.contracts.Result;

public record CreateBookResult(long id) implements Result {
}
