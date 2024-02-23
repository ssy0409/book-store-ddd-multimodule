package kr.ssy.bookstore2.adminapi.config.web;

import kr.ssy.bookstore2.adminapi.config.security.AdminAuthenticationFilter;
import kr.ssy.bookstore2.adminapi.config.security.TokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.annotation.web.configurers.HeadersConfigurer.FrameOptionsConfig;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;

import java.util.List;

import static org.springframework.security.config.http.SessionCreationPolicy.STATELESS;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity(securedEnabled = true)
@RequiredArgsConstructor
public class WebSecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http,
                                           TokenProvider tokenProvider) throws Exception {
        http
                .authorizeHttpRequests(configurer -> configurer
                        //      .requestMatchers("/docs/**",
                        //             "/swagger*/**", "/admins/**", "/users/**").permitAll()
                        //      .anyRequest().authenticated()) // 나머지 모든 요청에 대해 인증 요구
                        .anyRequest().permitAll())
                .headers(configurer -> configurer.frameOptions(FrameOptionsConfig::disable))
                .formLogin(AbstractHttpConfigurer::disable)
                .httpBasic(AbstractHttpConfigurer::disable)
                .csrf(AbstractHttpConfigurer::disable)
                .sessionManagement(configurer -> configurer.sessionCreationPolicy(STATELESS))
                .cors(configurer ->
                        configurer.configurationSource(request -> {
                            var cors = new CorsConfiguration();
                            cors.setAllowedOrigins(List.of("*"));
                            cors.setAllowedMethods(
                                    List.of("GET", "POST", "PUT", "PATCH", "DELETE", "OPTIONS"));
                            cors.setAllowedHeaders(List.of("*"));
                            return cors;
                        }))

/*
                .addFilterBefore(new UserAuthenticationFilter(userJwtTokenProvider),
                        UsernamePasswordAuthenticationFilter.class)
*/
                .addFilterBefore(new AdminAuthenticationFilter(tokenProvider),
                        UsernamePasswordAuthenticationFilter.class)
                .sessionManagement(configurer -> configurer.sessionCreationPolicy(STATELESS));

        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
