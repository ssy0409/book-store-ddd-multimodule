package kr.ssy.bookstore2.book.application.command.createbook;

import kr.ssy.bookstore2.book.application.contracts.CommandHandler;
import kr.ssy.bookstore2.book.domain.book.Book;
import kr.ssy.bookstore2.book.domain.book.BookRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CreateBookHandler implements CommandHandler<CreateBook, CreateBookResult> {
    private final BookRepository bookRepository;

    @Override
    public CreateBookResult handle(CreateBook command) {
        var book = Book.create(command.parentId(),
                command.categoryId(),
                command.isbn(),
                command.title(),
                command.author(),
                command.publicationDate(),
                command.publisher(),
                command.thumbnailUrl(),
                command.contents(),
                command.retailPrice(),
                command.salesPrice(),
                command.type(),
                command.salesOpenedAt(),
                command.salesClosedAt(),
                command.bookGenreList(),
                command.adminId(),
                command.quantityAvailable()

        );

        var bookId = bookRepository.save(book);

        return new CreateBookResult(bookId);
    }
}
