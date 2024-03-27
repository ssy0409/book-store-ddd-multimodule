package kr.ssy.bookstore2.book.infrastructure.domain.book;

import kr.ssy.bookstore2.book.domain.book.Book;
import kr.ssy.bookstore2.book.domain.book.BookRepository;
import kr.ssy.bookstore2.book.domain.book.Category;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

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

    @Override
    public Optional<Category> selectCategoryById(long id) {
        return bookMyBatisMapper.selectCategoryById(id);
    }

    @Override
    public Optional<List<Category>> selectCategoryListByParentId(long parentId) {
        return Optional.ofNullable(bookMyBatisMapper.selectCategoryListByParentId(parentId));
    }

    @Override
    public Optional<Long> updateBookStatus(Book updateBook) {

        return bookMyBatisMapper.updateBookStatus(updateBook);
    }

    @Override
    public Optional<Long> updateSalesPrice(Book book) {
        return bookMyBatisMapper.updateSalesPrice(book);
    }

    @Override
    public Optional<Integer> selectCategorylastOrderByParentId(long parentId) {
        return bookMyBatisMapper.selectCategoryLastOrderByParentId(parentId);
    }

    @Override
    public void updateCategroy(Category newCategory) {
        bookMyBatisMapper.updateCategory(newCategory);
    }


}
