package kr.ssy.bookstore2.product.infrastructure.domain.product;

import kr.ssy.bookstore2.product.domain.product.Product;
import kr.ssy.bookstore2.product.domain.product.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@RequiredArgsConstructor
class ProductRepositoryImpl implements ProductRepository {

    private final ProductMyBatisMapper productMyBatisMapper;

    @Override
    public Optional<Product> getById(long id) {
        return productMyBatisMapper.selectById(id);
    }

    @Override
    public Product save(Product product) {
        productMyBatisMapper.insertOrUpdateProduct(product);
        productMyBatisMapper.insertOrUpdateProductContentsList(
                product.getProductContentsList());
        return product;
    }
}
