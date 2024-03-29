package kr.ssy.bookstore2.admin.application.query.mapstruct;

import kr.ssy.bookstore2.admin.application.query.getadminbyid.GetAdminByIdResult;
import kr.ssy.bookstore2.admin.domain.user.Admin;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface GetAdminByIdMapper {
    GetAdminByIdMapper INSTANCE = Mappers.getMapper(GetAdminByIdMapper.class);

    GetAdminByIdResult mappingAdminById(Admin admin);
}
