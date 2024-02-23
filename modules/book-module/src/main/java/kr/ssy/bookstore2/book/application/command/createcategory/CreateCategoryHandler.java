package kr.ssy.bookstore2.book.application.command.createcategory;

import kr.ssy.bookstore2.book.application.contracts.CommandHandler;
import kr.ssy.bookstore2.book.domain.book.BookRepository;
import kr.ssy.bookstore2.book.domain.book.Category;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CreateCategoryHandler implements CommandHandler<CreateCategory, CreateCategoryResult> {
    private final BookRepository bookRepository;

    @Override
    public CreateCategoryResult handle(CreateCategory command) {
        var category = Category.create(command.parent_id(),
                command.name(),
                command.orderIndex()
        );

        var result = bookRepository.insertCategory(category);

        return null;
    }
}
