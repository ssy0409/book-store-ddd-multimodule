package kr.ssy.bookstore2.admin.application.query.mapstruct;

import kr.ssy.bookstore2.admin.application.query.getclientbyemail.GetClientByEmailResult;
import kr.ssy.bookstore2.admin.domain.user.Client;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface GetClientByEmailMapper {

    GetClientByEmailMapper INSTANCE = Mappers.getMapper(GetClientByEmailMapper.class);

    GetClientByEmailResult mappingAdminById(Client client);
}
