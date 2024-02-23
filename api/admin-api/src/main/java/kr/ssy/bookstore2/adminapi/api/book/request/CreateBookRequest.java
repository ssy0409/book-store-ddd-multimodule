package kr.ssy.bookstore2.adminapi.api.book.request;

import io.swagger.v3.oas.annotations.media.Schema;
import kr.ssy.bookstore2.book.domain.book.enumtype.BookGenre;
import kr.ssy.bookstore2.book.domain.book.enumtype.BookStatus;
import kr.ssy.bookstore2.book.domain.book.enumtype.BookType;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import static io.swagger.v3.oas.annotations.media.Schema.RequiredMode.NOT_REQUIRED;
import static io.swagger.v3.oas.annotations.media.Schema.RequiredMode.REQUIRED;

public record CreateBookRequest(
        @Schema(
                name = "parentId",
                description = "전집일 경우 상위 bookid",
                requiredMode = NOT_REQUIRED
        )
        long parentId,
        @Schema(
                name = "categoryId",
                description = "카테고리id",
                requiredMode = REQUIRED
        )
        long categoryId,
        @Schema(
                name = "isbn",
                description = "isbn",
                requiredMode = REQUIRED
        )
        String isbn,
        @Schema(
                name = "title",
                description = "책 제목",
                requiredMode = REQUIRED
        )
        String title,
        @Schema(
                name = "author",
                description = "작가",
                requiredMode = REQUIRED
        )
        String author,
        @Schema(
                name = "publicationDate",
                description = "출판일",
                requiredMode = REQUIRED
        )
        LocalDate publicationDate,
        @Schema(
                name = "publisher",
                description = "출판사",
                requiredMode = REQUIRED
        )
        String publisher,
        @Schema(
                name = "thumbnailUrl",
                description = "썸네일 이미지 주소",
                requiredMode = NOT_REQUIRED

        )
        String thumbnailUrl,
        @Schema(
                name = "contents",
                description = "내용",
                requiredMode = NOT_REQUIRED

        )
        String contents,
        @Schema(
                name = "retailPrice",
                description = "상품원가",
                requiredMode = REQUIRED

        )
        BigDecimal retailPrice,
        @Schema(
                name = "salesPrice",
                description = "할인된 판매가",
                requiredMode = REQUIRED
        )
        BigDecimal salesPrice,
        @Schema(
                name = "type",
                description = "상품 종류: 일반 서적,정기 구독",
                requiredMode = REQUIRED
        )
        BookType type,
        @Schema(
                name = "salesOpenedAt",
                description = "판매 시작일",
                requiredMode = REQUIRED
        )
        LocalDate salesOpenedAt,
        @Schema(
                name = "salesClosedAt",
                description = "판매 종료일",
                requiredMode = REQUIRED
        )
        LocalDate salesClosedAt,
        @Schema(
                name = "status",
                description = "상품 상태 판매중, 판매중지",
                requiredMode = REQUIRED
        )
        BookStatus status,
        @Schema(
                name = "bookGenreList",
                description = "장르 List",
                requiredMode = REQUIRED
        )
        List<BookGenre> bookGenreList,
        @Schema(
                name = "quantityAvailable",
                description = "재고",
                requiredMode = REQUIRED
        )
        int quantityAvailable
) {
}
