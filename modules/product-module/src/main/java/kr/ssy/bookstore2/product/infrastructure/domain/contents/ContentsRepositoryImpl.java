package kr.ssy.bookstore2.product.infrastructure.domain.contents;

import kr.ssy.bookstore2.product.domain.contents.Contents;
import kr.ssy.bookstore2.product.domain.contents.ContentsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
class ContentsRepositoryImpl implements ContentsRepository {

    private final ContentsMyBatisMapper contentsMyBatisMapper;


    @Override
    public Optional<Contents> getById(long id) {
        return contentsMyBatisMapper.selectById(id);
    }

    @Override
    public List<Contents> getByIds(List<Long> ids) {
        return contentsMyBatisMapper.selectListByIds(ids);
    }

    @Override
    public Contents save(Contents contents) {
        contentsMyBatisMapper.insertOrUpdateContents(contents);

        return contents;
    }
}
