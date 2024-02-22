package kr.ssy.bookstore2.product.infrastructure.domain.product;

import kr.ssy.bookstore2.product.domain.product.Product;
import kr.ssy.bookstore2.product.domain.product.ProductContents;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Optional;

@Mapper
public interface ProductMyBatisMapper {

    Optional<Product> selectById(long id);

    void insertOrUpdateProduct(Product product);

    void insertOrUpdateProductContentsList(
            @Param("list") List<ProductContents> productContentsList);

}
