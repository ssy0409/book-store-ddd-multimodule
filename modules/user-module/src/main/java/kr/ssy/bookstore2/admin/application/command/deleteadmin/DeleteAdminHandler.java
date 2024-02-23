package kr.ssy.bookstore2.admin.application.command.deleteadmin;

import kr.ssy.bookstore2.admin.application.contracts.CommandHandler;
import kr.ssy.bookstore2.admin.domain.admin.AdminRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DeleteAdminHandler implements CommandHandler<DeleteAdmin, DeleteAdminResult> {
    private final AdminRepository adminRepository;

    @Override
    public DeleteAdminResult handle(DeleteAdmin command) {
        var result = adminRepository.deleteAdmin(command.id());
        return new DeleteAdminResult(result);
    }
}
