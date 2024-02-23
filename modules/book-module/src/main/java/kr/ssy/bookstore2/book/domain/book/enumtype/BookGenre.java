package kr.ssy.bookstore2.book.domain.book.enumtype;

public enum BookGenre {
    FICTION("픽션"),
    NON_FICTION("비픽션"),
    MYSTERY("미스터리"),
    ROMANCE("로맨스"),
    SCIENCE_FICTION("과학 소설"),
    FANTASY("판타지"),
    HORROR("호러"),
    THRILLER("스릴러"),
    SELF_HELP("자기개발"),
    HISTORY("역사"),
    TECHNOLOGY("기술"),
    BUSINESS("경제"),
    FINANCE("금융"),
    OTHER("기타");

    private final String genreName;

    BookGenre(String genreName) {
        this.genreName = genreName;
    }

    public String getGenreName() {
        return genreName;
    }

    // String 값에서 Enum 상수를 찾아오는 메서드
    public static BookGenre fromGenreName(String genreName) {
        for (BookGenre genre : BookGenre.values()) {
            if (genre.genreName.equals(genreName)) {
                return genre;
            }
        }
        throw new IllegalArgumentException("Unknown genreName: " + genreName);
    }
}
