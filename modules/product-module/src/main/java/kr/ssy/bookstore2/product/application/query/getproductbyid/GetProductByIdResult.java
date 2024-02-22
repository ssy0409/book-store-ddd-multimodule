package kr.ssy.bookstore2.product.application.query.getproductbyid;

import kr.ssy.bookstore2.product.application.contracts.Result;
import kr.ssy.bookstore2.product.domain.product.enumtype.ContentsPositionType;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

public record GetProductByIdResult(
        long id,
        String name,
        String description,
        BigDecimal msrPrice,
        BigDecimal salesPrice,
        LocalDateTime salesOpenedAt,
        LocalDateTime salesClosedAt,
        List<ProductContents> productContentsList
) implements Result {

    public record ProductContents(
            long id,
            long contentsId,
            ContentsPositionType contentsPositionType,
            int orderIndex,
            boolean deleted
    ) {

    }
}
