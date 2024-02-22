package kr.ssy.bookstore2.admin.application.command.registeradmin;

import kr.ssy.bookstore2.admin.application.contracts.CommandHandler;
import kr.ssy.bookstore2.admin.domain.admin.Admin;
import kr.ssy.bookstore2.admin.domain.admin.AdminRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class RegisterAdminHandler implements CommandHandler<RegisterAdmin, RegisterAdminResult> {
    private final AdminRepository adminRepository;
    private final PasswordEncoder passwordEncoder;


    @Transactional
    @Override
    public RegisterAdminResult handle(RegisterAdmin command) {
        var newAdmin = Admin.create(
                command.email(),
                passwordEncoder.encode(command.password()),
                command.name(),
                command.authorityList()
        );

        var adminId = adminRepository.save(newAdmin);

        return new RegisterAdminResult(adminId);
    }


}
