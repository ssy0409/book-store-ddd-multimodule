package kr.ssy.bookstore2.product.domain.contents;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import kr.ssy.bookstore2.buildingblocks.domain.AggregateRoot;
import kr.ssy.bookstore2.buildingblocks.domain.AuditLog;
import kr.ssy.bookstore2.buildingblocks.domain.Auditable;
import kr.ssy.bookstore2.buildingblocks.domain.Entity;
import kr.ssy.bookstore2.product.domain.contents.enumtype.ContentsType;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter(AccessLevel.PRIVATE)
@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class Contents extends AggregateRoot implements Entity, Auditable {

    private long id;

    @NotNull
    private ContentsType contentsType;

    @NotBlank
    private String name;

    private String description;

    @NotBlank
    private String data;

    private final AuditLog auditLog = new AuditLog();

    private Contents(ContentsType contentsType, String name, String description,
                     String data) {
        setContentsType(contentsType);
        setName(name);
        setDescription(description);
        setData(data);
    }

    public static Contents create(ContentsType contentsType, String name,
                                  String description, String data) {
        return new Contents(contentsType, name, description, data);
    }
}
