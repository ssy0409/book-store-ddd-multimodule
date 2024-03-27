package kr.ssy.bookstore2.book.application.command.createbook;

import kr.ssy.bookstore2.book.application.contracts.Command;
import kr.ssy.bookstore2.book.domain.book.enumtype.BookGenre;
import kr.ssy.bookstore2.book.domain.book.enumtype.BookStatus;
import kr.ssy.bookstore2.book.domain.book.enumtype.BookType;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

public record CreateBook(

        long parentId,
        long categoryId,

        String isbn,

        String title,

        String author,

        LocalDate publicationDate,

        String publisher,

        String thumbnailUrl,

        String contents,

        BigDecimal retailPrice,

        BigDecimal salesPrice,

        BookType type,

        LocalDate salesOpenedAt,

        LocalDate salesClosedAt,

        BookStatus status,

        List<BookGenre> bookGenreList,

        int quantityAvailable,
        long adminId
) implements Command<CreateBookResult> {
}
