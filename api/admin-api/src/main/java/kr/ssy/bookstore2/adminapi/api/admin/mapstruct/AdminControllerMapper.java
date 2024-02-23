package kr.ssy.bookstore2.adminapi.api.admin.mapstruct;


import kr.ssy.bookstore2.admin.application.command.deleteadmin.DeleteAdminResult;
import kr.ssy.bookstore2.admin.application.command.loginadmin.LoginAdmin;
import kr.ssy.bookstore2.admin.application.command.registeradmin.RegisterAdmin;
import kr.ssy.bookstore2.admin.application.query.getadminbyid.GetAdminByIdResult;
import kr.ssy.bookstore2.adminapi.api.admin.request.LoginAdminRequest;
import kr.ssy.bookstore2.adminapi.api.admin.request.RegisterAdminRequest;
import kr.ssy.bookstore2.adminapi.api.admin.response.DeleteAdminResponse;
import kr.ssy.bookstore2.adminapi.api.admin.response.GetAdminByIdResponse;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface AdminControllerMapper {
    AdminControllerMapper INSTANCE = Mappers.getMapper(AdminControllerMapper.class);

    RegisterAdmin mappingRegisterAdmin(RegisterAdminRequest request);

    GetAdminByIdResponse mappingGetAdminByIdResponse(GetAdminByIdResult result);

    LoginAdmin mappingLoginAdmin(LoginAdminRequest request);


    DeleteAdminResponse mappingdeleteAdminResponse(DeleteAdminResult result);
}
