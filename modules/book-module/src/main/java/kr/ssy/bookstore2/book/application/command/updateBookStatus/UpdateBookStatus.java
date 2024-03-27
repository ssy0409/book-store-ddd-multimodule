package kr.ssy.bookstore2.book.application.command.updateBookStatus;

import kr.ssy.bookstore2.book.application.contracts.Command;
import kr.ssy.bookstore2.book.domain.book.enumtype.BookStatus;

public record UpdateBookStatus(

        long id,
        BookStatus status,

        long adminId
) implements Command<UpdateBookStatusResult> {
}
