package kr.ssy.bookstore2.book.application.command.updatesalesprice;

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
public class UpdateSalesPriceHandler implements CommandHandler<UpdateSalesPrice, UpdateSalesPriceResult> {
    private final BookRepository bookRepository;

    @Override
    public UpdateSalesPriceResult handle(UpdateSalesPrice command) {
        var book = Book.updateSalesPrice(command.id(), command.salesPrice(), command.adminId());

        bookRepository.updateSalesPrice(book).orElseThrow(
                () -> new BusinessRuleException(BOOK_ID_NOT_EXISTS)
        );

        return new UpdateSalesPriceResult(command.id());
    }
}
