package kr.ssy.bookstore2.adminapi.config.security;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;

@RequiredArgsConstructor
public class RoleGrantAuthority implements GrantedAuthority {


    private final String roleName;


    @Override
    public String getAuthority() {
        return this.roleName;
    }

    @Getter
    @RequiredArgsConstructor
    public enum SystemUser {
        ANONYMOUS(-9999),
        ;

        private final int id;
    }

    @Getter
    @RequiredArgsConstructor
    public enum SystemAdmin {
        ANONYMOUS(-8888),
        ;

        private final int id;
    }
}
