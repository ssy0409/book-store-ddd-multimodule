package kr.ssy.bookstore2.admin.application.query.getadminbyemail;

import kr.ssy.bookstore2.admin.application.contracts.Query;

public record GetAdminByEmail(String email) implements Query<GetAdminByEmailResult> {
}
