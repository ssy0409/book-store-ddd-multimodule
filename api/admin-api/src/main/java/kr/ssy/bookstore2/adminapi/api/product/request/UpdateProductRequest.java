package kr.ssy.bookstore2.adminapi.api.product.request;

import kr.ssy.bookstore2.product.domain.product.enumtype.ContentsPositionType;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

public record UpdateProductRequest(
        long productId,
        String name,
        String description,
        BigDecimal msrPrice,
        BigDecimal salesPrice,
        LocalDateTime salesOpenedAt,
        LocalDateTime salesClosedAt,
        List<ProductContents> productContentsList
) {

    public record ProductContents(
            long contentsId,
            ContentsPositionType contentsPositionType,
            int orderIndex
    ) {

    }

}
