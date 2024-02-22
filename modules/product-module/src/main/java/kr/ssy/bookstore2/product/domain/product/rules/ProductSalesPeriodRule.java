package kr.ssy.bookstore2.product.domain.product.rules;

import kr.ssy.bookstore2.buildingblocks.domain.BusinessRule;
import kr.ssy.bookstore2.buildingblocks.domain.BusinessRuleMessage;
import kr.ssy.bookstore2.product.domain.ProductBusinessRuleMessage;
import kr.ssy.bookstore2.product.domain.product.Product;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class ProductSalesPeriodRule implements BusinessRule {

    private final Product product;

    @Override
    public boolean isBroken() {
        if (product.getSalesOpenedAt() == null) {
            return true;
        }

        if (product.getSalesClosedAt() == null) { // unlimited
            return false;
        }

        return product.getSalesOpenedAt().isAfter(product.getSalesClosedAt())
                || product.getSalesOpenedAt().isEqual(product.getSalesClosedAt());
    }

    @Override
    public BusinessRuleMessage getMessage() {
        return ProductBusinessRuleMessage.PRODUCT_INVALID_SALES_PERIOD;
    }
}
