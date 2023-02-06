package com.b304.bobs.jwt;

import com.b304.bobs.oauth2.CustomOAuth2User;
import io.jsonwebtoken.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.Base64;
import java.util.Collection;
import java.util.Date;
import java.util.stream.Collectors;

@Component
@Slf4j
public class JwtProvider {

    private final String SECRET_KEY;

    private static final Long ACCESS_TOKEN_VALIDATE_TIME = 1000L;
    public static final Long REFRESH_TOKEN_VALIDATE_TIME = 1000L * 60 * 60 * 24 * 7;
    private final String AUTHORITIES_KEY = "role";

    public JwtProvider(@Value("${app.auth.jwt.secret-key}") String secretKey) {
        this.SECRET_KEY = Base64.getEncoder().encodeToString(secretKey.getBytes());
    }

    // accessToken 생성 메서드
    public String createAccessToken(Authentication authentication) {
        Date now = new Date();
        Date validity = new Date(now.getTime() + ACCESS_TOKEN_VALIDATE_TIME);

        // 사용자 정보 가져오기
        CustomOAuth2User oAuth2User = (CustomOAuth2User) authentication.getPrincipal();

        String email = oAuth2User.getName();
        // 권한 가져오기
        String role = authentication.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.joining(","));

        return Jwts.builder()
                .signWith(SignatureAlgorithm.HS512, SECRET_KEY) // header에 들어갈 내용 및 서명을 하기 위한 시크릿 키
                // payload에 들어갈 내용
                .setSubject(email) 
                .claim(AUTHORITIES_KEY, role)
                .setIssuer("issuer")
                .setIssuedAt(now)
                .setExpiration(validity)
                .compact();
    }

    // refresh token 생성 메서드
    public String createRefreshToken(Authentication authentication) {
        Date now = new Date();
        Date validity = new Date(now.getTime() + REFRESH_TOKEN_VALIDATE_TIME);

        return Jwts.builder()
                .signWith(SignatureAlgorithm.HS512, SECRET_KEY)
                .setIssuer("issuer")
                .setIssuedAt(now)
                .setExpiration(validity)
                .compact();
    }

    // jwt로 인증번호 조회
    public Authentication getAuthentication(String accessToken) {
        // token 복호화 (jwt에서 claims 추출)
        Claims claims = parseClaims(accessToken);

        // claim에서 권한 정보 가져오기
        Collection<? extends GrantedAuthority> authorities =
                Arrays.stream(claims.get(AUTHORITIES_KEY).toString().split(","))
                .map(SimpleGrantedAuthority::new).collect(Collectors.toList());

        // Authentication return
        return new UsernamePasswordAuthenticationToken(new CustomOAuth2User(claims.getSubject()), "", authorities);
    }

    // jwt token 복호화해서 가져오기
    private Claims parseClaims(String accessToken) {
        try {
            return Jwts.parser().setSigningKey(SECRET_KEY).parseClaimsJws(accessToken).getBody();
        } catch (ExpiredJwtException e) {
            return e.getClaims();
        }
    }

    // jwt의 유효성 및 만료일자 확인
    public boolean validateToken(String token) {
        try {
            Jwts.parser().setSigningKey(SECRET_KEY).parseClaimsJws(token);
            return true;
        } catch (ExpiredJwtException e) {
            log.info("만료된 토큰입니다.");
        } catch (UnsupportedJwtException e) {
            log.info("지원되지 않는 토큰입니다.");
        } catch (IllegalStateException e) {
            log.info("토큰이 잘못되었습니다.");
        }

        return false;
    }
}
