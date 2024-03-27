package kr.ssy.bookstore2.adminapi.api.category.request;

import io.swagger.v3.oas.annotations.media.Schema;

import static io.swagger.v3.oas.annotations.media.Schema.RequiredMode.REQUIRED;

public record UpdateCategoryRequest(
        @Schema(
                name = "id",
                description = "category id",
                requiredMode = REQUIRED
        )
        long id,
        @Schema(
                name = "parentId",
                description = "변경될 상위 카테고리 id",
                requiredMode = REQUIRED
        )
        long parentId
) {
}
