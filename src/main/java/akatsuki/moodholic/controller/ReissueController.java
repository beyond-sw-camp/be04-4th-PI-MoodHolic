//package akatsuki.moodholic.controller;
//
//import akatsuki.moodholic.domain.RefreshEntity;
//import akatsuki.moodholic.jwt.JWTUtil;
//import akatsuki.moodholic.repository.RefreshRepository;
//import io.jsonwebtoken.ExpiredJwtException;
//import jakarta.servlet.http.Cookie;
//import jakarta.servlet.http.HttpServletRequest;
//import jakarta.servlet.http.HttpServletResponse;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.stereotype.Controller;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.ResponseBody;
//
//import java.util.Date;
//
//@Controller
//@ResponseBody
//public class ReissueController {
//
//    private final JWTUtil jwtUtil;
//    private final RefreshRepository refreshRepository;
//    private final long accessTokenExpirationTime;
//    private final long refreshTokenExpirationTime;
//
//    @Autowired
//    public ReissueController(JWTUtil jwtUtil, RefreshRepository refreshRepository,
//                             @Value("${spring.jwt.accessTokenExpirationTime}") long accessTokenExpirationTime,
//                             @Value("${spring.jwt.refreshTokenExpirationTime}") long refreshTokenExpirationTime)  {
//        this.jwtUtil = jwtUtil;
//        this.refreshRepository = refreshRepository;
//        this.accessTokenExpirationTime = accessTokenExpirationTime;
//        this.refreshTokenExpirationTime = refreshTokenExpirationTime;
//    }
//
//    @PostMapping("/reissue")
//    public ResponseEntity<?> reissue(HttpServletRequest request, HttpServletResponse response) {
//        // 새로운 JWT를 발급하고 사용자에게 응답합니다.
//
//        // 새로고침 토큰 가져오기
//        String refresh = null;
//        Cookie[] cookies = request.getCookies();
//        if (cookies != null) {
//            for (Cookie cookie : cookies) {
//                if ("refreshToken".equals(cookie.getName())) {
//                    refresh = cookie.getValue();
//                    break;
//                }
//            }
//        }
//
//        if (refresh == null) {
//            return new ResponseEntity<>("새로고침 토큰이 없습니다", HttpStatus.BAD_REQUEST);
//        }
//
//        // 새로고침 토큰이 만료되었는지 확인합니다.
//        try {
//            jwtUtil.isExpired(refresh);
//        } catch (ExpiredJwtException e) {
//            return new ResponseEntity<>("새로고침 토큰이 만료되었습니다", HttpStatus.BAD_REQUEST);
//        }
//
//        // 토큰 카테고리를 확인합니다.
//        String category = jwtUtil.getCategory(refresh);
//        if (!"refreshToken".equals(category)) {
//            return new ResponseEntity<>("잘못된 새로고침 토큰입니다", HttpStatus.BAD_REQUEST);
//        }
//
//        // 데이터베이스에 새로고침 토큰이 존재하는지 확인합니다.
//        if (!refreshRepository.existsByRefresh(refresh)) {
//            return new ResponseEntity<>("잘못된 새로고침 토큰입니다", HttpStatus.BAD_REQUEST);
//        }
//
//        // 토큰에서 사용자 이름과 역할을 추출합니다.
//        String username = jwtUtil.getEmail(refresh);
//        String role = jwtUtil.getRole(refresh);
//
//        // 새로운 토큰을 생성합니다.
//        String newAccess = jwtUtil.createJwt("accessToken", username);
//        String newRefresh = jwtUtil.createJwt("refreshToken", username);
//
//        // 데이터베이스에 새로운 새로고침 토큰을 저장합니다.
//        refreshRepository.deleteByRefreshToken(refresh);
//        addRefreshEntity(username, newRefresh, refreshTokenExpirationTime);
//
//        // 응답 헤더를 설정합니다.
//        response.setHeader("accessToken", newAccess);
//        response.addCookie(createCookie("refreshToken", newRefresh));
//
//        return new ResponseEntity<>(HttpStatus.OK);
//    }
//
//    // 데이터베이스에 새로운 새로고침 토큰을 저장하는 메서드
//    private void addRefreshEntity(String username, String refreshToken, long expiredMs) {
//        Date expirationDate = new Date(System.currentTimeMillis() + expiredMs);
//
//        RefreshEntity refreshEntity = new RefreshEntity();
//        refreshEntity.setEmail(username);
//        refreshEntity.setRefreshToken(refreshToken);
//        refreshEntity.setExpiration(expirationDate.toString());
//
//        refreshRepository.save(refreshEntity);
//    }
//
//    // 쿠키를 생성하는 메서드
//    private Cookie createCookie(String key, String value) {
//        Cookie cookie = new Cookie(key, value);
//        cookie.setMaxAge(24 * 60 * 60); // 1일 만료
////        cookie.setHttpOnly(true);
////        cookie.setSecure(true);
////        cookie.setSameSite(SameSite.STRICT);
//        return cookie;
//    }
//}
