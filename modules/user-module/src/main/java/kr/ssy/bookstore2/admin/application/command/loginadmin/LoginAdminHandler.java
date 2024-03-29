package kr.ssy.bookstore2.admin.application.command.loginadmin;

import kr.ssy.bookstore2.admin.application.contracts.CommandHandler;
import kr.ssy.bookstore2.admin.infrastructure.query.user.UserQueryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class LoginAdminHandler implements CommandHandler<LoginAdmin, LoginAdminResult> {
    private final UserQueryRepository adminQueryRepository;
    private final PasswordEncoder passwordEncoder;

    @Transactional
    @Override
    public LoginAdminResult handle(LoginAdmin command) {
        var getAdmin = adminQueryRepository.getAdminByEmail(command.email());

        Optional.ofNullable(getAdmin)
                .filter(admin ->
                        !passwordEncoder.matches(
                                admin.get().getPassword(),
                                passwordEncoder.encode(command.password())
                        )
                ).orElseThrow(
                        () -> new BadCredentialsException("Not exists ID or wrong password.")
                );

        return new LoginAdminResult(getAdmin.get());
    }
}
