package kr.ssy.bookstore2.admin.domain.admin;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import kr.ssy.bookstore2.admin.domain.admin.enumtype.AdminAuthorityType;
import kr.ssy.bookstore2.admin.domain.admin.events.RegisterAdminDomainEvent;
import kr.ssy.bookstore2.buildingblocks.domain.AggregateRoot;
import kr.ssy.bookstore2.buildingblocks.domain.AuditLog;
import kr.ssy.bookstore2.buildingblocks.domain.Auditable;
import kr.ssy.bookstore2.buildingblocks.domain.Entity;
import lombok.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Setter(AccessLevel.PRIVATE)
@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@EqualsAndHashCode(of = "id", callSuper = false)
public class Admin extends AggregateRoot implements Entity, Auditable {

    private long id;

    @NotBlank
    private String email;

    @NotBlank
    private String password;

    @NotBlank
    private String name;

    private LocalDateTime insertDate;
    private LocalDateTime updateDate;

    @JsonManagedReference
    @NotEmpty
    private List<@Valid AdminAuthority> adminAuthorityList = new ArrayList<>();


    private final AuditLog auditLog = new AuditLog();

    public Admin(long id, String email, List<AdminAuthority> adminAuthorityList) {
        this.id = id;
        this.email = email;
        this.adminAuthorityList = adminAuthorityList;
        //  adminAuthorityList.forEach(authority -> this.adminAuthorityList.add(new AdminAuthority(authority.getAuthorityType())));

        addDomainEvent(new RegisterAdminDomainEvent(this));
    }

    public Admin(String email, String password, String name, List<AdminAuthority> adminAuthorityList) {
        this.email = email;
        this.password = password;
        this.name = name;
        this.adminAuthorityList = adminAuthorityList;

        addDomainEvent(new RegisterAdminDomainEvent(this));
    }

    public static Admin create(String email, String password, String name, List<AdminAuthorityType> adminAuthorityTypeList) {

        List<AdminAuthority> authorityList = adminAuthorityTypeList.stream()
                .map(AdminAuthority::create)
                .collect(Collectors.toList());

        return new Admin(email, password, name, authorityList);
    }

    public void addAdminAuthority(AdminAuthorityType authorityType) {
        adminAuthorityList.add(
                AdminAuthority.create(authorityType)
        );

    }
}
