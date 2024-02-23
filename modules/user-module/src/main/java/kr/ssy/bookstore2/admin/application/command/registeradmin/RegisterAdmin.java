package kr.ssy.bookstore2.admin.application.command.registeradmin;

import kr.ssy.bookstore2.admin.application.contracts.Command;
import kr.ssy.bookstore2.admin.domain.admin.enumtype.AdminAuthorityType;

import java.util.List;

public record RegisterAdmin(
        String email,
        String password,

        String name,
        List<AdminAuthorityType> authorityList

) implements Command<RegisterAdminResult> {
}
