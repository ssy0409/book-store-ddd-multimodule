package kr.ssy.bookstore2.adminapi.api.book;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import kr.ssy.bookstore2.adminapi.annotation.AdminDetailsResolver;
import kr.ssy.bookstore2.adminapi.api.book.mapstruct.BookControllerMapper;
import kr.ssy.bookstore2.adminapi.api.book.request.CreateBookRequest;
import kr.ssy.bookstore2.adminapi.api.book.request.CreateCategoryRequest;
import kr.ssy.bookstore2.adminapi.api.book.response.CreateBookResponse;
import kr.ssy.bookstore2.adminapi.api.book.response.CreateCatergoryResponse;
import kr.ssy.bookstore2.adminapi.config.security.AdminDetails;
import kr.ssy.bookstore2.book.application.contracts.BookModule;
import kr.ssy.bookstore2.buildingblocks.infrastructure.web.CommonDataResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "책 카테고리 등록,책 새상품 추가, 상품(책)정보 수정, 상품(책) 조회, 삭제  API", description = "")
@Validated
@RequestMapping("/books")
@RestController
@RequiredArgsConstructor
public class BookController {
    private final BookModule bookModule;

    @Operation(summary = "상품(책) 카테고리 등록", description = "책 카테고리를 등록한다")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "success"),
            @ApiResponse(responseCode = "400", description = "when business validation error")
    })
    @PostMapping("/category")
    public CommonDataResponse<CreateCatergoryResponse> createCategory(
            @RequestBody @Valid CreateCategoryRequest request,
            @AdminDetailsResolver AdminDetails adminDetails) {

        var adminId = adminDetails.getId();
        var result = bookModule.executeCommand(
                BookControllerMapper.INSTANCE.mappingCreateCategory(request, adminId)
        );

        return CommonDataResponse.ok(new CreateCatergoryResponse(result.id()));
    }

    @Operation(summary = "상품(책) 카테고리 등록", description = "책 카테고리를 등록한다")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "success"),
            @ApiResponse(responseCode = "400", description = "when business validation error")
    })
    @PostMapping("")
    public CommonDataResponse<CreateBookResponse> createBook(
            @RequestBody @Valid CreateBookRequest request,
            @AdminDetailsResolver AdminDetails adminDetails) {

        var adminId = adminDetails.getId();

        var result = bookModule.executeCommand(
                BookControllerMapper.INSTANCE.mappingCreateBook(request)
        );

        return CommonDataResponse.ok(new CreateBookResponse(result.id()));
    }


}
