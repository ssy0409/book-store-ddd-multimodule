package kr.ssy.bookstore2.product.application.query.mapstruct;

import kr.ssy.bookstore2.product.application.query.getproductbyid.GetProductByIdResult;
import kr.ssy.bookstore2.product.domain.product.Product;
import kr.ssy.bookstore2.product.domain.product.ProductContents;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface GetProductByIdMapper {
    GetProductByIdMapper INSTANCE = Mappers.getMapper(GetProductByIdMapper.class);

    GetProductByIdResult mappingProduct(Product product);

    GetProductByIdResult.ProductContents mappingProductContents(ProductContents productContents);
}
