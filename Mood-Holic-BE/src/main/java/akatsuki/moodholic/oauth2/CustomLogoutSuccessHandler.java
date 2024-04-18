package akatsuki.moodholic.oauth2;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class CustomLogoutSuccessHandler implements LogoutSuccessHandler {

    private StringRedisTemplate redisTemplate;

    private static final Logger log = LoggerFactory.getLogger(CustomLogoutSuccessHandler.class);

    @Autowired
    public CustomLogoutSuccessHandler(StringRedisTemplate redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    @Override
    public void onLogoutSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException {

        String refreshTokenValue = extractRefreshToken(request.getCookies());

        if (refreshTokenValue != null) {
            redisTemplate.delete(refreshTokenValue);
            log.info("Refresh token deleted from Redis: {}", refreshTokenValue);
        }
        else {
            log.info("No refresh token found in request cookies.");
        }

        // 쿠키 제거
        deleteCookie(response, "refreshToken");

        response.setStatus(HttpServletResponse.SC_OK);
        response.getWriter().write("로그아웃 성공");
        log.info("Logout successful and refresh token cookie deleted.");
    }

    private String extractRefreshToken(Cookie[] cookies) {
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if ("refreshToken".equals(cookie.getName())) {
                    return cookie.getValue();
                }
            }
        }
        return null;
    }

    private void deleteCookie(HttpServletResponse response, String name) {
        Cookie cookie = new Cookie(name, null);
        cookie.setPath("/");
        cookie.setMaxAge(0);
        response.addCookie(cookie);
    }
}
