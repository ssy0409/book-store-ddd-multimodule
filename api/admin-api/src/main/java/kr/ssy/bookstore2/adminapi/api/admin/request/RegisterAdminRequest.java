package kr.ssy.bookstore2.adminapi.api.admin.request;

import io.swagger.v3.oas.annotations.media.Schema;
import kr.ssy.bookstore2.admin.domain.user.enumtype.UserAuthorityType;

import java.util.List;

import static io.swagger.v3.oas.annotations.media.Schema.RequiredMode.REQUIRED;

public record RegisterAdminRequest(
        @Schema(name = "email",
                description = "로그인 시 사용할 이메일",
                requiredMode = REQUIRED
        )
        String email,
        @Schema(name = "password",
                description = "로그인시 사용할 비밀번호",
                requiredMode = REQUIRED
        )
        String password,
        @Schema(name = "name",
                description = "이름",
                requiredMode = REQUIRED
        )
        String name,
        List<UserAuthorityType> authorityList
) {
}
