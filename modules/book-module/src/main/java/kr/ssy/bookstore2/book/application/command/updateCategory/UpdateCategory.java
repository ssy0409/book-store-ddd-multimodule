package kr.ssy.bookstore2.book.application.command.updateCategory;

import kr.ssy.bookstore2.book.application.contracts.Command;

public record UpdateCategory(

        long id,

        long parentId
) implements Command<UpdateCategoryResult> {
}
