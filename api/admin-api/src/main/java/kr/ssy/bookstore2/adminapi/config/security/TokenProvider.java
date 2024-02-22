package kr.ssy.bookstore2.adminapi.config.security;

import io.jsonwebtoken.*;
import kr.ssy.bookstore2.admin.application.contracts.AdminModule;
import kr.ssy.bookstore2.admin.application.query.getadminbyemail.GetAdminByEmail;
import kr.ssy.bookstore2.admin.application.query.getadminbyemail.GetAdminByEmailResult;
import kr.ssy.bookstore2.admin.domain.admin.Admin;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;
import java.util.Date;


@Slf4j
@Component
@RequiredArgsConstructor
public class TokenProvider {

    private final AdminModule adminModule;
    private final long expirationTimeInMillis = 1000L * 60 * 60 * 24; // 1일
    private String secretKeyString = "mySecretKeymySecretKeymySecretKeymySecretKeymySecretKeymySecretKeymySecretKey";
    byte[] secretKeyBytes = secretKeyString.getBytes(StandardCharsets.UTF_8);


    // 토큰 생성
    public String generateToken(Admin admin) {
        //  byte[] secretKeyBytes = secretKeyString.getBytes(StandardCharsets.UTF_8);

        return Jwts.builder()
                .setSubject(String.valueOf(admin.getId()))
                .claim("email", admin.getEmail())
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + expirationTimeInMillis))
                .signWith(SignatureAlgorithm.HS256, secretKeyBytes)
                .compact();
    }

    // 토큰 유효성 검사
    public boolean validateToken(String token) {
        try {
            //     byte[] secretKeyBytes = secretKeyString.getBytes(StandardCharsets.UTF_8);

            Jwts.parser().setSigningKey(secretKeyBytes).parseClaimsJws(token);
            return true;
        } catch (io.jsonwebtoken.security.SecurityException | MalformedJwtException e) {
            log.info("잘못된 JWT 서명입니다.");
        } catch (ExpiredJwtException e) {
            log.info("만료된 JWT 토큰입니다.");
        } catch (UnsupportedJwtException e) {
            log.info("지원되지 않는 JWT 토큰입니다.");
        } catch (IllegalArgumentException e) {
            log.info("JWT 토큰이 잘못되었습니다.");
        }
        return false;

    }


    public GetAdminByEmailResult getManagerByToken(String token) {
        //     byte[] secretKeyBytes = secretKeyString.getBytes(StandardCharsets.UTF_8);

        Claims claims =
                Jwts.parser()
                        .setSigningKey(secretKeyBytes)
                        .parseClaimsJws(token)
                        .getBody();

        var email = (String) claims.get("email");

        var result = adminModule.executeQuery(new GetAdminByEmail(email));

        return result;
    }


}
