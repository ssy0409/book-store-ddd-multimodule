package kr.ssy.bookstore2.book.infrastructure.domain.book;

import kr.ssy.bookstore2.book.infrastructure.query.book.BookQueryRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

@Slf4j
@Repository
@RequiredArgsConstructor
public class BookRepositoryImpl implements BookQueryRepository {
    private final BookMyBatisMapper bookMyBatisMapper;


}
