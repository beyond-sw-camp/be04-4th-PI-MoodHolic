package akatsuki.moodholic.oauth2;

import akatsuki.moodholic.domain.RefreshEntity;
import akatsuki.moodholic.dto.CustomOAuth2User;
import akatsuki.moodholic.jwt.JWTUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
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
import java.time.Duration;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;

@Component
public class CustomSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {

    private static final Logger logger = LoggerFactory.getLogger(CustomSuccessHandler.class);

    @Autowired
    private JWTUtil jwtUtil;

    @Autowired
    private StringRedisTemplate redisTemplate;

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
        logger.info("생성된 accessToken = " + accessToken);

        // RefreshToken 생성 및 Redis에 저장
        String refreshToken = jwtUtil.createJwt("refresh", email, role, provider, jwtUtil.getRefreshTokenExpirationTime());
        redisTemplate.opsForValue().set(refreshToken, email, Duration.ofSeconds(jwtUtil.getRefreshTokenExpirationTime() / 1000));
        logger.info("생성된 refreshToken = " + refreshToken);

        // RefreshToken 쿠키에 설정
        response.addCookie(createCookie("refreshToken", refreshToken, (int) (jwtUtil.getRefreshTokenExpirationTime() / 1000)));

        // AccessToken 헤더에 설정
        response.setHeader("Authorization", "Bearer " + accessToken);
        response.setStatus(HttpStatus.OK.value());
        response.sendRedirect("http://localhost:5173/");
    }

    private Cookie createCookie(String name, String value, int maxAge) {
        Cookie cookie = new Cookie(name, value);
        cookie.setMaxAge(maxAge);
        cookie.setPath("/");
        cookie.setHttpOnly(false);
        return cookie;
    }
}
