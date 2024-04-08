package akatsuki.moodholic.oauth2;

import akatsuki.moodholic.dto.CustomOAuth2User;
import akatsuki.moodholic.jwt.JWTUtil;
import akatsuki.moodholic.domain.Token;
import akatsuki.moodholic.repository.TokenDAO; // 수정된 import 문
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;

@Component
public class CustomSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {

    private final JWTUtil jwtUtil;
    private final TokenDAO tokenDAO; // TokenDAO 사용

    @Autowired
    public CustomSuccessHandler(JWTUtil jwtUtil, TokenDAO tokenDAO) {
        this.jwtUtil = jwtUtil;
        this.tokenDAO = tokenDAO;
    }

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        CustomOAuth2User customUserDetails = (CustomOAuth2User) authentication.getPrincipal();
        String email = customUserDetails.getEmail();
        String role = customUserDetails.getAuthorities().iterator().next().getAuthority();

        Token storedToken = tokenDAO.findById(email).orElse(new Token(email, "", "", new Date(), new Date()));
        String refreshToken = jwtUtil.createRefreshToken(role, email);
        String accessToken = jwtUtil.createAccessToken(role, email);
        Date accessTokenExpiration = jwtUtil.extractExpiration(accessToken);

        storedToken.setAccessToken(accessToken);
        storedToken.setRefreshToken(refreshToken);
        storedToken.setIssuedAt(new Date());
        storedToken.setAccessTokenExpiresAt(accessTokenExpiration);
        tokenDAO.save(storedToken);

        setTokenResponse(response, storedToken);
    }

    private void setTokenResponse(HttpServletResponse response, Token storedToken) throws IOException {
        response.setHeader("Content-Type", "application/x-www-form-urlencoded;charset=utf-8");
        response.setHeader("AccessToken", storedToken.getAccessToken());
        response.setHeader("RefreshToken", storedToken.getRefreshToken());
        response.setHeader("Authorization", "Bearer " + storedToken.getAccessToken());

        Cookie accessTokenCookie = new Cookie("AccessToken", storedToken.getAccessToken());
        accessTokenCookie.setMaxAge(30 * 60); // 30분 유효
        accessTokenCookie.setPath("/");
        response.addCookie(accessTokenCookie);

        response.sendRedirect("http://localhost:8888/");
    }
}
