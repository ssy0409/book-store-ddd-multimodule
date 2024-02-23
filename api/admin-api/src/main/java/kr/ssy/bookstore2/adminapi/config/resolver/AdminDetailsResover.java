package kr.ssy.bookstore2.adminapi.config.resolver;

import kr.ssy.bookstore2.adminapi.config.security.AdminDetails;
import org.springframework.core.MethodParameter;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

public class AdminDetailsResover implements HandlerMethodArgumentResolver {
    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        return parameter.getParameterType().equals(AdminDetails.class);
    }

    @Override
    public AdminDetails resolveArgument(
            MethodParameter parameter,
            ModelAndViewContainer mavContainer,
            NativeWebRequest webRequest,
            WebDataBinderFactory binderFactory) throws Exception {
        var principal = SecurityContextHolder.
                getContext().
                getAuthentication().getPrincipal();
        if (principal instanceof AdminDetails adminDetails) {
            return adminDetails;
        }
        return null;

    }
}
