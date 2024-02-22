package kr.ssy.bookstore2.adminapi.api.book;

import io.swagger.v3.oas.annotations.tags.Tag;
import kr.ssy.bookstore2.book.application.contracts.BookModule;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "책의 기본 정보를 저장, 책의 재고 관리, 입고 및 출고 기능을 제공합니다.\n" +
        "책의 주문 처리와 배송 관리를 지원합니다.\n" +
        "책의 리뷰 및 평점을 관리할 수 있는 기능을 추가할 수 있습니다.", description = "")
@Validated
@RequestMapping("/books")
@RestController
@RequiredArgsConstructor
public class BookController {
    private final BookModule bookModule;

}
