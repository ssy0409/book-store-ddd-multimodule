package kr.ssy.bookstore2.product.domain.contents;

import java.util.List;
import java.util.Optional;

public interface ContentsRepository {

    Optional<Contents> getById(long id);

    List<Contents> getByIds(List<Long> ids);

    Contents save(Contents contents);
}
