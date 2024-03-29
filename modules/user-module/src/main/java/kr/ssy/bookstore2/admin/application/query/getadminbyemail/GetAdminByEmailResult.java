package kr.ssy.bookstore2.admin.application.query.getadminbyemail;

import kr.ssy.bookstore2.admin.application.contracts.Result;
import kr.ssy.bookstore2.admin.domain.user.AdminAuthority;

import java.time.LocalDateTime;
import java.util.List;

public record GetAdminByEmailResult(
        Long id,
        String password,
        String email,
        String name,
        LocalDateTime insertDate,
        List<AdminAuthority> adminAuthorityList
) implements Result {
}
