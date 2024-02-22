package kr.ssy.bookstore2.buildingblocks.infrastructure.mybatis;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.executor.parameter.ParameterHandler;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.mapping.SqlCommandType;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.plugin.Intercepts;
import org.apache.ibatis.plugin.Invocation;
import org.apache.ibatis.plugin.Signature;
import org.apache.ibatis.reflection.DefaultReflectorFactory;
import org.apache.ibatis.reflection.MetaObject;
import org.apache.ibatis.reflection.SystemMetaObject;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;

import java.sql.PreparedStatement;
import java.util.Collection;
import java.util.Map;

@Slf4j
@Component
@Intercepts(
        @Signature(type = ParameterHandler.class, method = "setParameters",
                args = PreparedStatement.class))
@RequiredArgsConstructor
public class MyBatisInterceptor implements Interceptor {

    private final MyBatisAuditLogHandler auditLogHandler;
    private final MyBatisBeanValidationHandler beanValidationHandler;

    @Override
    public Object intercept(Invocation invocation) throws Throwable {
        if (invocation.getTarget() instanceof ParameterHandler parameterHandler) {
            var statement = getStatement(parameterHandler);

            SqlCommandType commandType = statement.getSqlCommandType();
            Object target = parameterHandler.getParameterObject();

            if (!ObjectUtils.isEmpty(target)) {
                if (target instanceof Map<?, ?> map) {
                    for (Object value : map.values()) {
                        handleMapObject(value, commandType);
                    }
                } else {
                    handleObject(target, commandType);
                }
            }
        }

        return invocation.proceed();
    }

    private void handleObject(Object target, SqlCommandType commandType) {

        if (SqlCommandType.INSERT == commandType || SqlCommandType.UPDATE == commandType) {
            beanValidationHandler.handle(target, commandType);
            auditLogHandler.handle(target, commandType);
        }
    }

    private void handleMapObject(Object target, SqlCommandType commandType) {
        if (target instanceof Collection<?> list) {
            for (Object value : list) {
                handleObject(value, commandType);
            }
        } else {
            handleObject(target, commandType);
        }
    }

    private MappedStatement getStatement(ParameterHandler parameterHandler) {
        MetaObject metaObject = MetaObject.forObject(parameterHandler,
                SystemMetaObject.DEFAULT_OBJECT_FACTORY,
                SystemMetaObject.DEFAULT_OBJECT_WRAPPER_FACTORY, new DefaultReflectorFactory());
        return (MappedStatement) metaObject.getValue("mappedStatement");
    }
}
