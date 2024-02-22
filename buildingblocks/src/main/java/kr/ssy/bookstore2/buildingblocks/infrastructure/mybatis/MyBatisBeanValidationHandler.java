package kr.ssy.bookstore2.buildingblocks.infrastructure.mybatis;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import jakarta.validation.Validator;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.mapping.SqlCommandType;
import org.springframework.stereotype.Component;

import java.util.Set;


@Slf4j
@Component
@RequiredArgsConstructor
public class MyBatisBeanValidationHandler {

    private final Validator validator;

    public void handle(Object target, SqlCommandType commandType) {
        Set<ConstraintViolation<Object>> violationSet = validator.validate(target);
        if (!violationSet.isEmpty()) {
            throw new ConstraintViolationException(violationSet);
        }
    }
}
