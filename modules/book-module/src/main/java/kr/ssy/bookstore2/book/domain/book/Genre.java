package kr.ssy.bookstore2.book.domain.book;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import kr.ssy.bookstore2.book.domain.book.enumtype.BookGenre;
import kr.ssy.bookstore2.buildingblocks.domain.AggregateRoot;
import kr.ssy.bookstore2.buildingblocks.domain.AuditLog;
import kr.ssy.bookstore2.buildingblocks.domain.Auditable;
import kr.ssy.bookstore2.buildingblocks.domain.Entity;
import lombok.*;

@Setter(AccessLevel.PRIVATE)
@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@EqualsAndHashCode(of = "id", callSuper = false)
public class Genre extends AggregateRoot implements Entity, Auditable {
    private long id;

    @NotNull
    private long bookId;

    @NotBlank
    private BookGenre name;

    private final AuditLog auditLog = new AuditLog();

    private Genre(long bookId, BookGenre name) {
        this.bookId = bookId;
        this.name = name;
    }

    public static Genre create(long bookId, BookGenre name) {
        return new Genre(bookId, name);
    }
}
