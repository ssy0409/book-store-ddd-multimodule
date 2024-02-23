package kr.ssy.bookstore2.adminapi.api.book.request;

import io.swagger.v3.oas.annotations.media.Schema;
import kr.ssy.bookstore2.book.domain.book.enumtype.BooKCategory;

import static io.swagger.v3.oas.annotations.media.Schema.RequiredMode.NOT_REQUIRED;
import static io.swagger.v3.oas.annotations.media.Schema.RequiredMode.REQUIRED;

public record CreateCategoryRequest(
        @Schema
                (
                        name = "parentId",
                        description = "상위 카테고리 ID, 최상위 카테고리면 NULL",
                        requiredMode = NOT_REQUIRED
                )
        long parent_id,
        @Schema
                (
                        name = "name",
                        description = "카테고리이름",
                        requiredMode = REQUIRED
                )
        BooKCategory name,
        @Schema
                (
                        name = "orderIndex",
                        description = "카테고리 순서",
                        requiredMode = REQUIRED

                )
        int orderIndex,
        long adminId
) {
}
