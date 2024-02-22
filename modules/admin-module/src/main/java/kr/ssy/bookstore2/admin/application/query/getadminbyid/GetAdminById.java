package kr.ssy.bookstore2.admin.application.query.getadminbyid;

import kr.ssy.bookstore2.admin.application.contracts.Query;

public record GetAdminById(long id) implements Query<GetAdminByIdResult> {
}
