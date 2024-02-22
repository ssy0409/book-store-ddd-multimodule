package kr.ssy.bookstore2.admin.application.query.getadminbyid;

import kr.ssy.bookstore2.admin.application.contracts.Result;
import kr.ssy.bookstore2.admin.domain.admin.AdminAuthority;

import java.time.LocalDateTime;
import java.util.List;

public record GetAdminByIdResult(
        Long id,
        String password,
        String email,
        String name,
        LocalDateTime insertDate,
        List<AdminAuthority> adminAuthorityList
) implements Result {
}
