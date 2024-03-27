package kr.ssy.bookstore2.adminapi.api.category.response;

import kr.ssy.bookstore2.book.application.query.getCategoryByParentId.GetCategorByParentResult;

import java.time.LocalDateTime;
import java.util.List;

public record GetCategoryAllResponse(
        List<GetCategorByParentResult.GetCategory> getCategoryList
) {
    public record GetCategory(
            long id,
            long parentId,
            String name,
            int orderIndex,
            LocalDateTime insertDate,
            LocalDateTime updateDate
    ) {

    }
}
