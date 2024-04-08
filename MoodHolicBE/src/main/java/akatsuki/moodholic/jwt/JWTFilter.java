package akatsuki.moodholic.jwt;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import java.io.IOException;
import akatsuki.moodholic.repository.MemberDAO;
import akatsuki.moodholic.dto.CustomOAuth2User;

@Component
public class JWTFilter extends OncePerRequestFilter {
    private final JWTUtil jwtUtil;
    private final MemberDAO memberDAO; // Use MemberDAO to fetch Member details

    @Autowired
    public JWTFilter(JWTUtil jwtUtil, MemberDAO memberDAO) {
        this.jwtUtil = jwtUtil;
        this.memberDAO = memberDAO;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        String token = getTokenFromRequest(request);
        if (token != null && !jwtUtil.isExpired(token)) {
            String email = jwtUtil.getEmail(token);
            memberDAO.findByEmail(email).ifPresent(member -> {
                // Now using the member object directly to create CustomOAuth2User
                CustomOAuth2User customOAuth2User = new CustomOAuth2User(member);
                Authentication authentication = new UsernamePasswordAuthenticationToken(customOAuth2User, null, customOAuth2User.getAuthorities());
                SecurityContextHolder.getContext().setAuthentication(authentication);
            });
        }
        filterChain.doFilter(request, response);
    }

    private String getTokenFromRequest(HttpServletRequest request) {
        String bearerToken = request.getHeader("Authorization");
        if (bearerToken != null && bearerToken.startsWith("Bearer ")) {
            return bearerToken.substring(7);
        }
        return null;
    }
}
