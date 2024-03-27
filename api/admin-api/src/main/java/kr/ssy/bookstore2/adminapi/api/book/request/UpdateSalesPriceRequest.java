package kr.ssy.bookstore2.adminapi.api.book.request;

import io.swagger.v3.oas.annotations.media.Schema;

import java.math.BigDecimal;

import static io.swagger.v3.oas.annotations.media.Schema.RequiredMode.REQUIRED;

public record UpdateSalesPriceRequest(
        @Schema(
                name = "id",
                description = "book id",
                requiredMode = REQUIRED
        )
        long id,
        @Schema(
                name = "salesPrice",
                description = "할인된 판매가",
                requiredMode = REQUIRED
        )
        BigDecimal salesPrice
) {
}
