package kr.ssy.bookstore2.admin.infrastructure.domain.admin;

import kr.ssy.bookstore2.admin.domain.admin.Admin;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface AdminMyBatisMapper {
    long insertAdmin(Admin newAdmin);

    void insertAdminAuthorityList(Admin newAdmin);

}
