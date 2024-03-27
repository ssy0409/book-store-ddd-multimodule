package kr.ssy.bookstore2.book.infrastructure.query.book;

import kr.ssy.bookstore2.book.domain.book.Category;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Slf4j
@Repository
@RequiredArgsConstructor
public class BookQueryRepositoryImpl implements BookQueryRepository {
    private final BookQueryMyBatisMapper bookQueryMyBatisMapper;


    @Override
    public Optional<List<Category>> selectCategoryByParentId(long parentId) {
        return Optional.ofNullable(bookQueryMyBatisMapper.selectCategoryByParentId(parentId));

    }
}
