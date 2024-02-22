package kr.ssy.bookstore2.product.application.command.createproduct;

import kr.ssy.bookstore2.buildingblocks.domain.BusinessRuleException;
import kr.ssy.bookstore2.product.application.contracts.CommandHandler;
import kr.ssy.bookstore2.product.domain.contents.Contents;
import kr.ssy.bookstore2.product.domain.contents.ContentsRepository;
import kr.ssy.bookstore2.product.domain.product.Product;
import kr.ssy.bookstore2.product.domain.product.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashSet;

import static kr.ssy.bookstore2.product.domain.ProductBusinessRuleMessage.PRODUCT_CONTENTS_NOT_EXISTS;

@Service
@RequiredArgsConstructor
class CreateProductHandler implements CommandHandler<CreateProduct, CreateProductResult> {

    private final ProductRepository productRepository;
    private final ContentsRepository contentsRepository;

    @Override
    public CreateProductResult handle(CreateProduct command) {

        var newProduct = Product.create(
                command.name(),
                command.description(),
                command.msrPrice(),
                command.salesPrice(),
                command.salesOpenedAt(),
                command.salesClosedAt()
        );

        var existsContentsCheckSet = new HashSet<>(
                contentsRepository.getByIds(
                                command.productContentsList()
                                        .stream()
                                        .map(CreateProduct.ProductContents::contentsId)
                                        .toList()
                        ).stream()
                        .map(Contents::getId)
                        .toList()
        );

        for (var data : command.productContentsList()) {
            if (!existsContentsCheckSet.contains(data.contentsId())) {
                throw new BusinessRuleException(PRODUCT_CONTENTS_NOT_EXISTS,
                        Long.toString(data.contentsId()));
            }

            newProduct.addProductContents(
                    data.contentsId(),
                    data.contentsPositionType(),
                    data.orderIndex()
            );
        }

        productRepository.save(newProduct);


        return new CreateProductResult(newProduct.getId());
    }
}
