package kr.ssy.bookstore2.frontapi.config.web;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import kr.ssy.bookstore2.buildingblocks.domain.BusinessRuleException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.mybatis.spring.MyBatisSystemException;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.util.AntPathMatcher;
import org.springframework.util.CollectionUtils;
import org.springframework.validation.FieldError;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.NoHandlerFoundException;

import java.util.Map;
import java.util.stream.Collectors;

@Slf4j
@RestControllerAdvice
@RequiredArgsConstructor
public class GlobalControllerErrorAdvice {

    private final MessageSource msg;

    @ExceptionHandler(BusinessRuleException.class)
    public ProblemDetail handleBusinessRuleException(
            BusinessRuleException e) {
        return createProblemDetail(
                e.getHttpStatus(),
                msg.getMessage(e.getMessageKey(), e.getMessageArguments(), LocaleContextHolder.getLocale()),
                null
        );
    }

    @ExceptionHandler({AuthenticationException.class})
    public ProblemDetail handleAuthenticationException(AuthenticationException e) {
        String originalMessage = e.getMessage();
        String message =
                (originalMessage != null) ? originalMessage.split(";")[0]
                        : "Malformed JSON request";

        return createProblemDetail(
                HttpStatus.UNAUTHORIZED,
                message,
                null
        );
    }


    @ExceptionHandler(ConstraintViolationException.class)
    public ProblemDetail handleConstraintViolationException(
            ConstraintViolationException e) {
        return createProblemDetail(
                HttpStatus.BAD_REQUEST,
                msg.getMessage("common.error.bad_parameter", null, LocaleContextHolder.getLocale()),
                e.getConstraintViolations()
                        .stream()
                        .collect(Collectors.toMap(it -> it.getPropertyPath().toString(),
                                ConstraintViolation::getMessage))
        );
    }

    @ExceptionHandler(MyBatisSystemException.class)
    public ProblemDetail handleMyBatisSystemException(MyBatisSystemException e) {
        if (e.getRootCause() instanceof ConstraintViolationException violation) {
            return handleConstraintViolationException(violation);
        }
        return handleException(e);
    }

    @ExceptionHandler(MissingServletRequestParameterException.class)
    public ProblemDetail handleMissingServletRequestParameter(
            MissingServletRequestParameterException e) {
        return createProblemDetail(
                HttpStatus.BAD_REQUEST,
                msg.getMessage("common.error.bad_parameter", null, LocaleContextHolder.getLocale()),
                Map.of(e.getParameterName(), e.getMessage())
        );
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ProblemDetail handleMethodArgumentNotValidException(
            MethodArgumentNotValidException e) {
        return createProblemDetail(
                HttpStatus.BAD_REQUEST,
                msg.getMessage("common.error.bad_parameter", null, LocaleContextHolder.getLocale()),
                e.getBindingResult().getFieldErrors()
                        .stream()
                        .collect(Collectors.toMap(FieldError::getField,
                                it -> StringUtils.isBlank(it.getDefaultMessage())
                                        ? "Validation error"
                                        : it.getDefaultMessage()))
        );
    }

    @ExceptionHandler(AccessDeniedException.class)
    public ProblemDetail handleAccessDeniedException(AccessDeniedException e,
                                                     HttpServletRequest request) {
        if (new AntPathMatcher().match("/admin/**", request.getServletPath())) {
            return createProblemDetail(
                    HttpStatus.FORBIDDEN,
                    e.getMessage(),
                    null
            );
        }

        return createProblemDetail(
                HttpStatus.UNAUTHORIZED,
                "Unauthorized",
                null
        );
    }

    @ExceptionHandler(NoHandlerFoundException.class)
    public ProblemDetail handleNoHandlerFoundException(NoHandlerFoundException e) {
        return createProblemDetail(
                HttpStatus.NOT_FOUND,
                e.getMessage(),
                null
        );
    }

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ProblemDetail handleMethodArgumentTypeMismatchException(
            MethodArgumentTypeMismatchException e) {
        Class<?> requiredType = e.getRequiredType();
        String className = (requiredType != null) ? requiredType.getSimpleName() : "";
        String message =
                e.getName() + " must be " + className + ". [input value = " + e.getValue() + "]";

        return createProblemDetail(
                HttpStatus.BAD_REQUEST,
                message,
                null
        );
    }

    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public ProblemDetail handleHttpRequestMethodNotSupportedException(
            HttpRequestMethodNotSupportedException e) {
        String supportedMethods = (e.getSupportedMethods() != null)
                ? String.join(", ", e.getSupportedMethods())
                : "";

        String message = "Request method " + e.getMethod() + " not supported. "
                + "Supported methods = [" + supportedMethods + "].";

        return createProblemDetail(
                HttpStatus.BAD_REQUEST,
                message,
                null
        );
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ProblemDetail handleHttpMessageNotReadableException(
            HttpMessageNotReadableException e) {
        String originalMessage = e.getMessage();
        String message =
                (originalMessage != null) ? originalMessage.split(";")[0]
                        : "Malformed JSON request";

        return createProblemDetail(
                HttpStatus.BAD_REQUEST,
                message,
                null
        );
    }

    @ExceptionHandler({Exception.class, ResponseStatusException.class})
    public ProblemDetail handleException(Exception e) {
        log.error(e.getMessage(), e);

        return createProblemDetail(
                HttpStatus.INTERNAL_SERVER_ERROR,
                msg.getMessage("common.error.unknown", null, LocaleContextHolder.getLocale()),
                null
        );
    }


    private ProblemDetail createProblemDetail(HttpStatus httpStatus, String message, Map<String, String> errors) {
        var problemDetail = ProblemDetail.forStatusAndDetail(httpStatus, message);
        if (!CollectionUtils.isEmpty(errors)) {
            problemDetail.setProperty("errors", errors);
        }
        return problemDetail;
    }


}
