package kr.ssy.bookstore2.admin.infrastructure.query.user;

import kr.ssy.bookstore2.admin.domain.user.Admin;
import kr.ssy.bookstore2.admin.domain.user.Client;

import java.util.Optional;

public interface UserQueryRepository {
    Optional<Admin> getAdminById(long id);

    Optional<Admin> getAdminByEmail(String email);

    Optional<Client> getClientByEmail(String email);
}
