package kr.ssy.bookstore2.adminapi.api.book.mapstruct;

import kr.ssy.bookstore2.adminapi.api.book.request.CreateBookRequest;
import kr.ssy.bookstore2.adminapi.api.book.request.CreateCategoryRequest;
import kr.ssy.bookstore2.book.application.command.createbook.CreateBook;
import kr.ssy.bookstore2.book.application.command.createcategory.CreateCategory;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface BookControllerMapper {
    BookControllerMapper INSTANCE = Mappers.getMapper(BookControllerMapper.class);


    CreateCategory mappingCreateCategory(CreateCategoryRequest request, long adminId);

    CreateBook mappingCreateBook(CreateBookRequest request);
}
