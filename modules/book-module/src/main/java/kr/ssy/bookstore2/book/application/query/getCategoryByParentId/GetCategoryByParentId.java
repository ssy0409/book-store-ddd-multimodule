package kr.ssy.bookstore2.book.application.query.getCategoryByParentId;

import kr.ssy.bookstore2.book.application.contracts.Query;

public record GetCategoryByParentId(long id) implements Query<GetCategorByParentResult> {
}
