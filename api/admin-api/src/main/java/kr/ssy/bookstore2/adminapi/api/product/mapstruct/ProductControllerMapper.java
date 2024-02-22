package kr.ssy.bookstore2.adminapi.api.product.mapstruct;

import kr.ssy.bookstore2.adminapi.api.product.request.CreateProductRequest;
import kr.ssy.bookstore2.adminapi.api.product.request.UpdateProductRequest;
import kr.ssy.bookstore2.adminapi.api.product.response.GetProductByIdResponse;
import kr.ssy.bookstore2.product.application.command.createproduct.CreateProduct;
import kr.ssy.bookstore2.product.application.command.updateproduct.UpdateProduct;
import kr.ssy.bookstore2.product.application.query.getproductbyid.GetProductByIdResult;
import org.mapstruct.Context;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface ProductControllerMapper {
    ProductControllerMapper INSTANCE = Mappers.getMapper(ProductControllerMapper.class);

    CreateProduct mappingCreateProduct(CreateProductRequest request);

    CreateProduct.ProductContents mappingCreateProductContents(CreateProductRequest.ProductContents request);

    @Mapping(target = "id", source = "productId")
    UpdateProduct mappingUpdateProduct(UpdateProductRequest request, @Context long productId);

    UpdateProduct.ProductContents mappingCreateProductContents(UpdateProductRequest.ProductContents request);

    GetProductByIdResponse mappingGetProductByIdResponse(GetProductByIdResult result);

    GetProductByIdResponse mappingGetProductByIdContentsResponse(GetProductByIdResult.ProductContents result);

}
