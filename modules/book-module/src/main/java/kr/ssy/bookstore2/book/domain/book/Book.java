package kr.ssy.bookstore2.book.domain.book;


import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import kr.ssy.bookstore2.book.domain.book.enumtype.BookGenre;
import kr.ssy.bookstore2.book.domain.book.enumtype.BookStatus;
import kr.ssy.bookstore2.book.domain.book.enumtype.BookType;
import kr.ssy.bookstore2.book.domain.book.events.CreateBookDomainEvent;
import kr.ssy.bookstore2.book.domain.book.rules.BookPriceRule;
import kr.ssy.bookstore2.book.domain.book.rules.BookSalesPeriodRule;
import kr.ssy.bookstore2.buildingblocks.domain.AggregateRoot;
import kr.ssy.bookstore2.buildingblocks.domain.AuditLog;
import kr.ssy.bookstore2.buildingblocks.domain.Auditable;
import kr.ssy.bookstore2.buildingblocks.domain.Entity;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;


@Setter(AccessLevel.PRIVATE)
@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@EqualsAndHashCode(of = "id", callSuper = false)
public class Book extends AggregateRoot implements Entity, Auditable {
    private long id;

    @NotNull
    private long parentId;

    @NotNull
    private long categoryId;

    @NotBlank
    private String isbn;

    @NotBlank
    private String title;

    @NotBlank
    private String author;

    private LocalDate publicationDate;

    @NotBlank
    private String publisher;

    private String thumbnailUrl;

    private String contents;

    @NotNull
    private BigDecimal retailPrice;

    @NotNull
    private BigDecimal salesPrice;

    @NotBlank
    private BookType type;

    @NotNull
    private LocalDate salesOpenedAt;

    @NotNull
    private LocalDate salesClosedAt;

    @NotBlank
    private BookStatus status;

    private LocalDateTime insertDate;

    private long createBy;

    private LocalDateTime updateDate;

    private long updateBy;

    @JsonManagedReference
    @NotEmpty
    private List<@Valid Genre> genreList = new ArrayList<>();

    private final AuditLog auditLog = new AuditLog();

    private Book(long parentId,
                 long categoryId,
                 String isbn,
                 String title,
                 String author,
                 LocalDate publicationDate,
                 String publisher,
                 String thumbnailUrl,
                 String contents,
                 BigDecimal retailPrice,
                 BigDecimal salesPrice,
                 BookType type,
                 LocalDate salesOpenedAt,
                 LocalDate salesClosedAt,
                 BookStatus status
    ) {
        this.parentId = parentId;
        this.categoryId = categoryId;
        this.isbn = isbn;
        this.title = title;
        this.author = author;
        this.publicationDate = publicationDate;
        this.publisher = publisher;
        this.thumbnailUrl = thumbnailUrl;
        this.contents = contents;
        this.retailPrice = retailPrice;
        this.salesPrice = salesPrice;
        this.type = type;
        this.salesOpenedAt = salesOpenedAt;
        this.salesClosedAt = salesClosedAt;
        this.status = status;

        checkRule(new BookPriceRule(this));
        checkRule(new BookSalesPeriodRule(this));

        addDomainEvent(new CreateBookDomainEvent(this));
    }

    public static Book create(long parentId,
                              long categoryId,
                              String isbn,
                              String title,
                              String author,
                              LocalDate publicationDate,
                              String publisher,
                              String thumbnailUrl,
                              String contents,
                              BigDecimal retailPrice,
                              BigDecimal salesPrice,
                              BookType type,
                              LocalDate salesOpenedAt,
                              LocalDate salesClosedAt,
                              List<BookGenre> bookGenreList
    ) {
        Book book = new Book(parentId,
                categoryId,
                isbn,
                title,
                author,
                publicationDate,
                publisher,
                thumbnailUrl,
                contents,
                retailPrice,
                salesPrice,
                type,
                salesOpenedAt,
                salesClosedAt,
                BookStatus.ON_SALE);

        for (BookGenre genre : bookGenreList) {
            book.addGenre(genre);
        }

        return book;

    }

    public void addGenre(BookGenre genre) {
        genreList.add(
                Genre.create(this.id, genre)
        );
    }
}
