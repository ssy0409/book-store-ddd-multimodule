package kr.ssy.bookstore2.adminapi.api.book.request;

import io.swagger.v3.oas.annotations.media.Schema;
import kr.ssy.bookstore2.book.domain.book.enumtype.BookStatus;

import static io.swagger.v3.oas.annotations.media.Schema.RequiredMode.REQUIRED;

public record UpdateBookStatusRequest(
        @Schema(
                name = "id",
                description = "book id",
                requiredMode = REQUIRED
        )
        long id,

        @Schema(
                name = "status",
                description = "status",
                requiredMode = REQUIRED
        )
        BookStatus status
) {
}
