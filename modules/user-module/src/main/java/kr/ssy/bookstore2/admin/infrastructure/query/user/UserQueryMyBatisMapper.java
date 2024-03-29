package kr.ssy.bookstore2.admin.infrastructure.query.user;

import kr.ssy.bookstore2.admin.domain.user.Admin;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserQueryMyBatisMapper {
    Admin getAdminById(long id);

    Admin getAdminByEmail(String email);
}
