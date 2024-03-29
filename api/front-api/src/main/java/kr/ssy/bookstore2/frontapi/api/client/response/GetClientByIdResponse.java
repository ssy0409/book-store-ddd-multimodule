package kr.ssy.bookstore2.frontapi.api.client.response;

import kr.ssy.bookstore2.admin.domain.user.AdminAuthority;

import java.time.LocalDateTime;
import java.util.List;

public record GetClientByIdResponse(
        Long id,
        String password,
        String email,
        String name,
        LocalDateTime insertDate,
        List<AdminAuthority> adminAuthorityList
) {
}
