package kr.ssy.bookstore2.buildingblocks.infrastructure.mybatis;

import kr.ssy.bookstore2.buildingblocks.application.ExecutionContextAccessor;
import kr.ssy.bookstore2.buildingblocks.domain.AuditLog;
import kr.ssy.bookstore2.buildingblocks.domain.Auditable;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.mapping.SqlCommandType;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Slf4j
@Component
@RequiredArgsConstructor
public class MyBatisAuditLogHandler {

    private final ExecutionContextAccessor executionContextAccessor;

    public void handle(Object target, SqlCommandType commandType) {
        if (target instanceof Auditable auditable) {
            AuditLog auditLog = auditable.getAuditLog();
            if (auditLog == null) {
                return;
            }

            Long userId = executionContextAccessor.getUserId();
            var now = LocalDateTime.now();
            if (SqlCommandType.INSERT == commandType) {
                auditLog.setCreatedBy(userId);
                auditLog.setCreatedAt(now);
                auditLog.setUpdatedBy(userId);
                auditLog.setUpdatedAt(now);
            } else if (SqlCommandType.UPDATE == commandType) {
                auditLog.setUpdatedBy(userId);
                auditLog.setUpdatedAt(now);
            }
        }
    }
}
