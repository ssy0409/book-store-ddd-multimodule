package kr.ssy.bookstore2.book.application.query.getCategoryByParentId;

import kr.ssy.bookstore2.book.application.contracts.Result;

import java.time.LocalDateTime;
import java.util.List;

public record GetCategorByParentResult(

        List<GetCategory> getCategoryList

) implements Result {
    // 생성자 추가
    public GetCategorByParentResult(List<GetCategory> getCategoryList) {
        this.getCategoryList = getCategoryList;
    }

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
