package kr.ssy.bookstore2.admin.infrastructure.domain.user;

import kr.ssy.bookstore2.admin.domain.user.Admin;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMyBatisMapper {
    long insertAdmin(Admin newAdmin);

    void insertAdminAuthorityList(Admin newAdmin);

    int deleteAdmin(long id);
}
