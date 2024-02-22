package kr.ssy.bookstore2.product.domain.product;

import java.util.Optional;

public interface ProductRepository {

    Optional<Product> getById(long id);

    Product save(Product product);
}
