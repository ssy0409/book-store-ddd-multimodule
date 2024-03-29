package kr.ssy.bookstore2.admin.application.query.getclientbyemail;

import kr.ssy.bookstore2.admin.application.contracts.Query;

public record GetClientByEmail(String email) implements Query<GetClientByEmailResult> {
}
