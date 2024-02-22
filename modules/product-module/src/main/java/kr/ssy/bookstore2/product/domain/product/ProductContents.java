package kr.ssy.bookstore2.product.domain.product;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import kr.ssy.bookstore2.buildingblocks.domain.AuditLog;
import kr.ssy.bookstore2.buildingblocks.domain.Auditable;
import kr.ssy.bookstore2.buildingblocks.domain.Entity;
import kr.ssy.bookstore2.product.domain.product.enumtype.ContentsPositionType;
import lombok.*;

@Setter(AccessLevel.PRIVATE)
@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@EqualsAndHashCode(of = "id")
public class ProductContents implements Entity, Auditable {

    private long id;

    @NotNull
    @JsonBackReference
    private Product product; // for refernece key

    @Positive
    private long contentsId;

    @NotNull
    private ContentsPositionType contentsPositionType;

    @Positive
    private int orderIndex;

    private boolean deleted;

    private final AuditLog auditLog = new AuditLog();

    ProductContents(Product product, long contentsId,
                    ContentsPositionType contentsPositionType, int orderIndex) {
        setProduct(product);
        setContentsId(contentsId);
        setContentsPositionType(contentsPositionType);
        setOrderIndex(orderIndex);
    }

    static ProductContents create(Product product, long contentsId,
                                  ContentsPositionType contentsPositionType, int orderIndex) {

        return new ProductContents(product, contentsId,
                contentsPositionType, orderIndex);
    }

    void delete() {
        this.deleted = true;
    }

}
