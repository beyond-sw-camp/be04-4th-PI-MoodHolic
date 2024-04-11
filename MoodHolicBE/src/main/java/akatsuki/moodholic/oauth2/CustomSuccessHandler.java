package akatsuki.moodholic.oauth2;

import akatsuki.moodholic.domain.RefreshEntity;
import akatsuki.moodholic.dto.CustomOAuth2User;
import akatsuki.moodholic.jwt.JWTUtil;
import akatsuki.moodholic.repository.RefreshRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;
import java.util.Optional;

@Component
public class CustomSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {

    private static final Logger logger = LoggerFactory.getLogger(CustomSuccessHandler.class);

    @Autowired
    private JWTUtil jwtUtil;

    @Autowired
    private RefreshRepository refreshRepository;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        CustomOAuth2User customUserDetails = (CustomOAuth2User) authentication.getPrincipal();

        String email = authentication.getName();
        String provider = customUserDetails.getProvider();

        Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();

        Iterator<? extends GrantedAuthority> iterator = authorities.iterator();

        GrantedAuthority auth = iterator.next();
        String role = auth.getAuthority();

        // AccessToken 생성
        String accessToken = jwtUtil.createJwt("access", email, role, provider, jwtUtil.getAccessTokenExpirationTime());
        System.out.println("생성된 accessToken = " + accessToken);

        // RefreshToken 생성 및 저장
        String refreshToken = jwtUtil.createJwt("refresh", email, role, provider, jwtUtil.getRefreshTokenExpirationTime());
        RefreshEntity refreshEntity = new RefreshEntity();
        refreshEntity.setEmail(email);
        refreshEntity.setRefreshToken(refreshToken);
        refreshEntity.setProvide(provider);
        refreshEntity.setExpiration((String.valueOf(new Date(System.currentTimeMillis() + jwtUtil.getRefreshTokenExpirationTime()))));
        refreshRepository.save(refreshEntity);

        System.out.println("refresh = " + refreshToken);

        logger.info("Refresh token Redis에 저장: {}", refreshToken);

        // RefreshToken 쿠키에 감기
        response.addCookie(createCookie("refreshToken", refreshToken, (int) (jwtUtil.getRefreshTokenExpirationTime() / 1000)));

        // AccessToken 헤더에 담기
        response.setHeader("Authorization", "Bearer " + accessToken);
        response.setStatus(HttpStatus.OK.value());
    }

    private Cookie createCookie(String name, String value, int maxAge) {
        Cookie cookie = new Cookie(name, value);
        cookie.setMaxAge(maxAge);
        cookie.setPath("/");
//        cookie.setHttpOnly(true);
        return cookie;
    }
}
