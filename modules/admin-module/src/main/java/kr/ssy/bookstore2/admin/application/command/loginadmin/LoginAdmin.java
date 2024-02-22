package kr.ssy.bookstore2.admin.application.command.loginadmin;

import kr.ssy.bookstore2.admin.application.contracts.Command;


public record LoginAdmin(
        String email,
        String password
) implements Command<LoginAdminResult> {
}
