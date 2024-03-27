package kr.ssy.bookstore2.book.application.command.updateBookStatus;

import kr.ssy.bookstore2.book.application.contracts.Result;

public record UpdateBookStatusResult(long id) implements Result {
}
