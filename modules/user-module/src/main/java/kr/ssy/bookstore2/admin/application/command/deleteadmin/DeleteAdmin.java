package kr.ssy.bookstore2.admin.application.command.deleteadmin;

import kr.ssy.bookstore2.admin.application.contracts.Command;

public record DeleteAdmin(
        long id
) implements Command<DeleteAdminResult> {
}
