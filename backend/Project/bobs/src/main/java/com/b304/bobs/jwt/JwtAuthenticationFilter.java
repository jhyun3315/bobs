package com.b304.bobs.jwt;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureException;
import io.jsonwebtoken.UnsupportedJwtException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
@Slf4j
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final JwtProvider jwtProvider;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        log.info("request method: {}", request.getMethod());
//        log.info("jwt filter");
        String token = parseBearerToken(request);

        try {
            if (StringUtils.hasText(token) && jwtProvider.validateToken(token)) {
                Authentication authentication = jwtProvider.getAuthentication(token);
                SecurityContextHolder.getContext().setAuthentication(authentication);
            }
        } catch (ExpiredJwtException e) {
            log.info("만료된 토큰입니다.");
            request.setAttribute("Exception", JwtExceptionCode.EXPIRE.name());
        } catch (SignatureException | MalformedJwtException e) {
            log.info("유효하지 않은 토큰입니다.");
            request.setAttribute("Exception", "invalid");
        } catch (UnsupportedJwtException e) {
            log.info("지원하지 않는 토큰입니다.");
            request.setAttribute("Exception", "unsupported");
        } catch (IllegalStateException e) {
            log.info("잘못된 토큰입니다.");
            request.setAttribute("Exception", "illegal");
        }

        filterChain.doFilter(request, response);
    }

    private String parseBearerToken(HttpServletRequest request) {
        String bearerToken = request.getHeader("Authorization");

        if (StringUtils.hasText(bearerToken) && bearerToken.startsWith("Bearer ")) {
            return bearerToken.substring((7));
        }

        return null;
    }
}
