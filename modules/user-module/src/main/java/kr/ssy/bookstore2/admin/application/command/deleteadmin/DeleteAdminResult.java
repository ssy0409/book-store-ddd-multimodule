package kr.ssy.bookstore2.admin.application.command.deleteadmin;

import kr.ssy.bookstore2.admin.application.contracts.Result;

public record DeleteAdminResult(
        int deletedRowCount
) implements Result {
}
