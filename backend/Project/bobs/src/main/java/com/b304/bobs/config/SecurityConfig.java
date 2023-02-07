package com.b304.bobs.config;

import com.b304.bobs.jwt.JwtAccessDeniedHandler;
import com.b304.bobs.jwt.JwtAuthenticationEntryPoint;
import com.b304.bobs.jwt.JwtAuthenticationFilter;
import com.b304.bobs.jwt.JwtProvider;
import com.b304.bobs.oauth2.CustomOAuth2UserService;
import com.b304.bobs.oauth2.OAuth2LoginSuccessHandler;
import com.b304.bobs.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

//
@EnableWebSecurity // 웹 보안 활성화를 위함
@RequiredArgsConstructor // 초기화 되지 않은 final 필드나 @NonNull이 붙은 필드에 대해 생성자를 생성해줌
public class SecurityConfig {

    private final UserRepository userRepository;
    private final JwtProvider jwtProvider;
    private final CustomOAuth2UserService customOAuth2UserService;
    private final JwtAuthenticationFilter jwtAuthenticationFilter;
    private final JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;
    private final JwtAccessDeniedHandler jwtAccessDeniedHandler;

    // 로그인 성공 시
    @Bean
    public AuthenticationSuccessHandler authenticationSuccessHandler() {
        return new OAuth2LoginSuccessHandler(userRepository, jwtProvider);
    }

    // 컴포넌트 기반의 보안 설정이 가능해진다.
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        // spring security의 SCRF를 막는다.
        http.csrf().disable();
        http.authorizeHttpRequests()
                // POST
                .antMatchers(HttpMethod.POST, "/users").authenticated()
                // anyRequest 허용
                .anyRequest().permitAll()
                .and()
                .logout()
                .logoutSuccessUrl("/")
                .and()
                .oauth2Login()
                .successHandler(authenticationSuccessHandler())
                .userInfoEndpoint() // OAuth2 로그인 성공 이후 사용자 정보를 가져올 때 설정을 저장
                .userService(customOAuth2UserService); // OAuth2 로그인 성공 시, 후작업을 진행할 UserService 인터페이스 구현체 등록

        // jwt 사용을 위해 session 해제
        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);

        // jwt 필터 추가
        http.addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);

        // jwt 인증 실패시 exception handler 등록
        http.exceptionHandling()
                .accessDeniedHandler(jwtAccessDeniedHandler)
                .authenticationEntryPoint(jwtAuthenticationEntryPoint);

        return http.build();
    }
}
