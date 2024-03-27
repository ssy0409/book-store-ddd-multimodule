package kr.ssy.bookstore2.book.application.query.getCategoryByParentId;

import kr.ssy.bookstore2.book.application.contracts.QueryHandler;
import kr.ssy.bookstore2.book.application.query.mapstruct.GetCategoryByParentIdMapper;
import kr.ssy.bookstore2.book.infrastructure.query.book.BookQueryRepository;
import kr.ssy.bookstore2.buildingblocks.domain.BusinessRuleException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

import static kr.ssy.bookstore2.book.domain.BookBusinessRuleMessage.BOOK_CATEGORY_NOT_EXISTS;

@Service
@RequiredArgsConstructor
public class GetCategoryByParentIdHandler implements QueryHandler<GetCategoryByParentId, GetCategorByParentResult> {
    private final BookQueryRepository bookQueryRepository;

    @Override
    public GetCategorByParentResult handle(GetCategoryByParentId query) {
        var catetoryList = bookQueryRepository.selectCategoryByParentId(query.id())
                .orElseThrow(
                        () -> new BusinessRuleException(BOOK_CATEGORY_NOT_EXISTS)
                );

        GetCategorByParentResult result = new GetCategorByParentResult(new ArrayList<>());
        catetoryList.forEach(
                c -> {
                    result.getCategoryList().add(
                            GetCategoryByParentIdMapper.INSTANCE.mappingCategory(c)
                    );
                }
        );

        return result;
    }


}
