package kr.ssy.bookstore2.product.domain.product.events;

import kr.ssy.bookstore2.buildingblocks.domain.AbstractDomainEvent;
import kr.ssy.bookstore2.product.domain.product.Product;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class ProductCreatedDomainEvent extends AbstractDomainEvent {

    private final Product product;
}
