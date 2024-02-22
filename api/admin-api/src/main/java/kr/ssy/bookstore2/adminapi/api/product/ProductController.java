package kr.ssy.bookstore2.adminapi.api.product;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Positive;
import kr.ssy.bookstore2.adminapi.api.product.mapstruct.ProductControllerMapper;
import kr.ssy.bookstore2.adminapi.api.product.request.CreateProductRequest;
import kr.ssy.bookstore2.adminapi.api.product.request.UpdateProductRequest;
import kr.ssy.bookstore2.adminapi.api.product.response.CreateProductResponse;
import kr.ssy.bookstore2.adminapi.api.product.response.GetProductByIdResponse;
import kr.ssy.bookstore2.adminapi.api.product.response.UpdateProductResponse;
import kr.ssy.bookstore2.buildingblocks.infrastructure.web.CommonDataResponse;
import kr.ssy.bookstore2.product.application.contracts.ProductModule;
import kr.ssy.bookstore2.product.application.query.getproductbyid.GetProductById;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.annotation.Secured;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Tag(name = "컨텐츠, 상품 등록,수정,조회, 삭제 API", description = "상품 등록,수정,조회, 삭제 API")
@Validated
@RequestMapping("/products")
@RestController
@RequiredArgsConstructor
public class ProductController {

    private final ProductModule productModule;
    

    @Operation(summary = "상품 등록", description = "상품 등록을 등록한다")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "success"),
            @ApiResponse(responseCode = "400", description = "when business validation error")
    })
    @Secured({"PRODUCT_ADMIN"})
    @PostMapping("")
    public CommonDataResponse<CreateProductResponse> createProduct(
            @RequestBody @Valid CreateProductRequest request) {

        var result = productModule.executeCommand(
                ProductControllerMapper.INSTANCE.mappingCreateProduct(request));

        return CommonDataResponse.ok(new CreateProductResponse(result.id()));
    }

    @Secured({"PRODUCT_ADMIN"})
    @PutMapping("/{id}")
    public CommonDataResponse<UpdateProductResponse> updateProduct(
            @PathVariable long id,
            @RequestBody @Valid UpdateProductRequest request) {

        var result = productModule.executeCommand(
                ProductControllerMapper.INSTANCE.mappingUpdateProduct(request, id));

        return CommonDataResponse.ok(new UpdateProductResponse(result.id()));
    }

    @Secured({"PRODUCT_ADMIN"})
    @GetMapping("/{id}")
    public CommonDataResponse<GetProductByIdResponse> getProductById(
            @PathVariable @Positive long id) {

        var result = productModule.executeQuery(new GetProductById(id));

        return CommonDataResponse.ok(
                ProductControllerMapper.INSTANCE.mappingGetProductByIdResponse(result));
    }
}
