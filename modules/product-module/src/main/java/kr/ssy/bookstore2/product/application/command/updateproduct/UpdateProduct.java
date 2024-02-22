package kr.ssy.bookstore2.product.application.command.updateproduct;

import kr.ssy.bookstore2.product.application.contracts.Command;
import kr.ssy.bookstore2.product.domain.product.enumtype.ContentsPositionType;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

public record UpdateProduct(
        long id,
        String name,
        String description,
        BigDecimal msrPrice,
        BigDecimal salesPrice,
        LocalDateTime salesOpenedAt,
        LocalDateTime salesClosedAt,
        List<ProductContents> productContentsList
) implements Command<UpdateProductResult> {

    public record ProductContents(
            long contentsId,
            ContentsPositionType contentsPositionType,
            int orderIndex,
            boolean deleted
    ) {

    }
}

