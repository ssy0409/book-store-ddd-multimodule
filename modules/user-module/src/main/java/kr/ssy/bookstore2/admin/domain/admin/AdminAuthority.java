package kr.ssy.bookstore2.admin.domain.admin;

import kr.ssy.bookstore2.admin.domain.admin.enumtype.AdminAuthorityType;
import lombok.*;

@Setter(AccessLevel.PRIVATE)
@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@EqualsAndHashCode(of = "id", callSuper = false)
public class AdminAuthority {
    private long id;

    private long adminId;

    private AdminAuthorityType authorityType;


    public AdminAuthority(AdminAuthorityType authorityType) {
        this.authorityType = authorityType;
    }

    public static AdminAuthority create(AdminAuthorityType authorityType) {
        return new AdminAuthority(authorityType);
    }
}
