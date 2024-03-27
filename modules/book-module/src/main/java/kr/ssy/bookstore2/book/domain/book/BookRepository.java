package kr.ssy.bookstore2.book.domain.book;

import java.util.List;
import java.util.Optional;

public interface BookRepository {

    long insertCategory(Category category);

    long save(Book book);

    Optional<Category> selectCategoryById(long id);

    Optional<List<Category>> selectCategoryListByParentId(long parentId);

    Optional<Long> updateBookStatus(Book updateBook);

    Optional<Long> updateSalesPrice(Book book);

    Optional<Integer> selectCategorylastOrderByParentId(long parentId);

    void updateCategroy(Category newCategory);

}
