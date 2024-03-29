package kr.ssy.bookstore2.admin.application.command.loginadmin;

import kr.ssy.bookstore2.admin.application.contracts.Result;
import kr.ssy.bookstore2.admin.domain.user.Admin;

public record LoginAdminResult(
        Admin admin
) implements Result {
}
