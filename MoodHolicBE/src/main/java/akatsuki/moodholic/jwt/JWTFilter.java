package akatsuki.moodholic.jwt;

import akatsuki.moodholic.dto.CustomOAuth2User;
import akatsuki.moodholic.dto.UserDTO;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Arrays;

public class JWTFilter extends OncePerRequestFilter {

    private final JWTUtil jwtUtil;

    public JWTFilter(JWTUtil jwtUtil) {
        this.jwtUtil = jwtUtil;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {

        // 쿠키에서 "Authorization" 이름을 가진 쿠키 찾기
        String token = Arrays.stream(request.getCookies() != null ? request.getCookies() : new Cookie[]{})
                .filter(cookie -> "Authorization".equals(cookie.getName()))
                .findFirst()
                .map(Cookie::getValue)
                .orElse(null);

        // 토큰이 없으면 다음 필터로 요청 전달
        if (token == null) {
            System.out.println("Token is null");
            filterChain.doFilter(request, response);
            return;
        }

        // 토큰 만료 확인
        if (jwtUtil.isExpired(token)) {
            System.out.println("Token is expired");
            filterChain.doFilter(request, response);
            return;
        }

        // 토큰에서 사용자 이름과 역할 추출
        String username = jwtUtil.getUsername(token);
        String role = jwtUtil.getRole(token);

        // UserDTO를 사용하여 사용자 정보 설정
        UserDTO userDTO = new UserDTO();
        userDTO.setUsername(username);
        userDTO.setRole(role);

        // CustomOAuth2User를 사용하여 Spring Security 인증 객체 생성
        CustomOAuth2User customOAuth2User = new CustomOAuth2User(userDTO);
        Authentication authentication = new UsernamePasswordAuthenticationToken(customOAuth2User, null, customOAuth2User.getAuthorities());

        // SecurityContext에 인증 객체 설정
        SecurityContextHolder.getContext().setAuthentication(authentication);

        // 다음 필터로 요청 전달
        filterChain.doFilter(request, response);
    }
}
