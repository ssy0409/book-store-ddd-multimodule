package kr.ssy.bookstore2.adminapi.api.category;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import kr.ssy.bookstore2.adminapi.annotation.AdminDetailsResolver;
import kr.ssy.bookstore2.adminapi.api.category.mapstruct.CategoryControllerMapper;
import kr.ssy.bookstore2.adminapi.api.category.request.CreateCategoryRequest;
import kr.ssy.bookstore2.adminapi.api.category.request.UpdateCategoryRequest;
import kr.ssy.bookstore2.adminapi.api.category.response.CreateCategoryResponse;
import kr.ssy.bookstore2.adminapi.api.category.response.GetCategoryAllResponse;
import kr.ssy.bookstore2.adminapi.api.category.response.UpdateCategoryResponse;
import kr.ssy.bookstore2.adminapi.config.security.AdminDetails;
import kr.ssy.bookstore2.book.application.contracts.BookModule;
import kr.ssy.bookstore2.book.application.query.getCategoryByParentId.GetCategoryByParentId;
import kr.ssy.bookstore2.buildingblocks.infrastructure.web.CommonDataResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Tag(name = "카테고리  등록 , 조회, 삭제 API", description = "")
@Validated
@RequestMapping("/categories")
@RestController
@RequiredArgsConstructor
public class CategoryController {
    private final BookModule bookModule;

    @Operation(summary = "카테고리 등록", description = "카테고리를 등록한다")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "success"),
            @ApiResponse(responseCode = "400", description = "when business validation error")
    })
    //  @Secured({"PRODUCT_ADMIN"})
    @PostMapping("/category")
    public CommonDataResponse<CreateCategoryResponse> createCategory(
            @RequestBody @Valid CreateCategoryRequest request,
            @Schema(hidden = true)
            @AdminDetailsResolver AdminDetails adminDetails) {

        var adminId = adminDetails.getId();
        var result = bookModule.executeCommand(
                CategoryControllerMapper.INSTANCE.mappingCreateCategory(request, adminId)
        );

        return CommonDataResponse.ok(new CreateCategoryResponse(result.id()));
    }

    @Operation(summary = "카테고리 parentId 로 조회 ", description = "카테고리 조회")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "success"),
            @ApiResponse(responseCode = "400", description = "when business validation error")
    })
//    @Secured({"PRODUCT_ADMIN"})
    @GetMapping("/category")
    public CommonDataResponse<GetCategoryAllResponse> getCategoryByParentId(
            @RequestParam(required = false, defaultValue = "0") long parentId
    ) {

        var result = bookModule.executeQuery(new GetCategoryByParentId(parentId));

        return CommonDataResponse.ok(
                CategoryControllerMapper.INSTANCE.mappingGetCategoryByParentIdResponse(result)
        );

    }

    //카테고리 변경 상위 카테고리가 바뀌면 해당 카테고리의 가장 마지막 정렬순서에 위치하게된다
    // depth간 이동은 불가능하다.(2depth를 1depth로 이동)
    @Operation(summary = "상위 카테고리 변경 ", description = "")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "success"),
            @ApiResponse(responseCode = "400", description = "when business validation error")
    })
    //    @Secured({"PRODUCT_ADMIN"})
    @PutMapping("/category")
    public CommonDataResponse<UpdateCategoryResponse> UpdateCategory(
            @RequestBody UpdateCategoryRequest request
    ) {
        var result = bookModule.executeCommand(
                CategoryControllerMapper.INSTANCE.mappingUpdateCategory(request)
        );
        return CommonDataResponse.ok(
                CategoryControllerMapper.INSTANCE.mappingUpdateCategoryResponse(request)
        );
    }
}
