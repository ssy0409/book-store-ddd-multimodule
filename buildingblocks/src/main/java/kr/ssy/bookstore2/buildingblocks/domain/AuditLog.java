package kr.ssy.bookstore2.buildingblocks.domain;

import java.time.LocalDateTime;
import java.time.ZonedDateTime;

import jakarta.validation.constraints.NotNull;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@EqualsAndHashCode
public class AuditLog {

    @NotNull
    private LocalDateTime createdAt;
    private Long createdBy;
    @NotNull
    private LocalDateTime updatedAt;
    private Long updatedBy;
}
