package kr.ssy.bookstore2.admin.application.query.mapstruct;

import kr.ssy.bookstore2.admin.application.query.getadminbyemail.GetAdminByEmailResult;
import kr.ssy.bookstore2.admin.domain.user.Admin;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface GetAdminByEmailMapper {

    GetAdminByEmailMapper INSTANCE = Mappers.getMapper(GetAdminByEmailMapper.class);

    GetAdminByEmailResult mappingAdminById(Admin admin);
}
