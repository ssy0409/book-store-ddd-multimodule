package kr.ssy.bookstore2.admin.infrastructure.query.admin;

import kr.ssy.bookstore2.admin.domain.admin.Admin;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface AdminQueryMyBatisMapper {
    Admin getAdminById(long id);

    Admin getAdminByEmail(String email);
}
