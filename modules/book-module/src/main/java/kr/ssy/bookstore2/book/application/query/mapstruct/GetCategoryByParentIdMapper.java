package kr.ssy.bookstore2.book.application.query.mapstruct;

import kr.ssy.bookstore2.book.application.query.getCategoryByParentId.GetCategorByParentResult;
import kr.ssy.bookstore2.book.domain.book.Category;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface GetCategoryByParentIdMapper {
    GetCategoryByParentIdMapper INSTANCE = Mappers.getMapper(GetCategoryByParentIdMapper.class);


    GetCategorByParentResult.GetCategory mappingCategory(Category c);
}
