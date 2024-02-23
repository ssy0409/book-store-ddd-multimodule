package kr.ssy.bookstore2.adminapi.api.admin;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import kr.ssy.bookstore2.admin.application.command.deleteadmin.DeleteAdmin;
import kr.ssy.bookstore2.admin.application.contracts.AdminModule;
import kr.ssy.bookstore2.admin.application.query.getadminbyid.GetAdminById;
import kr.ssy.bookstore2.adminapi.api.admin.mapstruct.AdminControllerMapper;
import kr.ssy.bookstore2.adminapi.api.admin.request.LoginAdminRequest;
import kr.ssy.bookstore2.adminapi.api.admin.request.RegisterAdminRequest;
import kr.ssy.bookstore2.adminapi.api.admin.response.GetAdminByIdResponse;
import kr.ssy.bookstore2.adminapi.api.admin.response.LoginAdminResponse;
import kr.ssy.bookstore2.adminapi.api.admin.response.RegisterAdminResponse;
import kr.ssy.bookstore2.adminapi.config.security.TokenProvider;
import kr.ssy.bookstore2.buildingblocks.infrastructure.web.CommonDataResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.annotation.Secured;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Tag(name = "관리자 등록 등록, 조회, 삭제 API", description = "")
@Validated
@RequestMapping("/admins")
@RestController
@RequiredArgsConstructor
public class AdminController {
    private final AdminModule adminModule;
    private final TokenProvider tokenProvider;

    @Operation(summary = "관리자 등록", description = "관리자를 등록한다")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "success"),
            @ApiResponse(responseCode = "400", description = "when business validation error")
    })
    @PostMapping("")
    public CommonDataResponse<RegisterAdminResponse> createProduct(
            @RequestBody @Valid RegisterAdminRequest request) {

        var result = adminModule.executeCommand(
                AdminControllerMapper.INSTANCE.mappingRegisterAdmin(request));

        return CommonDataResponse.ok(new RegisterAdminResponse(result.id()));
    }

    @Operation(summary = "관리자 로그인", description = "관리자 email, password를 API로 보내 로그인 한다")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "success"),
            @ApiResponse(responseCode = "400", description = "when business validation error")
    })
    @PostMapping("/login")
    public CommonDataResponse<LoginAdminResponse> loginManager(
            @RequestBody LoginAdminRequest request) {

        var result = adminModule.executeCommand(
                AdminControllerMapper.INSTANCE.mappingLoginAdmin(request)
        );

        return CommonDataResponse.ok(
                new LoginAdminResponse(
                        tokenProvider.generateToken(result.admin()),
                        result.admin().getId()
                )

        );
    }

    @Operation(summary = "관리자 아이디로 관리자 조회", description = "관리자 아이디로 관리자 조회")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "success"),
            @ApiResponse(responseCode = "400", description = "when business validation error")
    })
    @Secured({"AUTHORITY_ADMIN"})
    @GetMapping("")
    public CommonDataResponse<GetAdminByIdResponse> getAdminById(
            @RequestParam long id

    ) {
        var result = adminModule.executeQuery(new GetAdminById(id));

        return CommonDataResponse.ok(
                AdminControllerMapper.INSTANCE.mappingGetAdminByIdResponse(result)
        );

    }

    @Operation(summary = "관리자 삭제 ", description = "관리자 아이디로 관리자 조회")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "success"),
            @ApiResponse(responseCode = "400", description = "when business validation error")
    })
    @Secured({"AUTHORITY_ADMIN"})
    @DeleteMapping("")
    public CommonDataResponse deleteAdmin(
            @RequestParam long id
    ) {
        var result = adminModule.executeCommand(new DeleteAdmin(id));
        return CommonDataResponse.ok(
                AdminControllerMapper.INSTANCE.mappingdeleteAdminResponse(result)
        );
    }

}
