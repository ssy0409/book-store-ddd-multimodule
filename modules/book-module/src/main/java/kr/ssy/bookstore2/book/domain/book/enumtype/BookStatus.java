package kr.ssy.bookstore2.book.domain.book.enumtype;

public enum BookStatus {
    ON_SALE("판매중"),
    SOLD_OUT("재고없음"),
    OFF_SALE("판매중지");
    private final String description;

    BookStatus(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

}
