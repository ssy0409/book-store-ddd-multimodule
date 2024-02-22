package kr.ssy.bookstore2.product.infrastructure.query.contents;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class ContentsQueryRepository {
    private final ContentsQueryMyBatisMapper contentsQueryMyBatisMapper;
}
