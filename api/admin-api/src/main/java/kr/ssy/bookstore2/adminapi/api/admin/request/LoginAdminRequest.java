package kr.ssy.bookstore2.adminapi.api.admin.request;

import io.swagger.v3.oas.annotations.media.Schema;

import static io.swagger.v3.oas.annotations.media.Schema.RequiredMode.REQUIRED;

public record LoginAdminRequest(
        @Schema(name = "email",
                description = "로그인 이메일",
                requiredMode = REQUIRED
        )
        String email,
        @Schema(name = "password",
                description = "로그인 시 사용할 이메일",
                requiredMode = REQUIRED
        )
        String password
) {
}
