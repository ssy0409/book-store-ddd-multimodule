package kr.ssy.bookstore2.adminapi.api.admin.response;

import kr.ssy.bookstore2.admin.domain.admin.AdminAuthority;

import java.time.LocalDateTime;
import java.util.List;

public record GetAdminByIdResponse(
        Long id,
        String password,
        String email,
        String name,
        LocalDateTime insertDate,
        List<AdminAuthority> adminAuthorityList
) {
}
