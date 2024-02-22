package kr.ssy.bookstore2.product.domain.product;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import kr.ssy.bookstore2.buildingblocks.domain.*;
import kr.ssy.bookstore2.product.domain.product.enumtype.ContentsPositionType;
import kr.ssy.bookstore2.product.domain.product.enumtype.ProductStatus;
import kr.ssy.bookstore2.product.domain.product.events.ProductCreatedDomainEvent;
import kr.ssy.bookstore2.product.domain.product.events.ProductUpdatedDomainEvent;
import kr.ssy.bookstore2.product.domain.product.rules.ProductPriceRule;
import kr.ssy.bookstore2.product.domain.product.rules.ProductSalesPeriodRule;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static kr.ssy.bookstore2.product.domain.ProductBusinessRuleMessage.PRODUCT_CANNOT_START_ON_SALE;
import static kr.ssy.bookstore2.product.domain.ProductBusinessRuleMessage.PRODUCT_ONLY_EDITABLE_STATUS_IN_DRAFTED;
import static kr.ssy.bookstore2.product.domain.product.enumtype.ProductStatus.*;

@Setter(AccessLevel.PRIVATE)
@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@EqualsAndHashCode(of = "id", callSuper = false)
public class Product extends AggregateRoot implements Entity, Auditable {

    private long id;

    @NotBlank
    private String name;

    private String description;

    @NotNull
    private BigDecimal msrPrice;

    @NotNull
    private BigDecimal salesPrice;

    @NotNull
    private LocalDateTime salesOpenedAt;

    private LocalDateTime salesClosedAt;

    @NotNull
    private ProductStatus status;

    private boolean deleted;

    @JsonManagedReference
    @NotEmpty
    private final List<@Valid ProductContents> productContentsList = new ArrayList<>();

    private final AuditLog auditLog = new AuditLog();

    private Product(String name, String description, BigDecimal msrPrice,
                    BigDecimal salesPrice,
                    LocalDateTime salesOpenedAt, LocalDateTime salesClosedAt) {
        setName(name);
        setDescription(description);
        setMsrPrice(msrPrice);
        setSalesPrice(salesPrice);
        setSalesOpenedAt(salesOpenedAt);
        setSalesClosedAt(salesClosedAt);
        setStatus(ProductStatus.DRAFTED);

        checkRule(new ProductPriceRule(this));
        checkRule(new ProductSalesPeriodRule(this));

        addDomainEvent(new ProductCreatedDomainEvent(this));
    }

    public static Product create(String name, String description,
                                 BigDecimal msrPrice, BigDecimal salesPrice,
                                 LocalDateTime salesOpenedAt, LocalDateTime salesClosedAt) {
        return new Product(name, description, msrPrice, salesPrice, salesOpenedAt,
                salesClosedAt);
    }

    public void update(String name, String description, BigDecimal msrPrice, BigDecimal salesPrice,
                       LocalDateTime salesOpenedAt, LocalDateTime salesClosedAt) {

        if (getStatus() != ProductStatus.DRAFTED) {
            throw new BusinessRuleException(PRODUCT_ONLY_EDITABLE_STATUS_IN_DRAFTED);
        }

        setName(name);
        setDescription(description);
        setMsrPrice(msrPrice);
        setSalesPrice(salesPrice);
        setSalesOpenedAt(salesOpenedAt);
        setSalesClosedAt(salesClosedAt);

        checkRule(new ProductPriceRule(this));
        checkRule(new ProductSalesPeriodRule(this));

        addDomainEvent(new ProductUpdatedDomainEvent(this));
    }

    public void deleteAllProductContents() {
        productContentsList.forEach(ProductContents::delete);
    }

    public void delete() {
        if (getStatus() != ProductStatus.DRAFTED) {
            throw new BusinessRuleException(PRODUCT_ONLY_EDITABLE_STATUS_IN_DRAFTED);
        }
        setDeleted(deleted);
        this.deleteAllProductContents();
    }

    public void addProductContents(long contentsId,
                                   ContentsPositionType contentsPositionType, int orderIndex) {
        productContentsList.add(
                ProductContents.create(this, contentsId, contentsPositionType, orderIndex)
        );
    }

    public void startSales(boolean forceStart) {
        var now = LocalDateTime.now();

        if (forceStart) {
            setSalesOpenedAt(now);
            setStatus(ON_SALE);

            return;
        }

        if (now.isBefore(getSalesOpenedAt())
                || now.isAfter(getSalesClosedAt())) {
            throw new BusinessRuleException(PRODUCT_CANNOT_START_ON_SALE);

        }
        setStatus(ON_SALE);
    }

    public void hold() {
        setStatus(HOLD);
    }


    public void soldOut() {
        setStatus(SOLD_OUT);
    }


    public void close() {
        setSalesClosedAt(LocalDateTime.now());
        setStatus(CLOSED);
    }


}
