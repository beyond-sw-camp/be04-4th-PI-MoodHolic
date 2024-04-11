package akatsuki.moodholic.oauth2;

import akatsuki.moodholic.repository.RefreshRepository;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class CustomLogoutSuccessHandler implements LogoutSuccessHandler {

    private final RefreshRepository refreshRepository;
    private static final Logger log = LoggerFactory.getLogger(CustomLogoutSuccessHandler.class);

    @Autowired
    public CustomLogoutSuccessHandler(RefreshRepository refreshRepository) {
        this.refreshRepository = refreshRepository;
    }

    @Override
    public void onLogoutSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException {

        String refreshTokenValue = extractRefreshToken(request.getCookies());

        if (refreshTokenValue != null) {
            // RefreshToken 값으로 엔티티를 삭제하고 로그를 남깁니다.
            refreshRepository.deleteByRefreshToken(refreshTokenValue);
            log.info("Refresh token deleted: {}", refreshTokenValue);
        } else {
            log.info("No refresh token found in request cookies.");
        }

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
