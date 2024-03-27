package kr.ssy.bookstore2.book.infrastructure.domain.book;


import kr.ssy.bookstore2.book.domain.book.Book;
import kr.ssy.bookstore2.book.domain.book.Category;
import kr.ssy.bookstore2.book.domain.book.Genre;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Optional;

@Mapper
public interface BookMyBatisMapper {

    long insertCategory(Category category);

    long insertBook(Book book);

    long insertGenre(List<Genre> genreList);

    Optional<Category> selectCategoryById(long id);

    List<Category> selectCategoryListByParentId(long parentId);

    Optional<Long> updateBookStatus(Book updateBook);

    Optional<Long> updateSalesPrice(Book book);

    Optional<Integer> selectCategoryLastOrderByParentId(long parentId);

    void updateCategory(Category newCategory);


}
