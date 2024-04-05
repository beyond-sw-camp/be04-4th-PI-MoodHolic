//package akatsuki.moodholic.controller;
//
//import akatsuki.moodholic.jwt.JWTUtil;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//@RestController
//public class AuthenticationController {
//
//    @Autowired
//    private JWTUtil jwtUtil;
//
//    @PostMapping("/login")
//    public ResponseEntity<?> authenticateUser(LoginRequest loginRequest) {
//        // 사용자 인증 로직 (생략)
//        // 인증 성공 후 토큰 발급
//        String username = "user"; // 예시 사용자 이름
//        String role = "ROLE_USER"; // 예시 역할
//
//        String accessToken = jwtUtil.createAccessToken(username, role);
//        String refreshToken = jwtUtil.createRefreshToken(username, role);
//
//        // 토큰 반환
//        return ResponseEntity.ok()
//                .header("AccessToken", accessToken)
//                .header("RefreshToken", refreshToken)
//                .body("Login successful.");
//    }
//}
