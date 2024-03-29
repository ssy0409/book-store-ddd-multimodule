package kr.ssy.bookstore2.admin.domain.user;

import kr.ssy.bookstore2.admin.domain.user.enumtype.UserAuthorityType;
import lombok.*;

@Setter(AccessLevel.PRIVATE)
@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@EqualsAndHashCode(of = "id", callSuper = false)
public class AdminAuthority {
    private long id;

    private long adminId;

    private UserAuthorityType authorityType;


    public AdminAuthority(UserAuthorityType authorityType) {
        this.authorityType = authorityType;
    }

    public static AdminAuthority create(UserAuthorityType authorityType) {
        return new AdminAuthority(authorityType);
    }
}
