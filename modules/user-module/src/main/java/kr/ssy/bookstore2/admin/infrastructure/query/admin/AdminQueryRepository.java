package kr.ssy.bookstore2.admin.infrastructure.query.admin;

import kr.ssy.bookstore2.admin.domain.admin.Admin;

import java.util.Optional;

public interface AdminQueryRepository {
    Optional<Admin> getAdminById(long id);

    Optional<Admin> getAdminByEmail(String email);
}
