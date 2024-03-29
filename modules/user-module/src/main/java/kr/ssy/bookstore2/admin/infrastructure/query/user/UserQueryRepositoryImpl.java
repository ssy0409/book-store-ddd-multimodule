package kr.ssy.bookstore2.admin.infrastructure.query.user;

import kr.ssy.bookstore2.admin.domain.user.Admin;
import kr.ssy.bookstore2.admin.domain.user.Client;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Slf4j
@Repository
@RequiredArgsConstructor
public class UserQueryRepositoryImpl implements UserQueryRepository {
    private final UserQueryMyBatisMapper userQueryMyBatisMapper;

    @Override
    public Optional<Admin> getAdminById(long id) {
        return Optional.ofNullable(userQueryMyBatisMapper.getAdminById(id));
    }

    @Override
    public Optional<Admin> getAdminByEmail(String email) {
        return Optional.ofNullable(userQueryMyBatisMapper.getAdminByEmail(email));
    }

    @Override
    public Optional<Client> getClientByEmail(String email) {
        return Optional.empty();
    }
}
