package kr.ssy.bookstore2.book.application.command.createcategory;

import kr.ssy.bookstore2.book.application.contracts.Command;
import kr.ssy.bookstore2.book.domain.book.enumtype.BooKCategory;

public record CreateCategory(

        long parent_id,
        BooKCategory name,
        int orderIndex
) implements Command<CreateCategoryResult> {
}
