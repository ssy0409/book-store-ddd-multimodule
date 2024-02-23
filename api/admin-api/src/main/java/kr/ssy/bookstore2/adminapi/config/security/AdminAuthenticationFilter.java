package kr.ssy.bookstore2.adminapi.config.security;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@RequiredArgsConstructor
public class AdminAuthenticationFilter extends OncePerRequestFilter {
    private final TokenProvider tokenProvider;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String token = parseBearerToken(request);

        if (token == null) {
            var securityContext = SecurityContextHolder.createEmptyContext();
            securityContext
                    .setAuthentication(
                            new UsernamePasswordAuthenticationToken(
                                    AdminDetails.createAnonymous(),
                                    Collections.emptyList())
                    );
            SecurityContextHolder.setContext(securityContext);


        } else if (tokenProvider.validateToken(token)) {


            var admin = tokenProvider.getManagerByToken(token);

            List<RoleGrantAuthority> authorities =
                    admin.adminAuthorityList()
                            .stream()
                            .map(authority -> new RoleGrantAuthority(authority.getAuthorityType().combinedWithAdmin()))
                            .collect(Collectors.toList());


            var securityContext = SecurityContextHolder.createEmptyContext();
            securityContext
                    .setAuthentication(
                            new UsernamePasswordAuthenticationToken(
                                    new AdminDetails(
                                            admin.id(),
                                            admin.name(),
                                            admin.email(),
                                            authorities
                                    ),
                                    token,
                                    authorities
                            )

                    );
            SecurityContextHolder.setContext(securityContext);

        }
        filterChain.doFilter(request, response);

    }

    private String parseBearerToken(HttpServletRequest request) {
        // 헤더에서 토큰 추출
        String bearerToken = request.getHeader("Authorization");
        if (StringUtils.hasText(bearerToken) && bearerToken.startsWith("Bearer ")) {
            return bearerToken.substring(7);
        }
        return null;
    }
}
