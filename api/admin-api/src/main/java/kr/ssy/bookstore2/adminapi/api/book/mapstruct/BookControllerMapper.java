package kr.ssy.bookstore2.adminapi.api.book.mapstruct;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface BookControllerMapper {
    BookControllerMapper INSTANCE = Mappers.getMapper(BookControllerMapper.class);


}
