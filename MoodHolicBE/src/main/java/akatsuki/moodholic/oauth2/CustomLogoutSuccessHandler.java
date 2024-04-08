package akatsuki.moodholic.oauth2;

import akatsuki.moodholic.dto.CustomOAuth2User;
import akatsuki.moodholic.jwt.JWTUtil;
import akatsuki.moodholic.domain.Token;
import akatsuki.moodholic.repository.TokenDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.stereotype.Component;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Optional;

@Component
public class CustomLogoutSuccessHandler implements LogoutSuccessHandler {

    private final TokenDAO tokenDAO;

    @Autowired
    public CustomLogoutSuccessHandler(TokenDAO tokenDAO) {
        this.tokenDAO = tokenDAO;
    }

    @Override
    public void onLogoutSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        if (authentication != null && authentication.getPrincipal() instanceof CustomOAuth2User) {
            CustomOAuth2User customOAuth2User = (CustomOAuth2User) authentication.getPrincipal();
            String email = customOAuth2User.getEmail();

            // 사용자의 토큰 정보 조회 및 삭제
            Optional<Token> tokenOptional = tokenDAO.findById(email);
            tokenOptional.ifPresent(token -> {
                tokenDAO.delete(token); // 토큰 정보 데이터베이스에서 삭제
            });

            // 클라이언트의 쿠키 삭제
            deleteCookie(response, "AccessToken");
            deleteCookie(response, "RefreshToken");

            response.setStatus(HttpServletResponse.SC_OK);
            response.getWriter().write("로그아웃에 성공했습니다. 토큰이 삭제되었습니다.");
            response.getWriter().flush();
        } else {
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            response.getWriter().write("로그아웃할 인증된 사용자가 없습니다.");
            response.getWriter().flush();
        }
    }

    private void deleteCookie(HttpServletResponse response, String name) {
        Cookie cookie = new Cookie(name, null);
        cookie.setPath("/");
        cookie.setMaxAge(0);
        response.addCookie(cookie);
    }
}

