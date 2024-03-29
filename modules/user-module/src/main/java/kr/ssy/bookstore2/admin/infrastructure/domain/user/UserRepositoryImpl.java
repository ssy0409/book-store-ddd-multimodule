package kr.ssy.bookstore2.admin.infrastructure.domain.user;

import kr.ssy.bookstore2.admin.domain.user.Admin;
import kr.ssy.bookstore2.admin.domain.user.AdminRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

@Slf4j
@Repository
@RequiredArgsConstructor
public class UserRepositoryImpl implements AdminRepository {
    private final UserMyBatisMapper userMyBatisMapper;


    @Override
    public long save(Admin newAdmin) {
        userMyBatisMapper.insertAdmin(newAdmin);
        userMyBatisMapper.insertAdminAuthorityList(newAdmin);
        return newAdmin.getId();
    }

    @Override
    public int deleteAdmin(long id) {
        return userMyBatisMapper.deleteAdmin(id);
    }

}
