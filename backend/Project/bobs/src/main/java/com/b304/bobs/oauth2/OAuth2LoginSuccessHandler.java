package com.b304.bobs.oauth2;

import com.b304.bobs.entity.User;
import com.b304.bobs.jwt.JwtProvider;
import com.b304.bobs.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseCookie;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Optional;


@Slf4j
@RequiredArgsConstructor
public class OAuth2LoginSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {

    private final UserRepository userRepository;
    private final JwtProvider jwtProvider;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        String accessToken = jwtProvider.createAccessToken(authentication);
        String refreshToken = jwtProvider.createRefreshToken(authentication);

        CustomOAuth2User oAuth2User = (CustomOAuth2User) authentication.getPrincipal();
        saveOrUpdateUser(refreshToken, oAuth2User);

        ResponseCookie cookie = ResponseCookie.from("refresh", refreshToken)
                .httpOnly(true)
                .maxAge(JwtProvider.REFRESH_TOKEN_VALIDATE_TIME)
                .path("/")
                .build();

        clearAuthenticationAttributes(request, response);

        response.addHeader("Set-Cookie", cookie.toString());
        response.getWriter().write(accessToken);
    }

    private void saveOrUpdateUser(String refreshToken, CustomOAuth2User oAuth2User) {
        Optional<User> opt = userRepository.findByEmail(oAuth2User.getEmail());
        User user;

        if (opt.isEmpty()) {
            user = User.builder()
                    .user_email(oAuth2User.getEmail())
                    .user_name(oAuth2User.getNickname())
                    .user_key(refreshToken)
                    .build();
        } else {
            user = opt.get();
            user.updateRefreshToken(refreshToken);
        }

        userRepository.save(user);
    }

    protected void clearAuthenticationAttributes(HttpServletRequest request, HttpServletResponse response) {
        super.clearAuthenticationAttributes(request);
    }
}
