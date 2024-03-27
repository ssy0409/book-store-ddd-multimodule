package kr.ssy.bookstore2.book.application.command.createcategory;

import kr.ssy.bookstore2.book.application.contracts.CommandHandler;
import kr.ssy.bookstore2.book.domain.book.BookRepository;
import kr.ssy.bookstore2.book.domain.book.Category;
import kr.ssy.bookstore2.buildingblocks.domain.BusinessRuleException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import static kr.ssy.bookstore2.book.domain.BookBusinessRuleMessage.BOOK_INVALID_NOT_CATEGROY_INDEX;
import static kr.ssy.bookstore2.book.domain.BookBusinessRuleMessage.BOOK_PARENTID_NOT_EXISTS;

@Slf4j
@Service
@RequiredArgsConstructor
public class CreateCategoryHandler implements CommandHandler<CreateCategory, CreateCategoryResult> {
    private final BookRepository bookRepository;

    @Override
    public CreateCategoryResult handle(CreateCategory command) {
        var category = Category.create(command.parentId(),
                command.name(),
                command.orderIndex()
        );


        // 부모 카테고리 확인
        if (command.parentId() > 0) {
            bookRepository.selectCategoryById(
                    command.parentId()
            ).orElseThrow(
                    () -> new BusinessRuleException(BOOK_PARENTID_NOT_EXISTS)
            );
        }

        // 순서 확인

        bookRepository.selectCategoryListByParentId(command.parentId())
                .ifPresent
                        (

                                categories -> {
                                    categories.forEach(c -> {
                                        if (c.getOrderIndex() == command.orderIndex()) {
                                            throw new BusinessRuleException(BOOK_INVALID_NOT_CATEGROY_INDEX);
                                        }
                                    });
                                }

                        );


        var result = bookRepository.insertCategory(category);

        return new CreateCategoryResult(result);
    }
}
