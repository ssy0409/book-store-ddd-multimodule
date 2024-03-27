package kr.ssy.bookstore2.book.application.command.updateCategory;

import kr.ssy.bookstore2.book.application.contracts.CommandHandler;
import kr.ssy.bookstore2.book.domain.book.BookRepository;
import kr.ssy.bookstore2.book.domain.book.Category;
import kr.ssy.bookstore2.buildingblocks.domain.BusinessRuleException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import static kr.ssy.bookstore2.book.domain.BookBusinessRuleMessage.CATEGORY_ID_NOT_EXISTS;

@Slf4j
@Service
@RequiredArgsConstructor
public class UpdateCategoryHandler implements CommandHandler<UpdateCategory, UpdateCategoryResult> {
    private final BookRepository bookRepository;

    @Override
    public UpdateCategoryResult handle(UpdateCategory command) {
        var order = bookRepository.selectCategorylastOrderByParentId(command.parentId())
                .orElseThrow(
                        () -> new BusinessRuleException(CATEGORY_ID_NOT_EXISTS)
                );


        var newCategory = Category.update(command.id(),
                command.parentId(),
                order + 1);

        bookRepository.updateCategroy(newCategory);

        return new UpdateCategoryResult(newCategory.getOrderIndex());
    }
}
