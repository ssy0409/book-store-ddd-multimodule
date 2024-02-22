package kr.ssy.bookstore2.product.domain.product.rules;

import kr.ssy.bookstore2.buildingblocks.domain.BusinessRule;
import kr.ssy.bookstore2.buildingblocks.domain.BusinessRuleMessage;
import kr.ssy.bookstore2.product.domain.ProductBusinessRuleMessage;
import kr.ssy.bookstore2.product.domain.product.Product;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class ProductPriceRule implements BusinessRule {

    private final Product product;

    @Override
    public boolean isBroken() {
        if (product.getMsrPrice() == null || product.getSalesPrice() == null) {
            return true;
        }

        return product.getMsrPrice().compareTo(product.getSalesPrice()) < 0;
    }

    @Override
    public BusinessRuleMessage getMessage() {
        return ProductBusinessRuleMessage.PRODUCT_INVALID_PRICE;
    }
}
