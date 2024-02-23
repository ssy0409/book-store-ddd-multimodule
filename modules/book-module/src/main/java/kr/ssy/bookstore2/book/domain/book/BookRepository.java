package kr.ssy.bookstore2.book.domain.book;

public interface BookRepository {

    long insertCategory(Category category);

    long save(Book book);
}
