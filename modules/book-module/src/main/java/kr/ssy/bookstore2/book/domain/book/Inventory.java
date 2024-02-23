package kr.ssy.bookstore2.book.domain.book;

import jakarta.validation.constraints.NotNull;
import kr.ssy.bookstore2.buildingblocks.domain.AggregateRoot;
import kr.ssy.bookstore2.buildingblocks.domain.AuditLog;
import kr.ssy.bookstore2.buildingblocks.domain.Auditable;
import kr.ssy.bookstore2.buildingblocks.domain.Entity;
import lombok.*;

@Setter(AccessLevel.PRIVATE)
@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@EqualsAndHashCode(of = "id", callSuper = false)
public class Inventory extends AggregateRoot implements Entity, Auditable {
    private long id;

    @NotNull
    private long bookId;

    @NotNull
    private int quantityAvailable;

    private final AuditLog auditLog = new AuditLog();
}
