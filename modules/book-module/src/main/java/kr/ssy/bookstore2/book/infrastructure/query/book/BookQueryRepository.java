package kr.ssy.bookstore2.book.infrastructure.query.book;


import kr.ssy.bookstore2.book.domain.book.Category;

import java.util.List;
import java.util.Optional;

public interface BookQueryRepository {


    Optional<List<Category>> selectCategoryByParentId(long id);
}
