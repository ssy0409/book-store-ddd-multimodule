package kr.ssy.bookstore2.product.application.command.updateproduct;

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
import static kr.ssy.bookstore2.product.domain.ProductBusinessRuleMessage.PRODUCT_NOT_EXISTS;

@Service
@RequiredArgsConstructor
class UpdateProductHandler implements CommandHandler<UpdateProduct, UpdateProductResult> {

    private final ProductRepository productRepository;
    private final ContentsRepository contentsRepository;

    @Override
    public UpdateProductResult handle(UpdateProduct command) {
        var product = getById(command.id());
        product.update(command.name(), command.description(), command.msrPrice(), command.salesPrice(),
                command.salesOpenedAt(), command.salesClosedAt());
        product.deleteAllProductContents();

        var existsContentsCheckSet = new HashSet<>(
                contentsRepository.getByIds(
                                command.productContentsList()
                                        .stream()
                                        .map(UpdateProduct.ProductContents::contentsId)
                                        .toList()
                        ).stream()
                        .map(Contents::getId)
                        .toList()
        );

        for (var data : command.productContentsList()) {
            if (data.deleted()) {
                continue;
            }

            if (!existsContentsCheckSet.contains(data.contentsId())) {
                throw new BusinessRuleException(PRODUCT_CONTENTS_NOT_EXISTS,
                        Long.toString(data.contentsId()));
            }

            product.addProductContents(
                    data.contentsId(),
                    data.contentsPositionType(),
                    data.orderIndex()
            );
        }

        productRepository.save(product);

        return new UpdateProductResult(product.getId());
    }

    private Product getById(long id) {
        return productRepository.getById(id)
                .orElseThrow(() -> new BusinessRuleException(PRODUCT_NOT_EXISTS));
    }
}
