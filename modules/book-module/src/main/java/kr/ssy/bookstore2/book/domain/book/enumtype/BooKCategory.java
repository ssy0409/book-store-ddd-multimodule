package kr.ssy.bookstore2.book.domain.book.enumtype;

public enum BooKCategory {
    DOMESTIC("국내"),
    FOREIGN("해외"),
    NOVEL("소설"),
    ESSAY("에세이"),
    BIOGRAPHY("전기"),
    COOKBOOK("요리"),
    POETRY("시"),
    ART("예술"),
    CHILDREN("어린이"),
    EDUCATION("교육"),
    RELIGION("종교"),
    TRAVEL("여행"),
    SPORTS("스포츠"),
    SCIENCE("과학"),
    OTHER("기타");
    private final String description;

    BooKCategory(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

}
