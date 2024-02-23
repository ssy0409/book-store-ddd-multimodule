package kr.ssy.bookstore2.admin.infrastructure.domain.admin;

import kr.ssy.bookstore2.admin.domain.admin.Admin;
import kr.ssy.bookstore2.admin.domain.admin.AdminRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

@Slf4j
@Repository
@RequiredArgsConstructor
public class AdminRepositoryImpl implements AdminRepository {
    private final AdminMyBatisMapper adminMyBatisMapper;


    @Override
    public long save(Admin newAdmin) {
        adminMyBatisMapper.insertAdmin(newAdmin);
        adminMyBatisMapper.insertAdminAuthorityList(newAdmin);
        return newAdmin.getId();
    }

    @Override
    public int deleteAdmin(long id) {
        return adminMyBatisMapper.deleteAdmin(id);
    }

}
