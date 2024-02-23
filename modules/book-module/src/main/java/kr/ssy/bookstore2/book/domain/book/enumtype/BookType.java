package kr.ssy.bookstore2.book.domain.book.enumtype;

public enum BookType {
    GENERAL_BOOK("일반 서적"),
    SUBSCRIPTION("정기 구독");
    private final String description;

    BookType(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

}
