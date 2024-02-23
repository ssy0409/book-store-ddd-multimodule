package kr.ssy.bookstore2.admin.infrastructure.query.admin;

import kr.ssy.bookstore2.admin.domain.admin.Admin;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Slf4j
@Repository
@RequiredArgsConstructor
public class AdminQueryRepositoryImpl implements AdminQueryRepository {
    private final AdminQueryMyBatisMapper adminQueryMyBatisMapper;

    @Override
    public Optional<Admin> getAdminById(long id) {
        return Optional.ofNullable(adminQueryMyBatisMapper.getAdminById(id));
    }

    @Override
    public Optional<Admin> getAdminByEmail(String email) {
        return Optional.ofNullable(adminQueryMyBatisMapper.getAdminByEmail(email));
    }
}
