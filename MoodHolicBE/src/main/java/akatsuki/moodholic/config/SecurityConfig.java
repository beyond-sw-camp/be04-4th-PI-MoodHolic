package akatsuki.moodholic.config;

import akatsuki.moodholic.jwt.JWTFilter;
import akatsuki.moodholic.jwt.JWTUtil;
import akatsuki.moodholic.oauth2.CustomLogoutSuccessHandler;
import akatsuki.moodholic.oauth2.CustomSuccessHandler;
import akatsuki.moodholic.repository.MemberDAO;
import akatsuki.moodholic.service.CustomOAuth2UserService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    private final CustomOAuth2UserService customOAuth2UserService;
    private final CustomLogoutSuccessHandler customLogoutSuccessHandler;
    private final CustomSuccessHandler customSuccessHandler;
    private final JWTUtil jwtUtil;
    private final MemberDAO memberDAO;

    public SecurityConfig(CustomOAuth2UserService customOAuth2UserService, CustomLogoutSuccessHandler customLogoutSuccessHandler, CustomSuccessHandler customSuccessHandler, JWTUtil jwtUtil, MemberDAO memberDAO) {
        this.customOAuth2UserService = customOAuth2UserService;
        this.customLogoutSuccessHandler = customLogoutSuccessHandler;
        this.customSuccessHandler = customSuccessHandler;
        this.jwtUtil = jwtUtil;
        this.memberDAO = memberDAO;
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        //csrf disable
        http.csrf((auth) -> auth.disable());

        //From 로그인 방식 disable
        http.formLogin((auth) -> auth.disable());

        //HTTP Basic 인증 방식 disable
        http.httpBasic((auth) -> auth.disable());

        //JWTFilter 추가
        http.addFilterBefore(new JWTFilter(jwtUtil, memberDAO), UsernamePasswordAuthenticationFilter.class);

        //oauth2
        http.oauth2Login((oauth2) -> oauth2
                .userInfoEndpoint((userInfoEndpointConfig) -> userInfoEndpointConfig
                        .userService(customOAuth2UserService))
                .successHandler(customSuccessHandler)
        );

        //경로별 인가 작업
        http.authorizeHttpRequests((auth) -> auth.anyRequest().permitAll());

        //세션 설정 : STATELESS
        http.sessionManagement((session) -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS));

        http.logout(logout -> logout
                 .logoutUrl("/logout")
                .logoutSuccessUrl("/login")
                .logoutSuccessHandler(customLogoutSuccessHandler)
        );

        return http.build();
    }
}
