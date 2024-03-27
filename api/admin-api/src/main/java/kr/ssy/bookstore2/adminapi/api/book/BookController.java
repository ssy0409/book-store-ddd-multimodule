package kr.ssy.bookstore2.adminapi.api.book;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import kr.ssy.bookstore2.adminapi.annotation.AdminDetailsResolver;
import kr.ssy.bookstore2.adminapi.api.book.mapstruct.BookControllerMapper;
import kr.ssy.bookstore2.adminapi.api.book.request.CreateBookRequest;
import kr.ssy.bookstore2.adminapi.api.book.request.UpdateBookStatusRequest;
import kr.ssy.bookstore2.adminapi.api.book.request.UpdateSalesPriceRequest;
import kr.ssy.bookstore2.adminapi.api.book.response.CreateBookResponse;
import kr.ssy.bookstore2.adminapi.config.security.AdminDetails;
import kr.ssy.bookstore2.book.application.contracts.BookModule;
import kr.ssy.bookstore2.buildingblocks.infrastructure.web.CommonDataResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Tag(name = "책 카테고리 등록,책 새상품 추가, 상품(책)정보 수정, 상품(책) 조회, 삭제  API", description = "")
@Validated
@RequestMapping("/books")
@RestController
@RequiredArgsConstructor
public class BookController {
    private final BookModule bookModule;

    @Operation(summary = "상품(책) 등록", description = "상품(책)을 등록한다")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "success"),
            @ApiResponse(responseCode = "400", description = "when business validation error")
    })
    //    @Secured({"PRODUCT_ADMIN"})
    @PostMapping("")
    public CommonDataResponse<CreateBookResponse> createBook(
            @RequestBody @Valid CreateBookRequest request,
            @Schema(hidden = true)
            @AdminDetailsResolver AdminDetails adminDetails) {

        var adminId = adminDetails.getId();

        var result = bookModule.executeCommand(
                BookControllerMapper.INSTANCE.mappingCreateBook(request, adminId)
        );

        return CommonDataResponse.ok(new CreateBookResponse(result.id()));
    }

    @Operation(summary = "상품(책) 상태변경", description = "상품(책)을 수정한다")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "success"),
            @ApiResponse(responseCode = "400", description = "when business validation error")
    })
    //    @Secured({"PRODUCT_ADMIN"})
    @PutMapping("/status")
    public CommonDataResponse updateBookStatus(
            @RequestBody @Valid UpdateBookStatusRequest request,
            @Schema(hidden = true)
            @AdminDetailsResolver AdminDetails adminDetails) {

        var adminId = adminDetails.getId();
        bookModule.executeCommand(
                BookControllerMapper.INSTANCE.mappingUpdatBookStatus(request, adminId)
        );

        return CommonDataResponse.emptyOk();
    }

    @Operation(summary = "상품(책)_판매가변경", description = "상품(책) 판매가를 수정한다")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "success"),
            @ApiResponse(responseCode = "400", description = "when business validation error")
    })
    //    @Secured({"PRODUCT_ADMIN"})
    @PutMapping("/sales-price")
    public CommonDataResponse updateSalesPrice(
            @RequestBody @Valid UpdateSalesPriceRequest request,
            @Schema(hidden = true)
            @AdminDetailsResolver AdminDetails adminDetails
    ) {
        var adminId = adminDetails.getId();
        bookModule.executeCommand(
                BookControllerMapper.INSTANCE.mappingUpdateSalesPrice(request, adminId)
        );

        return CommonDataResponse.emptyOk();
    }


}
