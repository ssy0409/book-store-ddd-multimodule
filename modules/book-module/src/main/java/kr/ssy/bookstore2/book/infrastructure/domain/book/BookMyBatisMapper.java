package kr.ssy.bookstore2.book.infrastructure.domain.book;


import kr.ssy.bookstore2.book.domain.book.Book;
import kr.ssy.bookstore2.book.domain.book.Category;
import kr.ssy.bookstore2.book.domain.book.Genre;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface BookMyBatisMapper {

    void insertCategory(Category category);

    void insertBook(Book book);

    void insertGenre(List<Genre> genreList);
}
