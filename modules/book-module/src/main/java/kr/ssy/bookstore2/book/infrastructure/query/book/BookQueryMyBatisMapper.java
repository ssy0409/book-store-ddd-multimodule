package kr.ssy.bookstore2.book.infrastructure.query.book;

import kr.ssy.bookstore2.book.domain.book.Category;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface BookQueryMyBatisMapper {


    List<Category> selectCategoryByParentId(long parentId);
}
