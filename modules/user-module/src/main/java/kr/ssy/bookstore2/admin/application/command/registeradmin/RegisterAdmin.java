package kr.ssy.bookstore2.admin.application.command.registeradmin;

import kr.ssy.bookstore2.admin.application.contracts.Command;
import kr.ssy.bookstore2.admin.domain.user.enumtype.UserAuthorityType;

import java.util.List;

public record RegisterAdmin(
        String email,
        String password,

        String name,
        List<UserAuthorityType> authorityList

) implements Command<RegisterAdminResult> {
}
