package akatsuki.moodholic.oauth2;

import akatsuki.moodholic.domain.Member;
import akatsuki.moodholic.dto.CustomOAuth2User;
import akatsuki.moodholic.jwt.JWTUtil;
import akatsuki.moodholic.repository.MemberDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Collection;
import java.util.Iterator;

@Component
public class CustomSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {

    private final JWTUtil jwtUtil;

    public CustomSuccessHandler(JWTUtil jwtUtil) {
        this.jwtUtil = jwtUtil;
    }

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        CustomOAuth2User customUserDetails = (CustomOAuth2User) authentication.getPrincipal();
        String username = customUserDetails.getProviderCode();

        Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
        Iterator<? extends GrantedAuthority> iterator = authorities.iterator();
        GrantedAuthority auth = iterator.next();
        String role = auth.getAuthority();

        // AccessToken 발급
        String accessToken = jwtUtil.createAccessToken(username, role);
        // RefreshToken 발급 (RefreshToken 생성 로직을 JWTUtil에 추가해야 함)
        String refreshToken = jwtUtil.createRefreshToken(username, role);

        // AccessToken과 RefreshToken을 쿠키로 클라이언트에 전달
        response.addCookie(createCookie("AccessToken", accessToken, 60*60*1)); // 1시간 유효
        response.addCookie(createCookie("RefreshToken", refreshToken, 60*60*24*7)); // 7일 유효

        response.sendRedirect("http://localhost:5173/");
    }

    private Cookie createCookie(String key, String value, int maxAge) {
        Cookie cookie = new Cookie(key, value);
        cookie.setMaxAge(maxAge);
        cookie.setPath("/");
        // cookie.setSecure(true);
        // cookie.setHttpOnly(true);
        return cookie;
    }
}
