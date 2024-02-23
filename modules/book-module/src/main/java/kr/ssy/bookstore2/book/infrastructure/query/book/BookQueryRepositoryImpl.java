package kr.ssy.bookstore2.book.infrastructure.query.book;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Slf4j
@Repository
@RequiredArgsConstructor
public class BookQueryRepositoryImpl implements BookQueryRepository {
    private final BookQueryMyBatisMapper bookQueryMyBatisMapper;


}
