package com.b304.bobs.controller;

import com.b304.bobs.service.AuthService;
import com.b304.bobs.util.CookieUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
@Slf4j
public class AuthController {

    private final AuthService authService;

    @GetMapping
    public String reissueAccessToken(HttpServletRequest request, @RequestHeader("Authorization") String oldAccessToken) {
        oldAccessToken = oldAccessToken.substring(7);
        log.info("oldAccessToken: {}", oldAccessToken);
        String refreshToken = CookieUtils.getCookie(request, "refresh")
                .orElseThrow(() -> new RuntimeException("refresh token이 없습니다."))
                .getValue();
        log.info("refreshToken: {}", refreshToken);

        String newAccessToken = authService.reissueAccessToken(oldAccessToken, refreshToken);
        log.info("newAccessToken: {}", newAccessToken);

        return newAccessToken;
    }
}
