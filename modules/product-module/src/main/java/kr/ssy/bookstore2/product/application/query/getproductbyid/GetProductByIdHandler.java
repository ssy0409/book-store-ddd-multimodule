package kr.ssy.bookstore2.product.application.query.getproductbyid;

import kr.ssy.bookstore2.buildingblocks.domain.BusinessRuleException;
import kr.ssy.bookstore2.product.application.contracts.QueryHandler;
import kr.ssy.bookstore2.product.application.query.mapstruct.GetProductByIdMapper;
import kr.ssy.bookstore2.product.domain.product.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import static kr.ssy.bookstore2.product.domain.ProductBusinessRuleMessage.PRODUCT_NOT_EXISTS;

@Service
@RequiredArgsConstructor
class GetProductByIdHandler implements QueryHandler<GetProductById, GetProductByIdResult> {

    //원래는 아래의 것을 이용해 쿼리짜기 편하게 자유도를 준다.
    //쿼리를 도메인으로만 조회하기에는 너무 귀찮은게 많다.
    //같은 모듈 내에서는 조인을 허용하며, 타 모듈 테이블은 조인 금지
    //  private final ProductQueryRepository productQuery;

    //일단 여기서는 추가 구현하기 귀찮아서 domain 것을 활용하여 조회했다.
    private final ProductRepository productRepository;

    @Override
    public GetProductByIdResult handle(GetProductById query) {
        var product = productRepository.getById(query.id())
                .orElseThrow(() -> new BusinessRuleException(PRODUCT_NOT_EXISTS));

        return GetProductByIdMapper.INSTANCE.mappingProduct(product);
    }
}
