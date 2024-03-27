package kr.ssy.bookstore2.book.application.command.createcategory;

import kr.ssy.bookstore2.book.application.contracts.Command;
import kr.ssy.bookstore2.book.domain.book.enumtype.BooKCategory;

public record CreateCategory(

        long parentId,
        BooKCategory name,
        int orderIndex,
        long adminId
) implements Command<CreateCategoryResult> {
}
