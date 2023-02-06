package com.b304.bobs.service;
import com.b304.bobs.dto.UserDTO;
import com.b304.bobs.entity.User;
import com.b304.bobs.jwt.JwtProvider;
import com.b304.bobs.oauth2.CustomOAuth2User;
import com.b304.bobs.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;
    private final JwtProvider jwtProvider;

    // Access token 재발급
    public String reissueAccessToken(String oldAccessToken, String refreshToken) {
        if (!jwtProvider.validateToken(refreshToken)) {
            throw new RuntimeException("invalid refresh token");
        }

        Authentication authentication = jwtProvider.getAuthentication(oldAccessToken);
        String email = ((CustomOAuth2User) authentication.getPrincipal()).getEmail();

        log.info("access token reissue 대상: {}", email);
        UserDTO findUser = new UserDTO(userRepository.findByEmail(email).orElseThrow(RuntimeException::new));

        if (!refreshToken.equals(findUser.getUser_key())) {
            throw new RuntimeException("invalid refresh token");
        }

        return jwtProvider.createAccessToken(authentication);
    }
}
