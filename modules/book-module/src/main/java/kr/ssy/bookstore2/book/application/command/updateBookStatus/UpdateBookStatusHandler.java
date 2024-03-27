package kr.ssy.bookstore2.book.application.command.updateBookStatus;

import kr.ssy.bookstore2.book.application.contracts.CommandHandler;
import kr.ssy.bookstore2.book.domain.book.Book;
import kr.ssy.bookstore2.book.domain.book.BookRepository;
import kr.ssy.bookstore2.buildingblocks.domain.BusinessRuleException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import static kr.ssy.bookstore2.book.domain.BookBusinessRuleMessage.BOOK_ID_NOT_EXISTS;

@Slf4j
@Service
@RequiredArgsConstructor
public class UpdateBookStatusHandler implements CommandHandler<UpdateBookStatus, UpdateBookStatusResult> {
    private final BookRepository bookRepository;

    @Override
    public UpdateBookStatusResult handle(UpdateBookStatus command) {

        var updateBook = Book.updateStatus(command.id(), command.status(), command.adminId());
        bookRepository.updateBookStatus(updateBook).ifPresentOrElse(
                res -> {
                    if (res < 1) {
                        throw new BusinessRuleException(BOOK_ID_NOT_EXISTS);
                    }
                },
                () -> {
                    throw new BusinessRuleException(BOOK_ID_NOT_EXISTS);
                }
        );

        return new UpdateBookStatusResult(command.id());
    }

}
