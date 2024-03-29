package kr.ssy.bookstore2.admin.domain.user;


import jakarta.validation.constraints.NotBlank;
import kr.ssy.bookstore2.admin.domain.user.enumtype.UserAuthorityType;
import kr.ssy.bookstore2.admin.domain.user.events.RegisterClientDomainEvent;
import kr.ssy.bookstore2.buildingblocks.domain.AggregateRoot;
import kr.ssy.bookstore2.buildingblocks.domain.AuditLog;
import kr.ssy.bookstore2.buildingblocks.domain.Auditable;
import kr.ssy.bookstore2.buildingblocks.domain.Entity;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Setter(AccessLevel.PRIVATE)
@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@EqualsAndHashCode(of = "id", callSuper = false)
public class Client extends AggregateRoot implements Entity, Auditable {

    private long id;

    @NotBlank
    private String email;

    @NotBlank
    private String password;

    @NotBlank
    private String name;

    private LocalDateTime insertDate;
    private LocalDateTime updateDate;


    private final AuditLog auditLog = new AuditLog();

    public Client(long id, String email, List<AdminAuthority> adminAuthorityList) {
        this.id = id;
        this.email = email;

        addDomainEvent(new RegisterClientDomainEvent(this));
    }

    private Client(String email, String password, String name, List<AdminAuthority> adminAuthorityList) {
        this.email = email;
        this.password = password;
        this.name = name;

        addDomainEvent(new RegisterClientDomainEvent(this));
    }

    public static Client create(String email, String password, String name, List<UserAuthorityType> userAuthorityTypeList) {

        List<AdminAuthority> authorityList = userAuthorityTypeList.stream()
                .map(AdminAuthority::create)
                .collect(Collectors.toList());

        return new Client(email, password, name, authorityList);
    }


}
