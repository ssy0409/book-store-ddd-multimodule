package kr.ssy.bookstore2.book.infrastructure.domain.book;

import kr.ssy.bookstore2.book.domain.book.Book;
import kr.ssy.bookstore2.book.domain.book.BookRepository;
import kr.ssy.bookstore2.book.domain.book.Category;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

@Slf4j
@Repository
@RequiredArgsConstructor
public class BookRepositoryImpl implements BookRepository {
    private final BookMyBatisMapper bookMyBatisMapper;

    @Override
    public long insertCategory(Category category) {
        bookMyBatisMapper.insertCategory(category);
        return category.getId();
    }

    @Override
    public long save(Book book) {
        bookMyBatisMapper.insertBook(book);
        bookMyBatisMapper.insertGenre(book.getGenreList());
        return book.getId();
    }
}
