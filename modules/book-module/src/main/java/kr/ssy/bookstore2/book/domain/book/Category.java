package kr.ssy.bookstore2.book.domain.book;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import kr.ssy.bookstore2.book.domain.book.enumtype.BooKCategory;
import kr.ssy.bookstore2.book.domain.book.events.CreateCategoryDomainEvent;
import kr.ssy.bookstore2.buildingblocks.domain.AggregateRoot;
import kr.ssy.bookstore2.buildingblocks.domain.AuditLog;
import kr.ssy.bookstore2.buildingblocks.domain.Auditable;
import kr.ssy.bookstore2.buildingblocks.domain.Entity;
import lombok.*;

import java.time.LocalDateTime;

@Setter(AccessLevel.PRIVATE)
@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@EqualsAndHashCode(of = "id", callSuper = false)
public class Category extends AggregateRoot implements Entity, Auditable {
    private long id;

    @NotNull
    private long parentId;

    @NotBlank
    private BooKCategory name;

    @NotNull
    private int orderIndex;

    private LocalDateTime insertDate;

    private LocalDateTime updateDate;

    private final AuditLog auditLog = new AuditLog();

    private Category(long parentId, BooKCategory name, int orderIndex) {
        this.parentId = parentId;
        this.name = name;
        this.orderIndex = orderIndex;
        
        addDomainEvent(new CreateCategoryDomainEvent(this));

    }

    public static Category create(
            long parentId, BooKCategory name, int orderIndex
    ) {
        return new Category(parentId, name, orderIndex);
    }
}
