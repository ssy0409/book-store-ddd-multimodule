package kr.ssy.bookstore2.product.infrastructure.query.product;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class ProductQueryRepository {

    private final ProductQueryMyBatisMapper productQueryMyBatisMapper;

}
