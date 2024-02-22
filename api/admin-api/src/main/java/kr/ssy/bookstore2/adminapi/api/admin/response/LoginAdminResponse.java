package kr.ssy.bookstore2.adminapi.api.admin.response;

public record LoginAdminResponse(
        String token,
        long id
) {
}
