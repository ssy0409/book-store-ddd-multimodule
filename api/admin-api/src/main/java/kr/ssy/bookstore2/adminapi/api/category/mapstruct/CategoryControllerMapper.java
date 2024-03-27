package kr.ssy.bookstore2.adminapi.api.category.mapstruct;

import kr.ssy.bookstore2.adminapi.api.category.request.CreateCategoryRequest;
import kr.ssy.bookstore2.adminapi.api.category.request.UpdateCategoryRequest;
import kr.ssy.bookstore2.adminapi.api.category.response.GetCategoryAllResponse;
import kr.ssy.bookstore2.adminapi.api.category.response.UpdateCategoryResponse;
import kr.ssy.bookstore2.book.application.command.createcategory.CreateCategory;
import kr.ssy.bookstore2.book.application.command.updateCategory.UpdateCategory;
import kr.ssy.bookstore2.book.application.query.getCategoryByParentId.GetCategorByParentResult;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface CategoryControllerMapper {
    CategoryControllerMapper INSTANCE = Mappers.getMapper(CategoryControllerMapper.class);


    CreateCategory mappingCreateCategory(CreateCategoryRequest request, long adminId);


    GetCategoryAllResponse mappingGetCategoryByParentIdResponse(GetCategorByParentResult result);

    UpdateCategory mappingUpdateCategory(UpdateCategoryRequest request);

    UpdateCategoryResponse mappingUpdateCategoryResponse(UpdateCategoryRequest request);
}
