package kr.ssy.bookstore2.admin.domain.admin;

public interface AdminRepository {

    long save(Admin newAdmin);

    int deleteAdmin(long id);
}
