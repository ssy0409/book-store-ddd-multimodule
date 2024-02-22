package kr.ssy.bookstore2.product.infrastructure.domain.contents;

import kr.ssy.bookstore2.product.domain.contents.Contents;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Optional;

@Mapper
public interface ContentsMyBatisMapper {

    Optional<Contents> selectById(long id);

    List<Contents> selectListByIds(List<Long> ids);

    void insertOrUpdateContents(Contents contents);
}
